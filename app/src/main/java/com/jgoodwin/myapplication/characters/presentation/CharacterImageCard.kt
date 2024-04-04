package com.jgoodwin.myapplication.characters.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.jgoodwin.myapplication.characters.domain.CharacterSummary
import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.ui.theme.MyApplicationTheme

@Composable
fun CharacterImageCard(
    character: CharacterSummary,
    onLocationClicked: (Location) -> Unit? = {},
    onEpisodeClicked:(Int) -> Unit? = {}
) {
    val linkTextStyle = TextStyle(
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold
    )

    Card(
        modifier = Modifier.fillMaxWidth()
    )
    {
        Row {
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                painter = rememberAsyncImagePainter(model = character.image),
                contentDescription = "Image"
            )
            Column(
                modifier = Modifier.padding(8.dp)
            )
            {
                Text(
                    text = character.name,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                )
                Row {
                    val statusIndicatorColour = when (character.status) {
                        "Alive" -> Color.Green
                        "Dead" -> Color.Red
                        else -> Color.Gray
                    }
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .align(Alignment.CenterVertically)
                            .background(statusIndicatorColour)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "${character.status} - ${character.species}"
                    )
                }

                Text(
                    modifier = Modifier.clickable(onClick = { onLocationClicked(character.location) }),
                    style = linkTextStyle,
                    text = "Location: ${character.location.name}"
                )

                Text(
                    modifier = Modifier.clickable(onClick = { onEpisodeClicked(character.episodeId) }),
                    style = linkTextStyle,
                    text = "First seen in: ${character.episode}"
                )

            }

        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CharactersScreenPreview() {
    MyApplicationTheme {
        CharacterImageCard(
            CharacterSummary(
                name = "Rick",
                status = "alive",
                gender = "male",
                species = "human",
                type = "human",
                origin = Location(name = "Earth", url = ""),
                location = Location(name = "Earth", url = ""),
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                episode = "Close Rick-counters of the Rick Kind",
                episodeId = 1
            )
        )
    }
}