package edu.com.trailslist.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.com.trailslist.compose.appcomponents.homecomponents.HomeScreen
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailDetails
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailsList
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.util.getNavItems
import edu.com.trailslist.viewmodels.TrailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneNavigation(navController: NavHostController, viewModel: TrailViewModel){
    val scope = rememberCoroutineScope()
    val navItems = getNavItems()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                navItems.forEachIndexed { index, navigationItem ->
                    NavigationDrawerItem(
                        label = { Text(text = navigationItem.title) },
                        selected = index == viewModel.selectedItemIndex,
                        onClick = {
                            when (index) {
                                0 -> navController.navigate("homeScreen")
                                1 -> navController.navigate("lowlying")
                                2 -> navController.navigate("mountain")
                            }
                            viewModel.selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }

                        },
                        icon = {
                            Icon(
                                imageVector = if (index == viewModel.selectedItemIndex){
                                    navigationItem.selectedIcon
                                } else navigationItem.unselectedIcon,
                                contentDescription = "Menu icon")
                        })
                }
            }
        },
        drawerState = drawerState) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            topBar = { TopAppBar(title = {
                Text(text = "TrailApp")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu, contentDescription = "Navigation Menu")
                    }
                }
            ) }
        ) { values ->
            NavHost(navController = navController, startDestination = "homeScreen") {
                composable("lowlying") {
                    viewModel.setToLowLying()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                            .pointerInput(Unit){
                                detectHorizontalDragGestures { change, dragAmount ->
                                    if (dragAmount < -10) {
                                        navController.navigate("mountain")
                                        viewModel.selectedItemIndex = 2
                                    } else if (dragAmount > 10) {
                                        navController.navigate("homeScreen")
                                        viewModel.selectedItemIndex = 0
                                    }
                                }
                            }
                    ) {
                        TrailsList(navController = navController, viewModel = viewModel)
                    }
                }
                composable("mountain") {
                    viewModel.setToMountain()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                            .pointerInput(Unit){
                                detectHorizontalDragGestures { change, dragAmount ->
                                    if (dragAmount < -10) {
                                        navController.navigate("homeScreen")
                                        viewModel.selectedItemIndex = 0
                                    } else if (dragAmount > 10) {
                                        navController.navigate("lowlying")
                                        viewModel.selectedItemIndex = 1
                                    }
                                }
                            }
                    ) {
                        TrailsList(navController = navController, viewModel = viewModel)
                    }
                }
                composable("homeScreen") {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                            .pointerInput(Unit){
                                detectHorizontalDragGestures { change, dragAmount ->
                                        if (dragAmount < -10) {
                                            navController.navigate("lowlying")
                                            viewModel.selectedItemIndex = 1
                                        } else if (dragAmount > 10) {
                                            navController.navigate("mountain")
                                            viewModel.selectedItemIndex = 2
                                        }
                                    }
                            }
                    ) {
                        HomeScreen()
                    }
                }
                composable("trailDetails/{trailId}/{trailName}/{trailDescription}/{trailImage}/{trailTime}/{type}") { backStackEntry ->
                    val trailId = backStackEntry.arguments?.getString("trailId").orEmpty().toInt()
                    val trailName = backStackEntry.arguments?.getString("trailName").orEmpty()
                    val trailDescription =
                        backStackEntry.arguments?.getString("trailDescription").orEmpty()
                    val trailImage =
                        backStackEntry.arguments?.getString("trailImage").orEmpty().toInt()
                    val trailTime =
                        backStackEntry.arguments?.getString("trailTime").orEmpty().toLong()
                    val type = backStackEntry.arguments?.getString("type").orEmpty().toInt()

                    val trail = Trail(
                        trailId,
                        trailName,
                        trailDescription,
                        trailImage,
                        trailTime,
                        type
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(values)
                    ) {
                        TrailDetails(trail, viewModel)
                    }
                }
            }
        }
    }
}