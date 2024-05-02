package edu.com.trailslist.compose.appcomponents.homecomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo

@Composable
fun HomeScreen() {
    val screenInfoData = screenInfo()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Phone) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Logo(Modifier)
                }
                item {
                    ApplicationDescription(Modifier)
                }
            }
        }
        else {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Logo(Modifier)
                ApplicationDescription(Modifier)
                }
            }
        }
    }