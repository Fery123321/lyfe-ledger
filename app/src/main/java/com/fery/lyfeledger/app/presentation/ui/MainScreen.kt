package com.fery.lyfeledger.app.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.fery.lyfeledger.app.presentation.BottomNavigationBar
import com.fery.lyfeledger.app.presentation.NavigationRoute

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->

        val graph = navController.createGraph(startDestination = NavigationRoute.Task) {
            composable<NavigationRoute.Task> {

            }

            composable<NavigationRoute.Habit> {

            }

            composable<NavigationRoute.Pomodoro> {

            }

            composable<NavigationRoute.Settings> {

            }
        }

        NavHost(
            navController = navController,
            graph = graph,
            modifier = Modifier.padding(paddingValues = paddingValues),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}



