package com.cookandroid.everytime.data.di

import com.cookandroid.everytime.data.repository.lecturelist.LecturelistRepository
import com.cookandroid.everytime.data.repository.lecturelist.LecturelistRepositoryImpl
import com.cookandroid.everytime.data.repository.review.ReviewRepository
import com.cookandroid.everytime.data.repository.review.ReviewRepositoryImpl
import com.cookandroid.everytime.data.repository.timetable.TimetableRepository
import com.cookandroid.everytime.data.repository.timetable.TimetableRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindLecturelistRepository(
        impl: LecturelistRepositoryImpl,
    ): LecturelistRepository

    @Binds
    abstract fun bindReviewRepository(
        impl: ReviewRepositoryImpl,
    ): ReviewRepository

    @Binds
    abstract fun bindTimetableRepository(
        impl: TimetableRepositoryImpl,
    ): TimetableRepository
}
