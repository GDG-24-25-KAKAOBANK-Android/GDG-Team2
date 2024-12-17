// TransferDoneScreen.kt
package com.gdg.kakaobank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gdg.kakaobank.R
import com.gdg.kakaobank.presentation.navigator.KakaoNav
import com.gdg.kakaobank.presentation.navigator.Transfer
import com.gdg.kakaobank.presentation.navigator.TransferViewModel
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H5_SB
import com.gdg.kakaobank.ui.theme.H7_B
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.White

@Composable
fun TransferDoneScreen(navController: NavController, recipientName: String, transferViewModel: TransferViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        Image(
            painter = painterResource(id = R.drawable.tran_done),
            contentDescription = "Done",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "${recipientName}님께",
            style = H5_SB,
            color = Black,
            modifier = Modifier
                .padding(0.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "이체가 완료되었습니다",
            style = H5_SB,
            color = Black,
            modifier = Modifier
                .padding(0.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                transferViewModel.addTransfer(Transfer(recipientName, "금액"))
                navController.navigate(KakaoNav.Home.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow)
        ) {
            Text(
                text = "돌아가기",
                style = H7_B,
                color = Black,
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTransferDoneScreen() {
    val transferViewModel = TransferViewModel()
    TransferDoneScreen(navController = rememberNavController(), recipientName = "홍길동", transferViewModel = transferViewModel)
}