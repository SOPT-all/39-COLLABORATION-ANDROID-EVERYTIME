package com.cookandroid.everytime.data.repository.timetable

import com.cookandroid.everytime.data.service.timetable.TimetableService
import jakarta.inject.Inject


class TimetableRepositoryImpl @Inject constructor(
    private val timetableService: TimetableService,
) : TimetableRepository {

}
