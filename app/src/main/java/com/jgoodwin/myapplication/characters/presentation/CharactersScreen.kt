package com.jgoodwin.myapplication.characters.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jgoodwin.myapplication.domain.Location
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.jgoodwin.myapplication.characters.domain.CharacterSummary
import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel.CharacterViewState
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme

@Composable
fun CharacterScreen(
    state: CharacterViewState,
    onLocationClicked: (Location) -> Unit? = {},
    onEpisodeClicked: (Int) -> Unit
) {
    when (state) {
        is CharacterViewState.Error -> {
            ListItem(leadingContent = {
                Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = "Error",
                    tint = MaterialTheme.colorScheme.primary
                )
            }, headlineContent = { Text(text = state.message) })
        }

        CharacterViewState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
                    .testTag("loading")
            )
        }

        is CharacterViewState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.results) { character ->
                    CharacterImageCard(character, onLocationClicked, onEpisodeClicked)
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EpisodeScreenErrorPreview() {
    RickAndMortyTheme {
        CharacterScreen(
            state = CharacterViewState.Error("Error occurred")
        ) {}
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EpisodeScreenLoadingPreview() {
    RickAndMortyTheme {
        CharacterScreen(
            state = CharacterViewState.Loading
        ) {}
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EpisodeScreenResultsPreview() {
    RickAndMortyTheme {
        CharacterScreen(
            state = CharacterViewState.Success(
                listOf(
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
            )
        ) {}
    }
}


