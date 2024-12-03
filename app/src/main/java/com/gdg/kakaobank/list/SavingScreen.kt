package com.gdg.kakaobank.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gdg.kakaobank.component.Banner
import com.gdg.kakaobank.component.SavingInfo
import com.gdg.kakaobank.ui.theme.*

data class Saving(val title: String, val description: String, val rate: String)

class SavingViewModel : ViewModel() {
    private val _savings = mutableStateListOf<Saving>()
    val savings: SnapshotStateList<Saving> get() = _savings

    init {
        // 초기 저축 데이터 추가
        _savings.addAll(
            listOf(
                Saving("자유적금", "매일/매주/매월 자유롭게", rate = "3.70%"),
                Saving("26주적금", "캐릭터와 함께 즐거운 도전", rate = "5.50%"),
                Saving("한달적금", "춘식이와 한달동안 매일매일 저금", rate = "7.00%"),
                Saving("정기예금", "실시간 이자로 돈 모으는 재미가 쏠쏠", rate = "3.10%"),
                Saving("저금통", "숨어있던 쟌돈을 알아서 차곡차곡", rate = "8.00%"),
                Saving("mini 26일저금", "매일 꾸준하게 용돈 자동저금", rate = "2.50%")
            )
        )
    }
}

@Composable
fun SavingScreen(savingViewModel: SavingViewModel = viewModel()) {
    val savings = savingViewModel.savings

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Banner(bannertext = "알아서 모아주는\n저금통 민들기", backgroundColor = Dark_Mint)
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "저축",
            style = H6_B,
            color = Black,
        )
        Spacer(modifier = Modifier.height(14.dp))

        savings.forEach { saving: Saving ->
            SavingInfo(savingtitle = saving.title, savinginfo = saving.description, savingrate = saving.rate)
            Spacer(modifier = Modifier.height(10.dp)) // 간격을 10dp로 줄임
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavingScreenPreview() {
    SavingScreen()
}