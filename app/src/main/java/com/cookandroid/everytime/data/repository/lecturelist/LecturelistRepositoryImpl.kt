package com.cookandroid.mjhackathon.data.repository.home

import com.cookandroid.mjhackathon.data.service.lecturelist.LecturelistService
import jakarta.inject.Inject

class LecturelistRepositoryImpl @Inject constructor(
    private val lecturelistService: LecturelistService,
) : LecturelistRepository {

}
