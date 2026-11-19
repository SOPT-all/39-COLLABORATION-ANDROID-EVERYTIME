package com.cookandroid.everytime.core.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Timetable

@Serializable
data object LectureList

@Serializable
data class Review(val courseId: Long)
