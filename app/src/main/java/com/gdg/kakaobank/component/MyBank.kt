// src/main/java/com/gdg/kakaobank/component/MyBank.kt
package com.gdg.kakaobank.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.B4_SB
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.Yellow

@Composable
fun MyBankComponent(
    name: String,
    amount: String,
    backgroundColor: Color = Main_Yellow,
    navController: NavController // navController 추가

) {
    Box(
        modifier = Modifier
            .size(340.dp, 140.dp)
            .background(backgroundColor, shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_bank),
            contentDescription = "KaKao Bank Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(31.dp)
                .align(Alignment.TopStart)
                .offset(y = 10.dp) // 아래로 이동

        )
        Text(
            text = "${name}의 통장",
            style = B4_R,
            color = Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 40.dp)
                .offset(y = 10.dp) // 아래로 이동
        )
        Text(
            text = "${amount}원",
            style = H6_B,
            color = Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 40.dp)
                .offset(y = 26.dp) // 아래로 이동
        )
        Image(
            painter = painterResource(id = R.drawable.more),
            contentDescription = "More Icon",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(29.dp)
                .align(Alignment.TopEnd)
        )
        Button(
            onClick = { navController.navigate("transfer") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(50.dp, 30.dp), // 버튼 크기 조정
            colors = ButtonDefaults.buttonColors(containerColor = Yellow),
            contentPadding = PaddingValues(0.dp) // 버튼 내부 패딩 조정

        ) {
            Text(
                text = "이체",
                style = B4_SB,
                color = Black,
                modifier = Modifier.padding(0.dp) // 텍스트 패딩 조정
            )
        }
    }
}