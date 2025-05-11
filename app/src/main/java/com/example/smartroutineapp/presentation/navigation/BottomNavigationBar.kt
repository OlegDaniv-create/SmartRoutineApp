package com.example.smartroutineapp.presentation.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.navigation.NavigableGraph.BottomNavItem
import com.example.smartroutineapp.presentation.theme.PrimaryBlue

@Composable
fun BottomNavigationBar(
    currentScreen: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .padding(start = 74.dp, end = 74.dp, bottom = 24.dp)
            .clip(CircleShape),
        containerColor = PrimaryBlue.copy(alpha = 0.3f),
        windowInsets = WindowInsets(left = 18.dp, right = 18.dp)
    ) {
        BottomNavItem.toList.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        modifier = Modifier.padding(vertical = 14.dp),
                        painter = painterResource(id = item.icon),
                        contentDescription = item.toString()
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.White,
                    selectedIndicatorColor = PrimaryBlue,
                    disabledIconColor = Color.White,
                    disabledTextColor = Color.White
                ),
                selected = item == currentScreen,
                onClick = { onItemSelected(item) },
            )
        }
    }
}

private val BottomNavItem.icon
    get() = when (this) {
        is BottomNavItem.Home -> R.drawable.ic_bottom_nav_home
        is BottomNavItem.List -> R.drawable.ic_bottom_nav_list_check
        is BottomNavItem.User -> R.drawable.ic_bottom_nav_user
    }

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(
        currentScreen = BottomNavItem.User,
    ) {}
}
