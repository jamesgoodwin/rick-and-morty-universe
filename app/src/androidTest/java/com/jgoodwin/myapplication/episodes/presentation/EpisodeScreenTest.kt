package com.jgoodwin.myapplication.episodes.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.episodes.presentation.EpisodesViewModel.EpisodesViewState
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class EpisodesScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun episodesScreenLoadingState_displaysCircularProgressIndicator() {
        setContent(EpisodesViewState.Loading)
        composeTestRule.onNodeWithTag("loading", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun episodesScreenErrorState_displaysErrorMessage() {
        val errorMessage = "Error occurred"
        setContent(EpisodesViewState.Error(errorMessage))

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun episodesScreenSuccessState_displaysEpisodes() {
        val Episodess = listOf(
            Episode(1,"Episode Name","AirDate","Episode", "1 Apr 2024", emptyList(), "url"),
        )
        setContent(EpisodesViewState.Success(Episodess))
        composeTestRule.onNodeWithText("Episode Name").assertIsDisplayed()
    }

    private fun setContent(state: EpisodesViewState) {
        composeTestRule.setContent {
            RickAndMortyTheme {
                EpisodesScreen(state = state) {}
            }
        }
    }

}
