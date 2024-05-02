package edu.com.trailslist.compose.appcomponents.homecomponents

import android.animation.ObjectAnimator
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.com.trailslist.R
import edu.com.trailslist.viewmodels.TrailViewModel


@Composable
fun Logo(modifier: Modifier, viewModel: TrailViewModel) {
    val logoPainter = painterResource(id = R.drawable.logo)
    var logoSize by remember {
        mutableIntStateOf(300)
    }

    val animator = ObjectAnimator.ofInt( 300, 100, 300).apply {
        duration = 3000
        addUpdateListener { valueAnimator ->
            logoSize = valueAnimator.animatedValue as Int
        }
    }

    Image(painter = logoPainter,
        contentDescription = "Application logo",
        modifier = modifier.size(logoSize.dp)
            .clickable {
                animator.start()
            })
}