package com.gdg.kakaobank.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import com.gdg.kakaobank.presentation.NoRippleInteractionSource
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Dark_Gray
import com.gdg.kakaobank.ui.theme.Deep_Blue
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Light_Gray
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.White

@Composable
fun LoanCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Deep_Blue),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 45.dp, start = 20.dp)
        ) {
            Column {
                Text(
                    text = "아파트부터 빌라까지\n주택담보대출",
                    style = H6_B,
                    color = White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoanCategoryChips() {
    val chipItems = listOf(
        "신용대출", "중신용대출", "비상금대출", "신용대출 갈아타기", "개인사업자 신용대출",
        "개인사업자 보증서대출", "중고차 구매대출", "저당대출", "전월세보증금 대출",
        "전월세보증금 대출 갈아타기", "담보대출", "주택담보대출", "주택담보대출 갈아타기"
    )

    val selectedChips = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "대출",
            style = H6_B,
            color = Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            chipItems.forEach { chip ->
                SelectableChip(
                    text = chip,
                    isSelected = selectedChips.contains(chip),
                    onClick = {
                        if (selectedChips.contains(chip)) {
                            selectedChips.remove(chip)
                        } else {
                            selectedChips.add(chip)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) White else Light_Gray
    val textColor = if (isSelected) Main_Yellow else Dark_Gray
    val borderColor = if (isSelected) Main_Yellow else Light_Gray

    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = NoRippleInteractionSource,
                indication = null,
                onClick = {
                    onClick()
                }
            )
            .border(
                BorderStroke(1.dp, borderColor),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            style = B4_R
        )
    }
}

@Composable
fun LoanScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LoanCard()
        LoanCategoryChips()
    }
}

@Preview(showBackground = true)
@Composable
fun LoanScreenPreview() {
    LoanScreen()
}
