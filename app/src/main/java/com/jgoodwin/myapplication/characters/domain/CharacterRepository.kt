package com.jgoodwin.myapplication.characters.domain

interface CharacterRepository {

    suspend fun getCharacters(): CharactersResponse

}