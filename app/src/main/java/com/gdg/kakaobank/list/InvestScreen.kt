package com.gdg.kakaobank.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gdg.kakaobank.component.Banner
import com.gdg.kakaobank.ui.theme.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

data class Invest(val title: String)

class InvestViewModel : ViewModel() {
    private val _invests = mutableStateListOf<Invest>()
    val invests: SnapshotStateList<Invest> get() = _invests

    var selectedInvest by mutableStateOf<Invest?>(null)

    init {
        //투자 데이터 추가
        _invests.addAll(
            listOf(
                Invest("펀드"),
                Invest("증권사 금융상품 투자"),
                Invest("국내주식 투자"),
                Invest("해외주식 투자"),


                )
        )
    }

    fun selectInvest(invest: Invest) {
        selectedInvest = if (selectedInvest == invest) null else invest
    }
}

@Composable
fun InvestScreen(investViewModel: InvestViewModel = viewModel()) {
    val invests = investViewModel.invests
    val selectedInvest = investViewModel.selectedInvest

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Banner(bannertext = "혜택 받고 간편하게\n주식계좌 개설하기", backgroundColor = Light_Mint)
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "투자",
            style = H6_B,
            color = Black
        )
        Spacer(modifier = Modifier.height(14.dp))

        LazyRow {
            items(invests) { invest ->
                InvestItem(
                    invest = invest,
                    isSelected = invest == selectedInvest,
                    onClick = { investViewModel.selectInvest(invest)
                    }
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}


@Composable
fun InvestItem(invest: Invest, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) Yellow else Light_Gray)
            .clickable { onClick() }
            .padding(15.dp,10.dp,15.dp,10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = invest.title,
                style = B3_SB,
                color = if (isSelected) White else Dark_Gray
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun InvestScreenPreview() {
    InvestScreen()
}