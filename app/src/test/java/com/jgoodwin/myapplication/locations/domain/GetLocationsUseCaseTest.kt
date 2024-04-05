package com.jgoodwin.myapplication.locations.domain

import com.jgoodwin.myapplication.domain.PaginationInfo
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetLocationsUseCaseTest {

    // Mock the LocationsRepository
    private val locationsRepository: LocationsRepository = mockk()

    // Instance of the UseCase to be tested
    private lateinit var getLocationsUseCase: GetLocationsUseCase

    @Before
    fun setUp() {
        // Initialize the UseCase with the mocked repository before each test
        getLocationsUseCase = GetLocationsUseCase(locationsRepository)
    }

    @Test
    fun `invoke returns list of LocationDetail from repository`() = runTest {
        // Prepare the mock to return a specific result when getLocations is called
        val results = listOf(
            LocationDetail(
                id = 1,
                name = "Location 1",
                "Type 1",
                "Dimension 1",
                listOf("Resident 1", "Resident 2"),
                "url"
            )
        )
        coEvery { locationsRepository.getLocations() } returns LocationsResponse(
            info = PaginationInfo(1, 10, "next", "prev"),
            results = results
        )

        // Execute the use case
        val result = getLocationsUseCase.invoke()
        // Assert that the result matches the expected value
        assertEquals(results, result)
    }
}
