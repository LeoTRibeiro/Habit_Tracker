package com.leonardoethais.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leonardoethais.habittracker.navigation.AppNavHost
import com.leonardoethais.habittracker.ui.theme.HabitTrackerTheme
import com.leonardoethais.habittracker.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HabitTrackerTheme {
                val sharedViewModel: MainViewModel = viewModel()
                AppNavHost(viewModel = sharedViewModel)
            }
        }
    }
}