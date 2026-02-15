package com.example.testtask

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testtask.ui.themeAndScreens.Screens.FirstScreen
import com.example.testtask.ui.themeAndScreens.Screens.FirstScreenViewModel
import com.example.testtask.ui.themeAndScreens.TestTaskTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testtask.ui.themeAndScreens.Screens.DetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestTaskTheme {
                val viewModel: FirstScreenViewModel = viewModel(factory = FirstScreenViewModel.Factory)
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "list"
                ) {
                    composable("list") {
                        FirstScreen(
                            gifUIState = viewModel.gifUiState,
                            retryAction = { viewModel.getGif() },
                            onGifClick = { gif ->
                                navController.navigate("detail/${Uri.encode(gif.gifSrc)}")
                            }
                        )
                    }
                    composable(
                        route = "detail/{gifUrl}",
                        arguments = listOf(navArgument("gifUrl") {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val gifUrl = backStackEntry.arguments?.getString("gifUrl")?.let { Uri.decode(it) } ?: ""
                        DetailsScreen(gifUrl)
                    }
                }
            }
        }
    }
}

