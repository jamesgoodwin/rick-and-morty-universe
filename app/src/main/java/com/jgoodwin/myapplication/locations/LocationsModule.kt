package com.jgoodwin.myapplication.locations

import com.jgoodwin.myapplication.locations.domain.GetLocationsUseCase
import com.jgoodwin.myapplication.locations.data.LocationsRemoteRepository
import com.jgoodwin.myapplication.locations.domain.LocationsRepository
import com.jgoodwin.myapplication.locations.data.LocationsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationsModule {

    @Provides
    @Singleton
    fun provideLocationsApiService(retrofit: Retrofit): LocationsApiService {
        return retrofit.create(LocationsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationsRepository(api: LocationsApiService): LocationsRepository {
        return LocationsRemoteRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetLocationsUseCase(repository: LocationsRepository): GetLocationsUseCase {
        return GetLocationsUseCase(repository)
    }

}