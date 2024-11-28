package com.gdg.kakaobank.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.White

@Composable
fun Banner(bannertext: String, backgroundColor: Color = Main_Yellow) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
    ) {
        Text(
            text = "${bannertext} ",
            style = H6_B,
            color = White,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 15.dp)
        )
    }
}

@Preview
@Composable
fun BannerPreview() {
    Banner(
        bannertext = "안녕하세요"
    )
}