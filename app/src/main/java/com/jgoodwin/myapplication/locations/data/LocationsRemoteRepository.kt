package com.jgoodwin.myapplication.locations.data

import com.jgoodwin.myapplication.locations.domain.LocationsRepository
import com.jgoodwin.myapplication.locations.domain.LocationsResponse

class LocationsRemoteRepository(val locationsApiService: LocationsApiService) :
    LocationsRepository {
    override suspend fun getLocations(): LocationsResponse {
        return locationsApiService.getLocations()
    }
}