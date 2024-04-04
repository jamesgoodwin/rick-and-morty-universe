package com.jgoodwin.myapplication.episodes.presentation

import androidx.compose.foundation.layout.Row
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
import com.jgoodwin.myapplication.locations.presentation.ClickableText

@Composable
fun EpisodeScreen(episodeId: Int? = null, onEpisodeCharactersClicked: (Int) -> Unit) {
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
                supportingContent = {
                    Row {
                        Text(text = "${episode.episode} - ${episode.air_date} - ")
                        ClickableText(text = "${episode.characters.count()} characters", onClick = { onEpisodeCharactersClicked(episode.id) })
                    }
                })
            HorizontalDivider()

        }
    }

}