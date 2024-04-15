package edu.com.trailslist.compose.appcomponents.homecomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.com.trailslist.R

@Composable
fun Logo(modifier: Modifier) {
    val logoPainter = painterResource(id = R.drawable.logo)
    Image(painter = logoPainter,
        contentDescription = "Application logo",
        modifier = modifier.size(300.dp))
}