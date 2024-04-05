package com.jgoodwin.myapplication.characters.domain

import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.domain.PaginationInfo
import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.episodes.domain.EpisodesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    // Mock the LocationsRepository
    private val characterRepository: CharacterRepository = mockk()
    private val episodesRepository: EpisodesRepository = mockk()

    // Instance of the UseCase to be tested
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        // Initialize the UseCase with the mocked repository before each test
        getCharactersUseCase = GetCharactersUseCase(characterRepository, episodesRepository)
    }

    @Test
    fun `invoke returns list of LocationDetail from repository`() = runTest {
        // Prepare the mock to return a specific result when getLocations is called
        val episodeUrl = "http://episodeurl.com"
        val results = listOf(
            RickAndMortyCharacter(
                id = 1,
                name = "Character Name",
                status = "Alive",
                species = "Species 1",
                type = "Human",
                gender = "Male",
                origin = Location("Earth", "url"),
                location = Location("Mars", "url"),
                image = "image",
                episode = listOf(episodeUrl)
            )
        )

        coEvery { episodesRepository.getEpisodeByUrl(episodeUrl) } returns Episode(
            1,
            "Pilot",
            "Dec 1, 2013",
            "S01E01",
            "created",
            emptyList(),
            "url"
        )

        coEvery { characterRepository.getCharacters() } returns CharactersResponse(
            info = PaginationInfo(1, 10, "next", "prev"),
            results = results
        )

        // Execute the use case
        val result = getCharactersUseCase.invoke()
        // Assert that the result matches the expected value
        val expectedResult = listOf(
            CharacterSummary(
                name = "Character Name",
                status = "Alive",
                species = "Species 1",
                type = "Human",
                gender = "Male",
                origin = Location(name = "Earth", url = "url"),
                location = Location(name = "Mars", url = "url"),
                image = "image",
                episode = "Pilot",
                episodeId = 1
            )
        )

        assertEquals(expectedResult, result)
    }
}
