package com.gdg.kakaobank.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.kakaobank.R
import com.gdg.kakaobank.api.Recommendation
import com.gdg.kakaobank.api.RecommendationViewModel
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B3_R
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Deep_Blue
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.White

@Composable
fun ListCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Deep_Blue),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hand_heart),
                    contentDescription = "Hand_heart",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 8.dp)
                )
            }
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .offset(y=140.dp)
            ) {
                Column {
                    Text(
                        text = "단골사장님께만 드려요",
                        style = B3_B,
                        color = White,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "우대금 0.24%p 제공",
                        style = B3_R,
                        color = White
                    )
                }
            }
        }
    }
}
@Composable
fun RecommendedList(viewModel: RecommendationViewModel = hiltViewModel()) {
    val recommendations = viewModel.recommendations

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "이가을님 맞춤 추천",
            style = H6_B,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recommendations) { recommendation ->
                RecommendationItem(
                    icon = recommendation.icon,
                    title = recommendation.title,
                    description = recommendation.description
                )
            }
        }
    }
}

@Composable
fun RecommendationItem(icon: Int, title: String, description: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp),
            tint = Color.Unspecified
        )
        Column {
            Text(
                text = title,
                style = B3_B,
                color = Black
            )
            Text(
                text = description,
                style = B4_R,
                color = Black
            )
        }
    }
}


@Composable
fun RecommendScreen(viewModel: RecommendationViewModel = hiltViewModel()) {
    val recommendations = viewModel.recommendations

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ListCard()
        RecommendedListContent(recommendations)
    }
}



@Composable
fun PreviewListScreen() {

    val dummyRecommendations = listOf(
        Recommendation(R.drawable.money, "정기예금", "실시간 이자로 돈 모으는 재미가 쏠쏠"),
        Recommendation(R.drawable.bank_banking_budget, "모임통장", "함께 쓰고 같이 보는 회비관리"),
        Recommendation(R.drawable.bank, "신용대출/중신용대출", "직장인이라면 쉽고 빠르게"),
        Recommendation(R.drawable.truck, "전월세보증금대출", "이사를 가거나 보증금이 오른다면"),
        Recommendation(R.drawable.dollar, "신용대출 갈아타기", "방문 없이 원하는 대출로 간편하게")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ListCard()
        RecommendedListContent(dummyRecommendations)
    }
}


@Composable
fun RecommendedListContent(recommendations: List<Recommendation>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "이가을님 맞춤 추천",
            style = H6_B,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(recommendations) { recommendation ->
                RecommendationItem(
                    icon = recommendation.icon,
                    title = recommendation.title,
                    description = recommendation.description
                )
            }
        }
    }
}




