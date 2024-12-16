package com.gdg.kakaobank.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.B4_SB
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.H5_SB
import com.gdg.kakaobank.ui.theme.White
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetWithBackHandler() {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }

    BackHandler(enabled = true) {
        showBottomSheet = true
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
                showBottomSheet = false
            },
            sheetState = bottomSheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("앱을 종료 하시겠습니까?", style = H5_B)
                Spacer(modifier = Modifier.height(8.dp))
                Text("종료 버튼을 누르면 앱이 종료 됩니다", style = B4_R, color = Color.Gray)
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch { bottomSheetState.hide() }
                            showBottomSheet = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Gray),
                        modifier = Modifier
                            .weight(2f)
                            .height(60.dp)
                            .padding(4.dp)
                    ) {
                        Text("취소", color = White, style = B4_SB)
                    }
                    Button(
                        onClick = {
                            showBottomSheet = false
                            System.exit(0)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow),
                        modifier = Modifier
                            .weight(2f)
                            .height(60.dp)
                            .padding(4.dp)
                    ) {
                        Text("종료", color = Black, style = B4_B)
                    }
                }
            }
        }
    }
}