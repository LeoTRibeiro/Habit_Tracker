package com.leonardoethais.habittracker.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leonardoethais.habittracker.ui.screens.LoginScreen
import com.leonardoethais.habittracker.ui.screens.MainScreen
import com.leonardoethais.habittracker.ui.screens.SplashScreen
import com.leonardoethais.habittracker.ui.viewmodel.MainViewModel

@Composable
fun AppNavHost(
    viewModel: MainViewModel
) {
    val rootNavController = rememberNavController()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    NavHost(
        navController = rootNavController,
        startDestination = Destination.Splash
    ) {
        composable<Destination.Splash> {
            SplashScreen(
                isLoggedIn = isLoggedIn,
                onDecisionMade = { logged ->
                    val destination = if (logged) Destination.Main else Destination.Login
                    rootNavController.navigate(destination) {
                        popUpTo<Destination.Splash> { inclusive = true }
                    }
                }
            )
        }

        composable<Destination.Login> {
            LoginScreen(
                onLoginClick = {
                    viewModel.login()
                    rootNavController.navigate(Destination.Main) {
                        popUpTo<Destination.Login> { inclusive = true }
                    }
                }
            )
        }

        composable<Destination.Main> {
            MainScreen(
                viewModel = viewModel,
                onLogout = {
                    rootNavController.navigate(Destination.Login) {
                        popUpTo<Destination.Main> { inclusive = true }
                    }
                }
            )
        }
    }
}