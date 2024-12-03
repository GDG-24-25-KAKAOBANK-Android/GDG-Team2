package com.gdg.kakaobank.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.ui.theme.*

@Composable
fun SavingInfo(savingtitle: String, savinginfo: String, savingrate: String, circleColor: Color = Yellow) {
    Row(
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(color = circleColor)
            }
        }
        Column {
            Text(text = savingtitle, style = B3_B, color = Black)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = savinginfo, style = B4_R, color = Black)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "최고 연 ${savingrate}",
                    style = B4_B,
                    color = Deep_Blue
                )
            }
        }
    }
}