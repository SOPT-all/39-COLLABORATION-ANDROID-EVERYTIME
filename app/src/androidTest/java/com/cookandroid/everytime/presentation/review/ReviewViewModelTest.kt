package com.cookandroid.everytime.presentation.review

import androidx.lifecycle.ViewModelStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.cookandroid.everytime.data.dto.review.KeywordCountResponse
import com.cookandroid.everytime.data.dto.review.LectureInfoResponse
import com.cookandroid.everytime.data.dto.review.ReviewListResponse
import com.cookandroid.everytime.data.dto.review.ReviewPageResponse
import com.cookandroid.everytime.data.dto.review.ReviewResponse
import com.cookandroid.everytime.data.repository.review.ReviewRepository
import java.io.IOException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/** Controlled repository responses; these tests do not contact the real API. */
@RunWith(AndroidJUnit4::class)
class ReviewViewModelTest {
    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val repository = FakeReviewRepository()
    private val store = ViewModelStore()
    private lateinit var viewModel: ReviewViewModel

    @Before
    fun setUp() = onMain {
        viewModel = ReviewViewModel(repository)
        store.put("review", viewModel)
    }

    @After
    fun tearDown() = onMain { store.clear() }

    @Test
    fun missingIdDoesNotRequestAndFirstPageUsesServerValues() {
        onMain { viewModel.loadReviews(null) }
        assertEquals(0, repository.requests.size)
        onMain {
            viewModel.loadReviews(11L)
            viewModel.loadReviews(11L)
            viewModel.loadNextPage()
        }
        assertEquals(1, repository.requests.size)
        assertEquals(1, repository.requests[0].page)
        assertEquals("LATEST", repository.requests[0].sort)
        assertEquals(10, repository.requests[0].size)
        complete(0, response(11L, 1, false, listOf(4_000_000_001L)))
        val state = viewModel.uiState.value
        assertEquals("서버 강의명", state.lectureName)
        assertEquals(4, state.averageScore)
        assertEquals(4_000_000_000L, state.totalReviewCount)
        assertEquals(listOf(1L, 2L, 3L, 4L), state.keywords.map { it.id })
        assertEquals(3_000_000_000L, state.keywords.first().count)
        assertEquals("26년 2학기 수강자", state.reviews.single().semesterLabel)
        assertEquals("서버 본문 그대로", state.reviews.single().content)
        assertEquals(4_000_000_001L, state.reviews.single().id)
        onMain { viewModel.loadReviews(11L) }
        assertEquals(1, repository.requests.size)
    }

    @Test
    fun pagesAppendWithoutDuplicatesAndStopAtLastPage() {
        onMain { viewModel.loadReviews(11L) }
        complete(0, response(11L, 1, true, listOf(103L, 103L, 102L)))
        onMain { repeat(3) { viewModel.loadNextPage() } }
        assertEquals(2, repository.requests.size)
        assertEquals(1, viewModel.uiState.value.page)
        complete(1, response(11L, 2, true, listOf(102L, 101L)))
        onMain { viewModel.loadNextPage() }
        complete(2, response(11L, 3, false, listOf(100L)))
        assertEquals(listOf(103L, 102L, 101L, 100L), viewModel.uiState.value.reviews.map { it.id })
        assertEquals(3, viewModel.uiState.value.page)
        assertEquals(4_000_000_000L, viewModel.uiState.value.totalReviewCount)
        onMain { repeat(3) { viewModel.loadNextPage() } }
        assertEquals(listOf(1, 2, 3), repository.requests.map { it.page })
    }

    @Test
    fun emptyPageIsSuccessfulAndDoesNotLoadMore() {
        onMain { viewModel.loadReviews(11L) }
        complete(0, response(11L, 1, false, emptyList()).copy(
            lecture = LectureInfoResponse(11L, "후기 없는 강의", 0),
            keywords = emptyKeywords(),
            totalReviewCount = 0L,
            reviews = ReviewPageResponse(emptyList(), 1, 10, 0L, 0, false),
        ))
        assertTrue(viewModel.uiState.value.reviews.isEmpty())
        assertNull(viewModel.uiState.value.errorMessage)
        assertEquals(0L, viewModel.uiState.value.totalReviewCount)
        assertFalse(viewModel.uiState.value.isLoading)
        onMain { viewModel.loadNextPage() }
        assertEquals(1, repository.requests.size)
    }

