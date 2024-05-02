package edu.com.trailslist.compose.appcomponents.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import edu.com.trailslist.viewmodels.TrailViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(viewModel: TrailViewModel, drawerState: DrawerState, scope: CoroutineScope) {
//        TopAppBar(title = {
//            Text(text = "TrailApp")
//        },
//
//            navigationIcon = {
//                IconButton(onClick = {
//                    scope.launch {
//                        drawerState.open()
//                    }
//                }) {
//                    Icon(
//                        imageVector = Icons.Default.Menu, contentDescription = "Navigation Menu"
//                    )
//                }
//            }
//        )
}