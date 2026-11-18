package com.cookandroid.everytime.data.review

import com.cookandroid.everytime.data.repository.review.ReviewRepository
import com.cookandroid.everytime.data.repository.review.ReviewRepositoryImpl
import com.cookandroid.everytime.data.service.review.ReviewService
import com.sun.net.httpserver.HttpServer
import java.io.IOException
import java.net.InetSocketAddress
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

/** Loopback HTTP only; validates the real Service, serializer and Repository. */
class ReviewRepositoryTest {
    private lateinit var server: HttpServer
    private lateinit var repository: ReviewRepository
    private var status = 200
    private var body = successBody
    @Volatile private var requestPath: String? = null
    @Volatile private var requestQuery: String? = null

    @Before
    fun setUp() {
        server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)
        server.createContext("/") { exchange ->
            requestPath = exchange.requestURI.path
            requestQuery = exchange.requestURI.rawQuery
            val bytes = body.toByteArray(Charsets.UTF_8)
            exchange.responseHeaders.add("Content-Type", "application/json")
            exchange.sendResponseHeaders(status, bytes.size.toLong())
            exchange.responseBody.use { it.write(bytes) }
        }
        server.start()
        val service = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:${server.address.port}/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ReviewService::class.java)
        repository = ReviewRepositoryImpl(service)
    }

    @After
    fun tearDown() = server.stop(0)

    @Test
    fun requestUsesCorrectPathDefaultsAndParsesIntegerAndLongFields() = runBlocking {
        val response = repository.getReviews(sectionId = 4_000_000_001L)
        assertEquals("/api/v1/sections/4000000001/reviews", requestPath)
        assertEquals("sort=LATEST&page=1&size=10", requestQuery)
        assertEquals(4, response.lecture.averageGradePoint)
        assertEquals(4_000_000_000L, response.totalReviewCount)
        assertEquals(5, response.reviews.content.single().gradePoint)
        assertNull(response.reviews.content.single().highlight)
        assertEquals("서버 본문", response.reviews.content.single().content)
    }

    @Test
    fun nextPageQueryIsForwarded() = runBlocking {
        repository.getReviews(sectionId = 11L, page = 2, size = 5)
        assertEquals("sort=LATEST&page=2&size=5", requestQuery)
    }

    @Test
    fun emptyContentIsSuccess() = runBlocking {
        body = successBody.replace(reviewJson, "")
            .replace("\"averageGradePoint\":4", "\"averageGradePoint\":0")
            .replace("3000000000", "0")
            .replace("4000000000", "0")
            .replace("\"totalPages\":400000000", "\"totalPages\":0")
            .replace("\"hasNext\":true", "\"hasNext\":false")
        val response = repository.getReviews(11L)
        assertTrue(response.reviews.content.isEmpty())
        assertEquals(0L, response.totalReviewCount)
        assertFalse(response.reviews.hasNext)
    }

    @Test
    fun httpFailureWithNullDataIsNotConvertedToSuccess() = runBlocking {
        status = 404
        body = """{"success":false,"code":"404","message":"존재하지 않는 강의입니다.","data":null}"""
        try {
            repository.getReviews(11L)
            fail("HTTP failure must propagate")
        } catch (exception: HttpException) {
            assertEquals(404, exception.code())
        }
    }

    @Test
    fun networkFailureIsNotConvertedToSuccess() = runBlocking {
        server.stop(0)
        try {
            repository.getReviews(11L)
            fail("Network failure must propagate")
        } catch (_: IOException) {
            // Expected: the caller receives a failure rather than an empty response.
        }
    }

    private companion object {
        val json = Json { ignoreUnknownKeys = true }
        const val reviewJson = """{"reviewId":103,"year":2026,"semester":"2학기","gradePoint":5,"content":"서버 본문","highlight":null}"""
        val successBody = """{
            "success":true,"code":"200","message":"강의평 목록 조회 성공",
            "data":{
                "lecture":{"sectionId":4000000001,"courseName":"서버 강의명","averageGradePoint":4},
                "keywords":[
                    {"keywordId":1,"name":"교수님","count":3000000000},
                    {"keywordId":2,"name":"과제","count":0},
                    {"keywordId":3,"name":"시험","count":0},
                    {"keywordId":4,"name":"팀 프로젝트","count":0}
                ],
                "totalReviewCount":4000000000,
                "reviews":{"content":[$reviewJson],"page":1,"size":10,"totalElements":4000000000,"totalPages":400000000,"hasNext":true}
            }
        }"""
    }
}
