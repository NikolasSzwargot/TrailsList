package edu.com.trailslist.compose.appcomponents.trailscomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import edu.com.trailslist.database.entities.Trail
import edu.com.trailslist.ui.theme.TrailTheme
import edu.com.trailslist.util.ScreenInfoData
import edu.com.trailslist.util.screenInfo

@Composable
fun TrailItem(trail: Trail) {
    val screenInfoData = screenInfo()
    val isTablet = screenInfoData.screenHeightData is ScreenInfoData.ScreenType.Tablet

    ElevatedCard(
        modifier = Modifier
            .padding(if (isTablet) 12.dp else 8.dp)
            .width(if (isTablet) 200.dp else 180.dp)
            .height(if (isTablet) 180.dp else 190.dp)
            .clip(RoundedCornerShape(16.dp))
            .fillMaxSize()
            .border(
                width = 5.dp,
                color = TrailTheme.colorScheme.borders,
                shape = TrailTheme.shape.card
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
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
                fontSize = TrailTheme.typography.titleNormal.fontSize,
                fontWeight = TrailTheme.typography.titleNormal.fontWeight,
                fontFamily = TrailTheme.typography.titleNormal.fontFamily,
                textAlign = TextAlign.Center
            )
            //}
        }
    }
}