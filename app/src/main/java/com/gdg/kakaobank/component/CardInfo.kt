package com.gdg.kakaobank.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Main_Yellow

@Composable
fun CardInfo(cardtitle: String, cardinfo: String, circleColor: Color = Main_Yellow) {
    Row(
        modifier = Modifier
            .padding(0.dp,0.dp,0.dp,12.dp),
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
            Text(text = cardtitle, style = B3_B, color = Black)
            Text(text = cardinfo, style = B4_R, color = Black)
        }
    }
}

