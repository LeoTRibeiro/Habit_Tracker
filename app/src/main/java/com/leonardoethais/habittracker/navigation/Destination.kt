package com.leonardoethais.habittracker.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object Splash : Destination

    @Serializable
    data object Login : Destination

    @Serializable
    data object Main : Destination

    @Serializable
    data object Home : Destination

    @Serializable
    data object RecipeList : Destination

    @Serializable
    data class RecipeDetail(val id: Int) : Destination

    @Serializable
    data object Profile : Destination
}