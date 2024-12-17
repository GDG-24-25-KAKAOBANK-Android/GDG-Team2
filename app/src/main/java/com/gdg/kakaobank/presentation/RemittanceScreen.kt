package com.gdg.kakaobank.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.H1_SB
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.H7_B
import com.gdg.kakaobank.ui.theme.H7_R
import com.gdg.kakaobank.ui.theme.H7_SB
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.White
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import com.gdg.kakaobank.presentation.navigator.KakaoNav
import com.gdg.kakaobank.ui.theme.B2_R
import com.gdg.kakaobank.ui.theme.BtnGray
import com.gdg.kakaobank.ui.theme.H5_SB
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemittanceScreen(navController: NavController, recipientName: String) {
    val sendmoney = remember { mutableStateOf(TextFieldValue("")) }
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .background(White)
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("이체 하시겠습니까?", style = H5_SB, color = Black)
                Text("이체하는 순간 잔액이 빠져나갑니다", style = B2_R, color = Black)

                Spacer(modifier = Modifier.height(50.dp))

                Row {
                    Button(
                        onClick = { navController.navigate(KakaoNav.Home.route) },
                        modifier = Modifier
                            .width(120.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BtnGray)
                    ) {
                        Text(
                            text = "취소",
                            style = H7_B,
                            color = White,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
Button(
    onClick = {
        navController.navigate("transfer_done/${recipientName}/${sendmoney.value.text}원")
    },
    modifier = Modifier
        .width(120.dp)
        .height(60.dp),
    colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
) {
    Text(
        text = "이체",
        style = H7_B,
        color = Black,
        modifier = Modifier.padding(0.dp)
    )
}
                }
            }
        },
        containerColor = White,
        sheetPeekHeight = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.popBackStack() },
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "취소",
                    style = H7_R,
                    color = Black,
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "${recipientName}님께",
                    style = H7_SB,
                    color = Black,
                )
                Spacer(modifier = Modifier.height(30.dp))

                // 보낼 금액 입력 필드
                TextField(
                    value = sendmoney.value,
                    onValueChange = { sendmoney.value = it },
                    placeholder = {
                        Text(
                            "보낼 금액",
                            style = H1_SB,
                            color = Gray,
                            modifier = Modifier.padding(horizontal = 90.dp)
                        )
                    },
                    textStyle = H1_SB.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Gray,
                        unfocusedIndicatorColor = Gray,
                        cursorColor = Black,
                        containerColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
                ) {
                    Text(
                        text = "다음",
                        style = H7_B,
                        color = Black,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRemittanceScreen() {
    RemittanceScreen(navController = rememberNavController(), recipientName = "홍길동")
}