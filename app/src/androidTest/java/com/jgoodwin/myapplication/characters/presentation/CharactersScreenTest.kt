package com.jgoodwin.myapplication.characters.presentation

import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel.CharacterViewState

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.jgoodwin.myapplication.characters.domain.CharacterSummary
import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme
import org.junit.Rule
import org.junit.Test

class CharacterScreenTest {

    @get:Rule val composeTestRule = createComposeRule()

    @Test
    fun characterScreenLoadingState_displaysCircularProgressIndicator() {
        setContent(CharacterViewState.Loading)
        composeTestRule.onNodeWithTag("loading", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun characterScreenErrorState_displaysErrorMessage() {
        val errorMessage = "Error occurred"
        setContent(CharacterViewState.Error(errorMessage))

        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

    @Test
    fun characterScreenSuccessState_displaysCharacterItems() {
        val characters = listOf(
            CharacterSummary(
                "Rick Sanchez",
                "Alive",
                "Human",
                "Type",
                "Male",
                Location("Citadel of Ricks", ""),
                Location("Earth", ""),
                "image url",
                "Citadel of Ricks",
                1
            )
        )
        setContent(CharacterViewState.Success(characters))
        composeTestRule.onNodeWithText("Rick Sanchez").assertIsDisplayed()
    }

    private fun setContent(state: CharacterViewState) {
        composeTestRule.setContent {
            RickAndMortyTheme {
                CharacterScreen(state = state) {}
            }
        }
    }

}
