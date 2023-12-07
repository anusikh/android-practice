package com.example.paging_jetpack.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import com.example.paging_jetpack.model.BottomNavItem

object Constants {
    const val BASE_URL = "https://newsapi.org/v2"
    const val API_KEY = "9908a284d11342b9819c623043e14fdc"

    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Paging",
            icon = Icons.Filled.Check,
            route = "paging"
        ),
        BottomNavItem(
            label = "Non Paging",
            icon = Icons.Filled.Clear,
            route = "nonPaging"
        ),
    )
}