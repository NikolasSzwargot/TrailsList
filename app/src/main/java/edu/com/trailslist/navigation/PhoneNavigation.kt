package edu.com.trailslist.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.com.trailslist.compose.appcomponents.components.ListTopBar
import edu.com.trailslist.compose.appcomponents.homecomponents.HomeScreen
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailDetails
import edu.com.trailslist.compose.appcomponents.trailscomponents.TrailsList
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.util.getNavItems
import edu.com.trailslist.viewmodels.TrailViewModel
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PhoneNavigation(navController: NavHostController, viewModel: TrailViewModel){
    val scope = rememberCoroutineScope()
    val navItems = getNavItems()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val state = rememberCollapsingToolbarScaffoldState()
    val textSize = (18 + (30 - 12) * state.toolbarState.progress).sp

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
        CollapsingToolbarScaffold(
            modifier = Modifier
                .fillMaxSize(),
            scrollStrategy = if (!viewModel.detailsOpened || textSize.value == 18f) {
                ScrollStrategy.ExitUntilCollapsed
            } else {
                   ScrollStrategy.EnterAlways
                   },
            state = state,
            toolbar = {
                if (!viewModel.detailsOpened) {
                ListTopBar(drawerState = drawerState, scope = scope)
            } else {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .pin()
                    .background(color = MaterialTheme.colorScheme.primary)
                )
                
                Image(painter = (painterResource(id = viewModel.selectedImageId)),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    alpha = if (textSize.value == 18f) 0f else 1f,
                    modifier = Modifier.fillMaxWidth())
                    
                    Text(
                        text = "Szlak",
                        style = TextStyle(color = Color.White, fontSize = textSize),
                        modifier = Modifier.padding(16.dp)
                    )
            }
            }
        ) {
            NavHost(navController = navController, startDestination = "homeScreen") {
                composable("lowlying") {
                    viewModel.setToLowLying()
                    viewModel.detailsOpened = false
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
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
                    viewModel.detailsOpened = false
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
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
                    viewModel.detailsOpened = false
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pointerInput(Unit) {
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
                    viewModel.selectedImageId = trailImage
                    viewModel.detailsOpened = true
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        TrailDetails(trail, viewModel)
                    }
                }
            }
        }
    }
}