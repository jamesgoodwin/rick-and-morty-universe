package com.jgoodwin.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jgoodwin.myapplication.characters.presentation.CharacterScreen
import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel
import com.jgoodwin.myapplication.episodes.presentation.EpisodesScreen
import com.jgoodwin.myapplication.episodes.presentation.EpisodesViewModel
import com.jgoodwin.myapplication.locations.presentation.LocationsScreen
import com.jgoodwin.myapplication.locations.presentation.LocationsViewModel
import com.jgoodwin.myapplication.ui.theme.RickAndMortyTheme

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                MainScreen()
            }
        }
    }
}

val items = listOf(
    NavigationItem.Characters,
    NavigationItem.Episodes,
    NavigationItem.Locations
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController = navController)
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

sealed class NavigationItem(val route: String, val icon: ImageVector, @StringRes val title: Int) {
    data object Characters : NavigationItem("characters", Icons.Filled.Face, R.string.characters)
    data object Episodes : NavigationItem("episodes", Icons.Filled.Tv, R.string.episodes_title)
    data object Locations : NavigationItem("locations", Icons.Filled.Public, R.string.locations_title)
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Characters.route) {
        composable(NavigationItem.Characters.route) {
            val characterViewModel: CharacterViewModel = hiltViewModel()
            val state by characterViewModel.state.collectAsStateWithLifecycle()
            CharacterScreen(
                state,
                { location -> navigateLocation(navController) },
                { episodeId -> navigateEpisode(navController) })
        }
        composable(NavigationItem.Episodes.route) {
            val episodesViewModel: EpisodesViewModel = hiltViewModel()
            val state by episodesViewModel.state.collectAsStateWithLifecycle()
            EpisodesScreen(state, onEpisodeCharactersClicked = {})
        }
        composable(NavigationItem.Locations.route) {
            val locationViewModel: LocationsViewModel = hiltViewModel()
            val state by locationViewModel.state.collectAsState()
            LocationsScreen(state, {}, {}, {})
        }
    }
}

fun navigateEpisode(navController: NavHostController) {
    navController.navigate(NavigationItem.Episodes.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}

fun navigateLocation(navController: NavHostController) {
    navController.navigate(NavigationItem.Locations.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = stringResource(item.title),
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = { Text(text = stringResource(item.title)) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
