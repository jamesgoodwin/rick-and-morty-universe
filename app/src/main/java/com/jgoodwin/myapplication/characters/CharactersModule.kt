package com.jgoodwin.myapplication.characters

import com.jgoodwin.myapplication.characters.data.CharacterApiService
import com.jgoodwin.myapplication.characters.data.CharacterRemoteRepository
import com.jgoodwin.myapplication.characters.domain.CharacterRepository
import com.jgoodwin.myapplication.characters.domain.GetCharactersUseCase
import com.jgoodwin.myapplication.episodes.domain.EpisodesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersModule {

    @Provides
    @Singleton
    fun provideCharacterApiService(retrofit: Retrofit): CharacterApiService {
        return retrofit.create(CharacterApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(api: CharacterApiService): CharacterRepository {
        return CharacterRemoteRepository(api)
    }

    @Provides
    @Singleton
    fun provideGetCharacterUseCase(
        characterRepository: CharacterRepository,
        episodesRepository: EpisodesRepository
    ): GetCharactersUseCase {
        return GetCharactersUseCase(characterRepository, episodesRepository)
    }

}