package com.jgoodwin.myapplication.episodes.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jgoodwin.myapplication.R
import com.jgoodwin.myapplication.episodes.domain.Episode
import com.jgoodwin.myapplication.episodes.presentation.EpisodesViewModel.EpisodesViewState
import com.jgoodwin.myapplication.locations.presentation.ClickableText
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme

@Composable
fun EpisodeScreen(state: EpisodesViewState, onEpisodeCharactersClicked: (Int) -> Unit) {

    LazyColumn {

        when (state) {
            is EpisodesViewState.Error -> TODO()
            is EpisodesViewState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }

            is EpisodesViewState.Success -> {
                items(state.results) { episode ->
                    ListItem(headlineContent = { Text(text = episode.name) },
                        supportingContent = {
                            Row {
                                Text(text = "${episode.episode} - ${episode.air_date} - ")

                                ClickableText(
                                    text = "${episode.characters.count()} ${stringResource(id = R.string.characters)}",
                                    onClick = { onEpisodeCharactersClicked(episode.id) })
                            }
                        })
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(name = "Dark Mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun EpisodeScreenResultsPreview() {
    RickAndMortyTheme {
        EpisodeScreen(
            state = EpisodesViewState.Success(
                listOf(
                    Episode(
                        1,
                        "Lawnmower Dog",
                        "December 9, 2013",
                        "S01E03",
                        "2013-12-01 12:00:00",
                        emptyList(),
                        ""
                    )
                )
            )
        ) {}
    }
}