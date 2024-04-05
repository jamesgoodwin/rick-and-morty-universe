package com.jgoodwin.myapplication.locations.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jgoodwin.myapplication.locations.domain.LocationDetail
import com.jgoodwin.myapplication.locations.presentation.LocationsViewModel.LocationsViewState
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class LocationsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun locationsScreenLoadingState_displaysCircularProgressIndicator() {
        setContent(LocationsViewState.Loading)
        composeTestRule.onNodeWithTag("loading", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun locationsScreenErrorState_displaysErrorMessage() {
        val errorMessage = "Error occurred"
        setContent(LocationsViewState.Error(errorMessage))

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun locationsScreenSuccessState_displaysLocations() {
        val locations = listOf(
            LocationDetail(
                1, "Earth", "Planet", "C-136 Dimension", listOf("Rick"), ""
            )
        )
        setContent(LocationsViewState.Success(locations))
        composeTestRule.onNodeWithText("Earth").assertIsDisplayed()
    }

    private fun setContent(state: LocationsViewState) {
        composeTestRule.setContent {
            RickAndMortyTheme {
                LocationsScreen(state = state, {}, {}) {}
            }
        }
    }

}
