package com.jgoodwin.myapplication.locations.domain

class GetLocationsUseCase(private val locationsRepository: LocationsRepository) {

    suspend operator fun invoke(): List<LocationDetail> {
        return locationsRepository.getLocations().results
    }

}
