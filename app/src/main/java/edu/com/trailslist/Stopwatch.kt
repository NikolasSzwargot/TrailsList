package edu.com.trailslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun Stopwatch(
    viewModel: TrailViewModel,
    modifier: Modifier = Modifier
): Long {
    var isTracking = viewModel.isTracking
    var elapsedTime = viewModel.elapsedTime

    if (isTracking) {
        LaunchedEffect(Unit) {
            while (isTracking) {
                delay(1000)
                elapsedTime++
                viewModel.updateElapsedTime(elapsedTime)
            }
        }
    }

    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = formatElapsedTime(elapsedTime),
            color = Color.Black,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { isTracking = !isTracking
                             viewModel.updateIsTracking(isTracking)},
                modifier = Modifier.padding(end = 8.dp)) {
                Text(text = if (isTracking) "Stop" else "Start")
            }
            if (elapsedTime != 0L) {
                Button(onClick = { elapsedTime = 0L
                    isTracking = false
                    viewModel.updateIsTracking(isTracking)
                    viewModel.updateElapsedTime(elapsedTime)},
                    modifier = Modifier.padding(end = 8.dp)) {
                    Text(text = "Reset")
                }
            }
            if (!isTracking && elapsedTime != 0L) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Zapisz")
                }
            }
        }
    }

    return elapsedTime
}

fun formatElapsedTime(elapsedTime: Long): String {
    val seconds = (elapsedTime % 60).toString().padStart(2, '0')
    val minutes = ((elapsedTime / 60) % 60).toString().padStart(2, '0')
    val hours = (elapsedTime / 3600).toString().padStart(2, '0')
    return "$hours:$minutes:$seconds"
}