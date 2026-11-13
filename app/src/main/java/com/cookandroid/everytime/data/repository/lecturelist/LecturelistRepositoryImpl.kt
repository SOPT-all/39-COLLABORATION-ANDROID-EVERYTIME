package com.cookandroid.everytime.data.repository.lecturelist

import com.cookandroid.everytime.data.repository.lecturelist.LecturelistRepository
import com.cookandroid.everytime.data.service.lecturelist.LecturelistService
import jakarta.inject.Inject

class LecturelistRepositoryImpl @Inject constructor(
    private val lecturelistService: LecturelistService,
) : LecturelistRepository {

}
