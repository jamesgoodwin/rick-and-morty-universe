package com.jgoodwin.myapplication.episodes.domain

class GetEpisodesUseCase(private val episodesRepository: EpisodesRepository) {

    suspend operator fun invoke(): List<Episode> {
        return episodesRepository.getEpisodes().results
    }

}
