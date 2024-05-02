package edu.com.trailslist.compose.appcomponents.homecomponents

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import edu.com.trailslist.ui.theme.TrailTheme

@Composable
fun ApplicationDescription(modifier: Modifier) {
    Text(modifier = modifier,
        text = "Welcome to TrailList! This app is used to display informations about two kind of trails: low-lying trails and moauntain trails. Enjoy!",
        fontFamily = TrailTheme.typography.body.fontFamily,
        fontSize = TrailTheme.typography.body.fontSize,
        fontWeight = TrailTheme.typography.body.fontWeight)
}