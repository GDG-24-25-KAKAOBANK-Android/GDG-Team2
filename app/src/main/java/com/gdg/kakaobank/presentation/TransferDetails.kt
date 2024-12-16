package com.gdg.kakaobank.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_SB
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Main_Yellow
import androidx.navigation.NavController
import com.gdg.kakaobank.ui.theme.B4_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.H1_SB
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.H7_R
import com.gdg.kakaobank.ui.theme.H7_SB
import com.gdg.kakaobank.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferDetailScreen(
    receiverName: String,
    onCancelClick: () -> Unit,
    onNextClick: () -> Unit,
    navController: NavController
) {
    var transferAmount by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Text (
            text = "취소",
            style = H7_R,
            color = Gray,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { onCancelClick() }
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("$receiverName"+"님께",
                style = H7_SB,
                color = Black)
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = transferAmount,
                onValueChange = { transferAmount = it },
                placeholder = { Text ("보낼금액",
                    color = Color.LightGray,
                    style = H1_SB,
                    textAlign = TextAlign.Center
                )
                              },
                textStyle = H1_SB.copy(textAlign = TextAlign.Center),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }

        Button(
            onClick = { showBottomSheet = true },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
        ) {
            Text("다음",
                style = B4_B,
                color = Black)
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("이체 하시겠습니까?", style = H5_B)
                Spacer(modifier = Modifier.height(8.dp))
                Text("이체하는 순간 잔액이 빠져나갑니다", style = B4_R, color = Color.Gray)
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { showBottomSheet = false },
                        colors = ButtonDefaults.buttonColors(containerColor = Gray), modifier = Modifier
                            .weight(2f)
                            .height(60.dp)
                            .padding(4.dp))
                    { Text("취소", color = White, style = B4_SB) }
                    Button(onClick = { onNextClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow), modifier = Modifier
                            .weight(2f)
                            .height(60.dp)
                            .padding(4.dp))
                    { Text("이체", color = Black, style = B4_B) }
                }
            }
        }
    }
}

