package edu.com.trailslist.compose.appcomponents.trailscomponents

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.com.trailslist.R

@Composable
fun SnapFab(modifier: Modifier, onClick: () -> Unit) {
        FloatingActionButton(
            modifier = modifier,
            onClick = { onClick() },
    ) {
            val painter: Painter = painterResource(id = R.drawable.cameraicon)
        Icon(
            painter = painter,
            contentDescription = "Camera Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}