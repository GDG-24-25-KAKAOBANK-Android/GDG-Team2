package com.gdg.kakaobank.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gdg.kakaobank.component.Banner
import com.gdg.kakaobank.component.CardInfo
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Dark_Gray
import com.gdg.kakaobank.ui.theme.Dark_Mint
import com.gdg.kakaobank.ui.theme.Deep_Blue
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Pink
import com.gdg.kakaobank.ui.theme.Yellow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment


data class Card(val title: String, val description: String, val color: Color)

class CardViewModel : ViewModel() {
    private val _cards = mutableStateListOf<Card>()
    val cards: SnapshotStateList<Card> get() = _cards

    init {
        // 카드 데이터 추가
        _cards.addAll(
            listOf(
                Card("혜택 좋은 신용카드", "", color = Yellow),
                Card("프렌즈 체크카드", "쓸 때마다 캐시백 , 주말엔 2배", color = Pink),
                Card("모임 체크카드", "결제하면 바로 받는 랜덤 캐시백", color = Dark_Mint),
                Card("개인사업자 체크카드", "사업자에게 꼭 필요한 혜택만 담아", color = Deep_Blue),
                Card("개인사업자 재휴 신용카드", "기본할인부터 사업자 특화혜택까지", color = Dark_Gray)
            )
        )
    }
}

@Composable
fun CardScreen(cardViewModel: CardViewModel = viewModel()) {
    val cards = cardViewModel.cards

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Banner(bannertext = "혜택 좋은 카드 쓰고\n현금 캐시백 받기", backgroundColor = Yellow)
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "카드",
            style = H6_B,
            color = Black,
        )
        Spacer(modifier = Modifier.height(14.dp))

        cards.forEach { card: Card ->
            CardInfo(cardtitle = card.title, cardinfo = card.description, circleColor = card.color)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardScreenPreview() {
    CardScreen()
}