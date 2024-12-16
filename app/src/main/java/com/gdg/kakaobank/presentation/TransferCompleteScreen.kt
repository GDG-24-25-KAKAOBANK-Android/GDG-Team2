package com.gdg.kakaobank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.ui.theme.B4_B
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.H5_SB

@Composable
fun TransferCompleteScreen(
    receiverName: String,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.success),
                contentDescription = "Success Icon",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "$receiverName"+"님께"+"\n이체가 완료되었습니다",
                style = H5_SB,
                color = Black,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
        ) {
            Text(
                text = "돌아가기",
                style = B4_B,
                color = Black
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TransferCompleteScreenPreview() {
    TransferCompleteScreen(receiverName = "이가을", onBackClick = {})
}
