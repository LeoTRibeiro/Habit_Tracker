package com.leonardoethais.habittracker.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val ingredients: List<String>
)

class MainViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _recipes = MutableStateFlow<List<Recipe>>(
        listOf(
            Recipe(1, "Bolo de Cenoura", "Bolo fofinho com cobertura de chocolate", listOf("Cenoura", "Farinha", "Ovos", "Chocolate")),
            Recipe(2, "Lasanha à Bolonhesa", "Lasanha tradicional com molho de carne", listOf("Massa", "Carne moída", "Queijo", "Molho de tomate")),
            Recipe(3, "Salada Caesar", "Salada fresca com molho especial e croutons", listOf("Alface", "Frango", "Croutons", "Molho Caesar"))
        )
    )
    val recipes: StateFlow<List<Recipe>> = _recipes

    fun login() {
        _isLoggedIn.value = true
    }

    fun logout() {
        _isLoggedIn.value = false
    }

    fun clearRecipes() {
        _recipes.value = emptyList()
    }

    fun getRecipeById(id: Int): Recipe? {
        return _recipes.value.find { it.id == id }
    }
}