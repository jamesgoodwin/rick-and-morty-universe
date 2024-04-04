package com.jgoodwin.myapplication.characters.domain

import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.episodes.domain.EpisodesRepository

data class CharacterSummary(
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Location,
    val location: Location,
    val image: String,
    val episode: String,
    val episodeId: Int
) {
}

class GetCharactersUseCase(
    private val characterRepository: CharacterRepository,
    private val episodeRepository: EpisodesRepository
) {

    suspend operator fun invoke(): List<CharacterSummary> {
        val characters = characterRepository.getCharacters().results

        return characters.map {
            val episode = episodeRepository.getEpisodeByUrl(it.episode.first())
            CharacterSummary(
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                origin = it.origin,
                location = it.location,
                image = it.image,
                episode = episode.name,
                episodeId = episode.id
            )
        }
    }

}