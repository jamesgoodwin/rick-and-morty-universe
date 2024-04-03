package com.jgoodwin.myapplication.episodes.domain

interface EpisodesRepository {
    suspend fun getEpisodes(): EpisodesResponse
    suspend fun getEpisodeByUrl(url: String): Episode
}