    @Test
    fun initialFailureAllowsSameSectionRetry() {
        onMain { viewModel.loadReviews(11L) }
        fail(0)
        assertNotNull(viewModel.uiState.value.errorMessage)
        assertEquals(0, viewModel.uiState.value.page)
        onMain { viewModel.loadReviews(11L) }
        assertTrue(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.errorMessage)
        complete(1, response(11L, 1, false, listOf(103L)))
        assertEquals(listOf(1, 1), repository.requests.map { it.page })
    }

    @Test
    fun nextPageFailurePreservesDataAndRequiresExplicitRetry() {
        onMain { viewModel.loadReviews(11L) }
        complete(0, response(11L, 1, true, listOf(103L)))
        val before = viewModel.uiState.value
        onMain { viewModel.loadNextPage() }
        fail(1)
        val after = viewModel.uiState.value
        assertEquals(before.reviews, after.reviews)
        assertEquals(before.page, after.page)
        assertEquals(before.hasNext, after.hasNext)
        assertEquals(before.keywords, after.keywords)
        assertEquals(before.totalReviewCount, after.totalReviewCount)
        assertNotNull(after.loadMoreErrorMessage)
        onMain { repeat(3) { viewModel.loadNextPage() } }
        assertEquals(2, repository.requests.size)
        onMain { viewModel.retryNextPage() }
        complete(2, response(11L, 2, false, listOf(102L)))
        assertEquals(listOf(1, 2, 2), repository.requests.map { it.page })
        assertNull(viewModel.uiState.value.loadMoreErrorMessage)
    }

    @Test
    fun lateCancelledResponseCannotOverwriteNewRequestEvenForSameSection() {
        repository.ignoreCancellation = true
        onMain { viewModel.loadReviews(11L) }
        repository.ignoreCancellation = false
        onMain {
            viewModel.loadReviews(22L)
            viewModel.loadReviews(11L)
        }
        complete(2, response(11L, 1, false, listOf(200L)))
        complete(0, response(11L, 1, false, listOf(100L)))
        assertEquals(listOf(200L), viewModel.uiState.value.reviews.map { it.id })
        assertNull(viewModel.uiState.value.errorMessage)
        onMain { viewModel.loadReviews(null) }
        assertEquals(ReviewUiState(), viewModel.uiState.value)
    }

    private fun onMain(block: () -> Unit) {
        instrumentation.runOnMainSync { block() }
        instrumentation.waitForIdleSync()
    }

    private fun complete(index: Int, value: ReviewListResponse) = onMain {
        repository.requests[index].result.complete(value)
    }

    private fun fail(index: Int) = onMain {
        repository.requests[index].result.completeExceptionally(IOException("Controlled failure"))
    }

    private fun emptyKeywords() = listOf("교수님", "과제", "시험", "팀 프로젝트").mapIndexed { index, name ->
        KeywordCountResponse(index.toLong() + 1, name, 0L)
    }

    private fun response(sectionId: Long, page: Int, hasNext: Boolean, ids: List<Long>) = ReviewListResponse(
        lecture = LectureInfoResponse(sectionId, "서버 강의명", 4),
        keywords = emptyKeywords().map { it.copy(count = 3_000_000_000L) }.reversed(),
        totalReviewCount = 4_000_000_000L,
        reviews = ReviewPageResponse(
            content = ids.map { ReviewResponse(it, 2026L, "2학기", 5, "서버 본문 그대로") },
            page = page,
            size = 10,
            totalElements = 4_000_000_000L,
            totalPages = 400_000_000,
            hasNext = hasNext,
        ),
    )

    private class FakeReviewRepository : ReviewRepository {
        val requests = mutableListOf<Request>()
        var ignoreCancellation = false

        override suspend fun getReviews(sectionId: Long, sort: String, page: Int, size: Int): ReviewListResponse {
            val request = Request(page, sort, size)
            requests += request
            return if (ignoreCancellation) {
                withContext(NonCancellable) { request.result.await() }
            } else {
                request.result.await()
            }
        }
    }

    private data class Request(
        val page: Int,
        val sort: String,
        val size: Int,
        val result: CompletableDeferred<ReviewListResponse> = CompletableDeferred(),
    )
}
