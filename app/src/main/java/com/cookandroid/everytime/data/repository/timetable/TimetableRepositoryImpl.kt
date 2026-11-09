package com.cookandroid.mjhackathon.data.repository.home

import jakarta.inject.Inject

class TimetableRepositoryImpl @Inject constructor(
    private val timetableService: TimetableService,
) : TimetableRepository {

}
