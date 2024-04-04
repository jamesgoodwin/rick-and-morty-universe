package com.jgoodwin.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import com.jgoodwin.myapplication.NavigationItem.Companion.EPISODE_ID_NAVIGATION_PARAM
import com.jgoodwin.myapplication.characters.presentation.CharacterScreen
import com.jgoodwin.myapplication.characters.presentation.CharacterViewModel
import com.jgoodwin.myapplication.domain.Location
import com.jgoodwin.myapplication.episodes.presentation.EpisodeScreen
import com.jgoodwin.myapplication.locations.presentation.LocationsScreen

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
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

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    data object Characters : NavigationItem("characters", Icons.Filled.Face, "Characters")
    data object Episodes :
        NavigationItem("episodes?episode={episodeId}", Icons.Filled.List, "Episodes")

    data object Locations : NavigationItem("locations", Icons.Filled.Place, "Locations")

    companion object {
        const val EPISODE_ID_NAVIGATION_PARAM = "{episodeId}"
    }

}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Characters.route) {
        composable(NavigationItem.Characters.route) {
            val characterViewModel: CharacterViewModel = hiltViewModel()
            val state by characterViewModel.state.collectAsStateWithLifecycle()
            CharacterScreen(
                state,
                { location -> navigateLocation(location, navController) },
                { episodeId -> navigateEpisode(episodeId, navController) })
        }
        composable(NavigationItem.Episodes.route) {
            EpisodeScreen(onEpisodeCharactersClicked = {})
        }
        composable(NavigationItem.Locations.route) {
            LocationsScreen({}, {}, {})
        }
    }
}

fun navigateEpisode(episodeId: Int, navController: NavHostController) {
    navController.navigate(
        NavigationItem.Episodes.route.replace(
            EPISODE_ID_NAVIGATION_PARAM,
            episodeId.toString()
        )
    ) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}

fun navigateLocation(location: Location, navController: NavHostController) {
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
    NavigationBar(
//        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                label = { Text(text = item.title) },
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
