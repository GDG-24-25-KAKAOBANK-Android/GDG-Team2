package com.gdg.kakaobank.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.B4_SB
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.H7_R
import com.gdg.kakaobank.ui.theme.H7_SB
import com.gdg.kakaobank.ui.theme.Main_Yellow

data class RecentTransfer(val name: String, val amount: String)

@Composable
fun TransferScreen(
    onCloseClick: () -> Unit,
    onNextClick: (String) -> Unit
) {
    val recentTransfers = listOf(
        RecentTransfer("이가을", "10,000원"),
        RecentTransfer("김나현", "20,000원"),
        RecentTransfer("백서연", "300,000원"),
        RecentTransfer("이현진", "400원"),
        RecentTransfer("조영서", "5,000원")
    )

    var receiverName by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = "닫기",
            style = H7_R,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { onCloseClick() }
        )

        Text(
            text = "이체",
            style = H5_B,
            color = Black,
            modifier = Modifier.padding(top = 50.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.search),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.padding(top = 20.dp, end = 8.dp)
            )

            TextField(
                value = receiverName,
                onValueChange = { receiverName = it },
                placeholder = {
                    Text(
                        text = "받는사람 이름 또는 계좌번호",
                        color = Color.Gray,
                        style = B4_R
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "최근 이체",
            style = H7_SB,
            color = Black
        )

        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(recentTransfers) { transfer ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = transfer.name,
                        style = B3_B,
                        color = Black
                    )
                    Text(
                        text = transfer.amount,
                        style = B4_R,
                        color = Black,

                        )
                }
            }
        }

        Button(
            onClick = {
                if (receiverName.text.isNotEmpty()) {
                    onNextClick(receiverName.text)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
        ) {
            Text(
                text = "다음",
                style = B4_B,
                color = Black
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TransferScreenPreview() {
    TransferScreen(
        onCloseClick = {},
        onNextClick = {}
    )
}
