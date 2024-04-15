package edu.com.trailslist.compose.appcomponents.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import edu.com.trailslist.util.BottomNavigationItem

@Composable
fun BottomNavBar(navController: NavController) {
    val navItems = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Low-Laying",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "Mountains",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        )

    )
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    NavigationBar(
    ) {
        navItems.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    when (index) {
                        0 -> navController.navigate("homeScreen")
                        1 -> navController.navigate("trailsList")
                        2 -> navController.navigate("trailsList")
                    }
                },
                label = {Text(text = bottomNavigationItem.title)},
                icon = {
                    Icon(imageVector =
                    if (index == selectedItemIndex) {bottomNavigationItem.selectedIcon}
                    else bottomNavigationItem.unselectedIcon,
                        contentDescription = "Bottom navigation item")
                })
        }
    }
}