package com.cookandroid.mjhackathon.data.di

import com.cookandroid.mjhackathon.data.repository.home.LecturelistRepository
import com.cookandroid.mjhackathon.data.repository.home.LecturelistRepositoryImpl
import com.cookandroid.mjhackathon.data.repository.home.ReviewRepository
import com.cookandroid.mjhackathon.data.repository.home.ReviewRepositoryImpl
import com.cookandroid.mjhackathon.data.repository.home.TimetableRepository
import com.cookandroid.mjhackathon.data.repository.home.TimetableRepositoryImpl
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
