package com.cookandroid.mjhackathon.data.repository.home

import jakarta.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewService: ReviewService,
) : TimetableRepository {

}
