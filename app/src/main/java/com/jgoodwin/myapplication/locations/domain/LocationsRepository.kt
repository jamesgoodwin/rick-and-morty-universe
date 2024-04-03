package com.jgoodwin.myapplication.locations.domain

interface LocationsRepository {
    suspend fun getLocations(): LocationsResponse
}
