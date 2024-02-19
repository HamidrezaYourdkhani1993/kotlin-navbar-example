// Import the ExperimentalMaterial3Api annotation
@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.week6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.week6.ui.theme.Week6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week6Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@Composable
fun MainTopBar(title: String, navController: NavController) {
    // State for handling menu expansion
    var expanded by remember { mutableStateOf(false)}
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(Icons.Filled.MoreVert,contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("info") },
                    onClick = { navController.navigate("info") })
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = { navController.navigate("Settings") })
            }
        }

    )
}

@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    // Top app bar for individual screens
    TopAppBar(
        title = {Text(title)},
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack,contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    // Scaffold for the main screen
    Scaffold (
        topBar = {MainTopBar("My App",navController)},
        content = { Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text("Content for Home screen")
        }},
    )
}
@Composable
fun InfoScreen(navController: NavController) {
    // Scaffold for the info screen
    Scaffold (
        topBar = {ScreenTopBar("Info",navController)},
        content = { Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text("Content for info screen")
        }},
    )
}
@Composable
fun SettingsScreen(navController: NavController) {
    // Scaffold for the settings screen
    Scaffold (
        topBar = {ScreenTopBar("Settings",navController)},
        content = { Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text("Content for Settings screen")
        }},
    )
}

@Composable
fun ScaffoldApp() {
    // Navigation setup with NavHost
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"
    )
    {
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}
