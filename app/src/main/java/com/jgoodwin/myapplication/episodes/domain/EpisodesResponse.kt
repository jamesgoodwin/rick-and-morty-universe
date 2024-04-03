package com.jgoodwin.myapplication.episodes.domain

import com.jgoodwin.myapplication.domain.PaginationInfo

data class EpisodesResponse(val info: PaginationInfo, val results: List<Episode>)