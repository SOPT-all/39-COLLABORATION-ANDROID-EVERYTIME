package com.cookandroid.everytime.data.repository.review

import com.cookandroid.everytime.data.service.review.ReviewService
import jakarta.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewRepository {

}
