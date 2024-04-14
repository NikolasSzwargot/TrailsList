package edu.com.trailslist.compose.appcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo

@Composable
fun TrailItem(trail: Trail) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Tablet
            || screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Tablet

    Box(
        modifier = Modifier
            .padding(8.dp)
            .width(180.dp)
            .height(190.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxSize()
            .border(width = 5.dp,
                color = Color(0, 153, 76),
                shape = CutCornerShape(8.dp)
            )
            .padding(10.dp)

            //.m
            //.border(if (isTablet) 2.dp else 1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(trail.image),
                contentDescription = "Trail image",
                modifier = Modifier.size(120.dp)
//                    .size(if (isTablet) 180.dp else 140.dp)
//                    .clip(RoundedCornerShape(if (isTablet) 45.dp else 40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            //Column {
            Text(
                text = trail.name,
                fontSize = 16.sp,//if (isTablet) 25.sp else 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            //}
        }
    }
}