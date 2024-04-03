package com.jgoodwin.myapplication.episodes.data

import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.episodes.domain.EpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface EpisodesApiService {

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse
    @GET
    suspend fun getEpisodeByUrl(@Url url: String): Episode

}