package com.cookandroid.mjhackathon.data.repository.home

import com.cookandroid.mjhackathon.data.service.review.ReviewService
import jakarta.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService,
) : ReviewRepository {

}
