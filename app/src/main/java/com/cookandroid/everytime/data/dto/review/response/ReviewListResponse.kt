package com.cookandroid.everytime.data.dto.review.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewListResponse(
    @SerialName("lecture")
    val lecture: LectureInfoResponse,
    @SerialName("keywords")
    val keywords: List<KeywordCountResponse>,
    @SerialName("totalReviewCount")
    val totalReviewCount: Long,
    @SerialName("reviews")
    val reviews: ReviewPageResponse
)

@Serializable
data class LectureInfoResponse(
    @SerialName("sectionId")
    val sectionId: Long,
    @SerialName("courseName")
    val courseName: String,
    @SerialName("averageGradePoint")
    val averageGradePoint: Int
)

@Serializable
data class KeywordCountResponse(
    @SerialName("keywordId")
    val keywordId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("count")
    val count: Long
)

@Serializable
data class ReviewPageResponse(
    @SerialName("content")
    val content: List<ReviewResponse>,
    @SerialName("page")
    val page: Int,
    @SerialName("size")
    val size: Int,
    @SerialName("totalElements")
    val totalElements: Long,
    @SerialName("totalPages")
    val totalPages: Int,
    @SerialName("hasNext")
    val hasNext: Boolean
)

@Serializable
data class ReviewResponse(
    @SerialName("reviewId")
    val reviewId: Long,
    @SerialName("year")
    val year: Long,
    @SerialName("semester")
    val semester: String,
    @SerialName("gradePoint")
    val gradePoint: Int,
    @SerialName("content")
    val content: String,
    @SerialName("highlight")
    val highlight: HighlightResponse? = null
)

@Serializable
data class HighlightResponse(
    @SerialName("start")
    val start: Int,
    @SerialName("end")
    val end: Int
)
