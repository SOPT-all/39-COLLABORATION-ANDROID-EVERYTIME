package com.cookandroid.mjhackathon.data.repository.home

import jakarta.inject.Inject

class LecturelistRepositoryImpl @Inject constructor(
    private val lecturelistService: LecturelistService,
) : TimetableRepository {

}
