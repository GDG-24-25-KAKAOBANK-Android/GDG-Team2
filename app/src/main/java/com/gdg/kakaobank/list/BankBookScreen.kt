package com.gdg.kakaobank.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.gdg.kakaobank.R
import com.gdg.kakaobank.api.MainViewModel
import com.gdg.kakaobank.component.Banner
import com.gdg.kakaobank.ui.theme.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items


@Composable
fun BankBookScreen() {
    val mainViewModel: MainViewModel = viewModel()
    val users by mainViewModel.users.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        mainViewModel.getUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Banner(bannertext = "기록하며 저축하는\n기록통장 만들기", backgroundColor = Pink)
        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "전문가와 상담하기",
            style = H6_B,
            color = Black,
        )
        Spacer(modifier = Modifier.height(14.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 20.dp) // 상단 컴포넌트와 동일한 padding 값 설정
        ) {
            items(users) { user ->
                Expert(user.toListUser(), Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun Expert(user: User?, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(165.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray, RoundedCornerShape(20.dp))
            .background(Color.Transparent)
    ) {
        user?.let {
            Text(
                text = it.firstName,
                style = B3_B,
                color = Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(25.dp, 30.dp)
            )
            Text(
                text = it.email,
                style = B4_R,
                color = Black,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(25.dp, 45.dp)
            )
            AsyncImage(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .align(Alignment.BottomEnd)
                    .padding(0.dp, 0.dp, 25.dp, 15.dp),
                model = it.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BankBookScreenPreview() {
    BankBookScreen()
}

@Preview(showBackground = true)
@Composable
fun ExpertPreview() {
    Expert(user = User("John", "Doe", "john.doe@example.com", "https://reqres.in/img/faces/1-image.jpg"))
}

data class User(
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String
)

fun com.gdg.kakaobank.api.User.toListUser(): User {
    return User(firstName, lastName, email, avatar)
}