package com.jgoodwin.myapplication.episodes.domain

data class Episode(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val created: String,
    val characters: List<String>,
    val url: String
)