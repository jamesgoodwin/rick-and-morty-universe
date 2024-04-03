package com.jgoodwin.myapplication.episodes.data

import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.episodes.domain.EpisodesRepository
import com.jgoodwin.myapplication.episodes.domain.EpisodesResponse
import retrofit2.Call

class EpisodesRemoteRepository(private val episodesApiService: EpisodesApiService) : EpisodesRepository {
    override suspend fun getEpisodes(): EpisodesResponse {
        return episodesApiService.getEpisodes()
    }

    override suspend fun getEpisodeByUrl(url: String): Episode {
        return episodesApiService.getEpisodeByUrl(url)
    }
}