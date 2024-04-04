package com.jgoodwin.myapplication.locations.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationsScreen(
    onLocationResidentsClicked: (Int) -> Unit,
    onLocationTypeClicked: (String) -> Unit,
    onLocationDimensionClicked: (String) -> Unit
) {
    val locationViewModel: LocationViewModel = hiltViewModel()
    val state by locationViewModel.state.collectAsState()

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
        items(state) { location ->
            ListItem(
                headlineContent = { Text(text = location.name) },
                supportingContent = {
                    Row {
                        ClickableText(
                            text = location.type,
                            onClick = { onLocationTypeClicked(location.type) })
                        Text(text = " - ")
                        ClickableText(text = location.dimension, onClick = { onLocationDimensionClicked(location.dimension)})
                        Text(text = " - ")
                        ClickableText(
                            text = "${location.residents.count()} residents",
                            onClick = { onLocationResidentsClicked(location.id) })
                    }
                },
            )
            HorizontalDivider()
        }
    }

}

@Composable
fun ClickableText(text: String, onClick: () -> Unit) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.clickable(onClick = onClick)
    )
}