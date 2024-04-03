package com.jgoodwin.myapplication.locations.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationsScreen() {
    val locationViewModel: LocationViewModel = hiltViewModel()
    val state by locationViewModel.state.collectAsState()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
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
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = location.name,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
            Text(color = MaterialTheme.colorScheme.primary, text = "Type: ${location.type}")
            Text(color = MaterialTheme.colorScheme.primary, text = "Dimension: ${location.dimension}")
            Text(color = MaterialTheme.colorScheme.primary, text = "Residents: ${location.residents.count()}")
        }
    }

}