package com.jgoodwin.myapplication.characters.data

import com.jgoodwin.myapplication.characters.domain.CharactersResponse
import retrofit2.http.GET

interface CharacterApiService {

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

}
