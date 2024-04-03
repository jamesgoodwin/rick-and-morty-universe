package com.jgoodwin.myapplication.episodes

import com.jgoodwin.myapplication.episodes.data.EpisodesRemoteRepository
import com.jgoodwin.myapplication.episodes.domain.EpisodesRepository
import com.jgoodwin.myapplication.episodes.domain.GetEpisodesUseCase
import com.jgoodwin.myapplication.episodes.data.EpisodesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EpisodesModule {

    @Provides
    @Singleton
    fun provideEpisodesApiService(retrofit: Retrofit): EpisodesApiService {
        return retrofit.create(EpisodesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodesRepository(api: EpisodesApiService): EpisodesRepository {
        return EpisodesRemoteRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetEpisodesUseCase(repository: EpisodesRepository): GetEpisodesUseCase {
        return GetEpisodesUseCase(repository)
    }

}