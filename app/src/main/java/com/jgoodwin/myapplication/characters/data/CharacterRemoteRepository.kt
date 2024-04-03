package com.jgoodwin.myapplication.characters.data

import com.jgoodwin.myapplication.characters.domain.CharactersResponse
import com.jgoodwin.myapplication.characters.domain.CharacterRepository

class CharacterRemoteRepository(private val characterApiService: CharacterApiService) : CharacterRepository {
    override suspend fun getCharacters(): CharactersResponse {
        return characterApiService.getCharacters()
    }
}