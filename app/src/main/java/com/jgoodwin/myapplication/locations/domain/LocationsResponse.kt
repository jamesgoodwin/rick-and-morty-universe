package com.jgoodwin.myapplication.locations.domain

import com.jgoodwin.myapplication.domain.PaginationInfo

data class LocationsResponse(val info: PaginationInfo, val results: List<LocationDetail>)