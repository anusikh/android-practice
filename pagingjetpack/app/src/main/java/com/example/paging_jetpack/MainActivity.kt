package com.example.paging_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.paging_jetpack.ui.screen.nonpaging.NonPagingLibraryScreen
import com.example.paging_jetpack.ui.screen.paging.PagingLibraryScreen
import com.example.paging_jetpack.ui.theme.PagingjetpackTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PagingjetpackTheme {
                navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavHostContainer(
    navController: NavHostController, padding: PaddingValues
) {
    NavHost(navController = navController,
        startDestination = "paging",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable("paging") {
                PagingLibraryScreen()
            }
            composable("nonPaging") {
                NonPagingLibraryScreen()
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    var selectedItem by remember { mutableStateOf(0) }

    Scaffold(bottomBar = {
        NavigationBar {
            NavigationBarItem(selected = selectedItem === 0, onClick = {
                selectedItem = 0
                navController.navigate("paging")
            }, icon = {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = "")
            }, label = {
                Text(text = "Paging")
            })
            NavigationBarItem(selected = selectedItem === 1, onClick = {
                selectedItem = 1
                navController.navigate("nonPaging")
            }, icon = {
                Icon(imageVector = Icons.Rounded.Clear, contentDescription = "")
            }, label = {
                Text(text = "Non Paging")
            })
        }
    }, content = { paddingValues ->
        NavHostContainer(navController = navController, padding = paddingValues)
    })
}