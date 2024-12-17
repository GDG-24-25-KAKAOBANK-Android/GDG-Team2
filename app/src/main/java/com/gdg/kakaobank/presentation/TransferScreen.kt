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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gdg.kakaobank.R
import com.gdg.kakaobank.presentation.navigator.TransferViewModel
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.H7_B
import com.gdg.kakaobank.ui.theme.H7_R
import com.gdg.kakaobank.ui.theme.H7_SB
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.White

data class Transfer(val name: String, val amount: String)

class TransferViewModel : ViewModel() {
    private val _transfer = mutableStateListOf<Transfer>()
    val transfer: List<Transfer> get() = _transfer

    init {
        _transfer.addAll(
            listOf(
                Transfer("이가을", "10,000원"),
                Transfer("김나현", "20,000원"),
                Transfer("백서현", "300,000원"),
                Transfer("이현진", "400원"),
                Transfer("조영서", "5000원")
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferScreen(navController: NavController, transferViewModel: TransferViewModel) {
    val transfer = transferViewModel.transfer
    val recipientName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(30.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.popBackStack() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "닫기",
                style = H7_R,
                color = Black,
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "이체",
            style = H5_B,
            color = Black,
        )
        Spacer(modifier = Modifier.height(10.dp))

        // 계좌번호 입력 필드
TextField(
    value = recipientName.value,
    onValueChange = { recipientName.value = it },
    placeholder = { Text("받는 사람 이름 또는 계좌번호", style = B4_R, color = Gray) },
    leadingIcon = {
        Icon(
            painter = painterResource(id = R.drawable.name_search),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .padding(4.dp),
            tint = Gray
        )
    },
    textStyle = B4_R.copy(color = Black),
    modifier = Modifier
        .fillMaxWidth(),
    colors = TextFieldDefaults.textFieldColors(
        focusedIndicatorColor = Gray,
        unfocusedIndicatorColor = Gray,
        cursorColor = Black,
        containerColor = Color.Transparent
    )
)

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "최근 이체",
            style = H7_SB,
            color = Black,
        )

        transfer.forEach { transferItem ->
            TransferList(name = transferItem.name, amount = transferItem.amount)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate("remit/${recipientName.value}")
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

@Composable
fun TransferList(name: String, amount: String) {
    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = name,
        style = B3_B,
        color = Black,
    )
    Text(
        text = amount,
        style = B4_R,
        color = Black,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTransferScreen() {
    TransferScreen(navController = rememberNavController(), transferViewModel = TransferViewModel())
}