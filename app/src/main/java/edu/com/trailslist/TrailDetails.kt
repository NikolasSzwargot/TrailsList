package edu.com.trailslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TrailDetails(trail: Trail, viewModel: TrailViewModel) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenWidthData is screenInfoData.ScreenType.Tablet
            || screenInfoData.screenHeightData is screenInfoData.ScreenType.Tablet
    val measuredTime by viewModel.observeMeasuredTime(trail.id!!).collectAsState(initial = trail.measuredTime)



    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        if (measuredTime != 0L) {
            Text(
                text = "Ostatni czas: " + formatElapsedTime(measuredTime ?: trail.measuredTime),
                fontSize = 20.sp,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
        }

        Stopwatch(viewModel = viewModel, id = trail.id!!)
        Text(
            text = trail.name,
            fontSize = if (isTablet) 45.sp else 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Image(
            painter = painterResource(id = trail.image),
            contentDescription = "Trail image",
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isTablet) 350.dp else 200.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(bottom = 8.dp)
        )
        Text(
            text = trail.description,
            fontSize = if (isTablet) 22.sp else 16.sp,
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun TrailDetailsPreview() {
    val trail = Trail(
        name = "Szlak przyklad",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        image = R.drawable.trail,
        measuredTime = 0L
        )
    Column (
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = trail.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painterResource(id = trail.image),
            contentDescription = "Trail image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(bottom = 8.dp)
        )
        Text(
            text = trail.description,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}