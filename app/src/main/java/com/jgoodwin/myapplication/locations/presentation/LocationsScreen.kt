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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jgoodwin.myapplication.R

@Composable
fun LocationsScreen(
    state: LocationsViewModel.LocationsViewState,
    onLocationResidentsClicked: (Int) -> Unit,
    onLocationTypeClicked: (String) -> Unit,
    onLocationDimensionClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        when (state) {
            is LocationsViewModel.LocationsViewState.Error -> TODO()
            LocationsViewModel.LocationsViewState.Loading -> {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
            is LocationsViewModel.LocationsViewState.Success -> {
                items(state.results) { location ->
                    ListItem(
                        headlineContent = { Text(text = location.name) },
                        supportingContent = {
                            Row {
                                val separator = stringResource(id = R.string.dash_separator)
                                ClickableText(
                                    text = location.type,
                                    onClick = { onLocationTypeClicked(location.type) })
                                Text(text = separator)
                                ClickableText(
                                    text = location.dimension,
                                    onClick = { onLocationDimensionClicked(location.dimension) })
                                Text(text = separator)
                                ClickableText(
                                    text = "${location.residents.count()} ${stringResource(id = R.string.residents)}",
                                    onClick = { onLocationResidentsClicked(location.id) })
                            }
                        },
                    )
                    HorizontalDivider()
                }
            }
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