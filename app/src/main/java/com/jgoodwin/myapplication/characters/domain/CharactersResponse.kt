package com.jgoodwin.myapplication.characters.domain

import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.domain.PaginationInfo

data class CharactersResponse(
    val info: PaginationInfo,
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val results: List<RickAndMortyCharacter>
)