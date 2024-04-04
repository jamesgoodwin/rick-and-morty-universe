package com.jgoodwin.myapplication.episodes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EpisodeScreen(episodeId: Int? = null) {
    val episodesViewModel: EpisodesViewModel = hiltViewModel()
    val state by episodesViewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.isEmpty()) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                )
            }
        }
        items(state) { episode ->
            ListItem(headlineContent = { Text(text = episode.name) },
                supportingContent = { Text(text = "${episode.episode} - ${episode.air_date} - ${episode.characters.count()} characters")})
            HorizontalDivider()
            /*Text(
                color = MaterialTheme.colorScheme.primary,
                text = episode.name,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
            Text(color = MaterialTheme.colorScheme.primary, text = "Airing date: ${episode.air_date}")
            Text(color = MaterialTheme.colorScheme.primary, text = "Episode: ${episode.episode}")
            Text(color = MaterialTheme.colorScheme.primary, text = "Characters: ${episode.characters.count()}")*/

        }
    }

}