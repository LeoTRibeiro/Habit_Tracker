package com.leonardoethais.habittracker.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.leonardoethais.habittracker.navigation.Destination
import com.leonardoethais.habittracker.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onLogout: () -> Unit
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination?.route?.contains("Home") == true,
                    onClick = {
                        bottomNavController.navigate(Destination.Home) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Ir para aba início"
                        )
                    },
                    label = { Text("Início") }
                )

                NavigationBarItem(
                    selected = currentDestination?.route?.contains("RecipeList") == true || currentDestination?.route?.contains("RecipeDetail") == true,
                    onClick = {
                        bottomNavController.navigate(Destination.RecipeList) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.List,
                            contentDescription = "Ir para aba receitas"
                        )
                    },
                    label = { Text("Receitas") }
                )

                NavigationBarItem(
                    selected = currentDestination?.route?.contains("Profile") == true,
                    onClick = {
                        bottomNavController.navigate(Destination.Profile) {
                            popUpTo(bottomNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Ir para aba perfil"
                        )
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Destination.Home,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Destination.Home> {
                HomeScreen()
            }

            composable<Destination.RecipeList> {
                val recipes by viewModel.recipes.collectAsState()
                RecipeListScreen(
                    recipes = recipes,
                    onRecipeClick = { id ->
                        bottomNavController.navigate(Destination.RecipeDetail(id = id))
                    },
                    onClearClick = {
                        viewModel.clearRecipes()
                    }
                )
            }

            composable<Destination.RecipeDetail> { backStackEntry ->
                val detailRoute: Destination.RecipeDetail = backStackEntry.toRoute()
                val recipe = viewModel.getRecipeById(detailRoute.id)
                RecipeDetailScreen(
                    recipe = recipe,
                    onBackClick = {
                        bottomNavController.popBackStack()
                    }
                )
            }

            composable<Destination.Profile> {
                ProfileScreen(
                    onLogoutClick = {
                        viewModel.logout()
                        onLogout()
                    }
                )
            }
        }
    }
}