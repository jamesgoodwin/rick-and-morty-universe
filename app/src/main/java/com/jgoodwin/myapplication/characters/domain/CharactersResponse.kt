package com.jgoodwin.myapplication.characters.domain

import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.domain.PaginationInfo

data class CharactersResponse(
    val info: PaginationInfo,
    val results: List<RickAndMortyCharacter>
)