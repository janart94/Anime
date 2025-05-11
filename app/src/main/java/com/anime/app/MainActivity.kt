package com.anime.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anime.app.ui.detail.AnimeDetailScreen
import com.anime.app.ui.home.AnimeListScreen
import com.anime.app.ui.theme.AnimeTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeTheme {
                val navController = rememberNavController()
                val navBackStackEntry = navController.currentBackStackEntryAsState().value
                val currentRoute = navBackStackEntry?.destination?.route
                var currentAnimeTitle by rememberSaveable { mutableStateOf<String?>(null) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        when {
                            currentRoute == "home" -> {
                                TopAppBar(
                                    title = { Text(stringResource(R.string.anime_app)) }
                                )
                            }

                            currentRoute?.startsWith("detail/") == true -> {
                                TopAppBar(
                                    title = {
                                        Text(
                                            text = currentAnimeTitle ?: "",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }, navigationIcon = {
                                        IconButton(onClick = { navController.popBackStack() }) {
                                            Icon(
                                                Icons.Default.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    AnimeApp(
                        navController,
                        modifier = Modifier.padding(innerPadding),
                        onAnimeTitleChange = { title -> currentAnimeTitle = title }
                    )
                }
            }
        }
    }
}

@Composable
fun AnimeApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onAnimeTitleChange: (String?) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            onAnimeTitleChange("")
            AnimeListScreen(navController)
        }
        composable("detail/{animeId}") { backStack ->
            val id = backStack.arguments?.getString("animeId")?.toIntOrNull() ?: return@composable
            AnimeDetailScreen(animeId = id) { title ->
                onAnimeTitleChange(title)
            }
        }
    }
}