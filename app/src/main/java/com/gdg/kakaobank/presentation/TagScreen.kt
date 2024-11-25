package com.gdg.kakaobank.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.B4_SB
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Dark_Gray
import com.gdg.kakaobank.ui.theme.Dark_Mint
import com.gdg.kakaobank.ui.theme.H1_B
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Light_Mint
import com.gdg.kakaobank.ui.theme.Main_Yellow
import com.gdg.kakaobank.ui.theme.Pink
import com.gdg.kakaobank.ui.theme.White
import java.time.format.TextStyle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import com.gdg.kakaobank.ui.theme.Deep_Blue
import com.gdg.kakaobank.ui.theme.Yellow


@Composable
fun TagScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding( horizontal = 23.dp ,vertical = 20.dp)
    ) {

        Text(
            text = "혜택",
            style = H5_B
        )

        Spacer(modifier = Modifier.height(16.dp))

        BenefitCard()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "바로 받아요",
            style = H6_B
        )

        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) {
                ShareItem()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val titles = listOf("24-25\nGDG Android", "24-25\nGDG Flutter", "24-25\nGDG Spring")
            items(titles.size) { index ->
                TagCard(
                    title = titles[index],
                    backgroundColor = when (index) {
                        0 -> Light_Mint
                        1 -> Yellow
                        else -> Deep_Blue
                    }
                )
            }
        }
    }
}

@Composable
fun BenefitCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Main_Yellow), // 사용자 정의 색상
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(5.dp)
    ) {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .padding( top = 5.dp),
                    text = "1원 뺴빼로 뽑기!",
                    style = B4_R,
                    color = Black,
                )
                Text(
                    text = "소문내고 기회 더 얻기",
                    style = B3_B,
                    color = Black,
                )
            }
        }
    }
}

@Composable
fun ShareItem() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_send),
            contentDescription = "Send Icon",
            modifier = Modifier.size(35.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))


        Column {
            Text(
                text = "공유하고",
                style = B3_B
            )
            Text(
                text = "1원에 구매하기",
                style = B4_R
            )
        }
    }
}


@Composable
fun TagCard(title: String, backgroundColor: Color) {
    var isSelected by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .width(205.dp)
            .height(90.dp)
            .padding(2.dp)
            .clickable(
            interactionSource = NoRippleInteractionSource,
            indication = null
        ) {
            isSelected = !isSelected
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = H6_B,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }


            Image(
                painter = painterResource(
                    id = if (isSelected) R.drawable.ic_selected else R.drawable.ic_nonselected
                ),
                contentDescription = "Heart Icon",
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp)
                    .padding(end = 10.dp)
                ,
                colorFilter = ColorFilter.tint(
                    if (isSelected) Pink else White
                )
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun TagScreenPreview() {
    TagScreen()
}
