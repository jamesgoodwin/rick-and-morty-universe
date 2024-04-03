package com.jgoodwin.myapplication.locations.data

import com.jgoodwin.myapplication.locations.domain.LocationsResponse
import retrofit2.http.GET

interface LocationsApiService {

    @GET("location")
    suspend fun getLocations(): LocationsResponse

}