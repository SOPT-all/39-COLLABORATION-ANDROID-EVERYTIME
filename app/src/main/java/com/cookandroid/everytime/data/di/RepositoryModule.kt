package com.cookandroid.mjhackathon.data.di

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
        impl: TimetableRepositoryImpl,
    ): TimetableRepository

    @Binds
    abstract fun bindReviewRepository(
        impl: PostDailyRepositoryImpl,
    ): PostDailyRepository

    @Binds
    abstract fun bindTimetableRepository(
        impl: TimetableRepositoryImpl,
    ): TimetableRepository
}
