package com.jgoodwin.myapplication.characters.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jgoodwin.myapplication.domain.Location

@Composable
fun CharacterScreen(
    state: CharacterViewModel.CharacterViewState,
    onLocationClicked: (Location) -> Unit? = {},
    onEpisodeClicked: (Int) -> Unit
) {
    when (state) {
        CharacterViewModel.CharacterViewState.Loading -> {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        is CharacterViewModel.CharacterViewState.Success -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp), modifier = Modifier.fillMaxSize()
            ) {
                items(state.results) { character ->
                    CharacterImageCard(character, onLocationClicked, onEpisodeClicked)
                }
            }
        }

        is CharacterViewModel.CharacterViewState.Error -> {
            Text(text = state.message)
        }
    }
}