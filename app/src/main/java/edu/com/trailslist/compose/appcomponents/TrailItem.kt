package edu.com.trailslist.compose.appcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.util.screenInfo
import edu.com.trailslist.util.ScreenInfoData

@Composable
fun TrailItem(trail: Trail) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenWidthData is ScreenInfoData.ScreenType.Tablet
            || screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Tablet

    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(if (isTablet) 2.dp else 1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(trail.image),
                contentDescription = "Trail image",
                modifier = Modifier
                    .size(if (isTablet) 180.dp else 140.dp)
                    .clip(RoundedCornerShape(if (isTablet) 45.dp else 40.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = trail.name,
                    fontSize = if (isTablet) 25.sp else 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}