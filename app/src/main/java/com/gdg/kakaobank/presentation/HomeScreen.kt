package com.gdg.kakaobank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.component.BankComponent
import com.gdg.kakaobank.component.MyBankComponent
import com.gdg.kakaobank.ui.theme.Dark_Mint
import com.gdg.kakaobank.ui.theme.Deep_Blue
import com.gdg.kakaobank.ui.theme.Light_Mint
import com.gdg.kakaobank.ui.theme.Pink
import com.gdg.kakaobank.ui.theme.White
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.B4_B
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H5_B

@Composable
fun HomeScreen() {
    val bankList = listOf(
        Pair("김나현", "700,000,000"),
        Pair("조영서", "700,000,000"),
        Pair("이가을", "100,000,000"),
        Pair("백서연", "100,000,000"),
        Pair("이현진", "100,000,000")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "눈송이",
                    style = H5_B,
                    color = Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "내 계좌",
                    style = B4_B,
                    color = Black
                )
            }
            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "Bell Icon",
                modifier = Modifier
                    .size(30.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(1) {
                MyBankComponent(name = "눈송이", amount = "100,000,000")
                Spacer(modifier = Modifier.height(12.dp))
            }
            items(bankList.size) { index ->
                val colors = listOf(Pink, Dark_Mint, Light_Mint, Deep_Blue)
                val (name, amount) = bankList[index]
                BankComponent(
                    name = name,
                    amount = amount,
                    backgroundColor = colors[index % colors.size]
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CustomComponentPreview() {
    HomeScreen()
}