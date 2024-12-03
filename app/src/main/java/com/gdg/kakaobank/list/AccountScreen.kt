package com.gdg.kakaobank.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gdg.kakaobank.api.MainViewModel
import com.gdg.kakaobank.api.User
import com.gdg.kakaobank.ui.theme.B3_B
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.H6_B
import com.gdg.kakaobank.ui.theme.Light_Gray
import com.gdg.kakaobank.ui.theme.Pink
import com.gdg.kakaobank.ui.theme.White

@Composable
fun AccountScreen() {
    val mainViewModel: MainViewModel = viewModel()
    val users by mainViewModel.users.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        mainViewModel.getUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {

        AccountCard()

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "전문가와 상담하기",
            color = Black,
            style = H6_B,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )


        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(users) { user ->
                UserItem(user)
            }
        }
    }
}

@Composable
fun AccountCard() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Pink),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(15.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 30.dp, start = 20.dp)
        ) {
            Column {
                Text(
                    text = "기록하며 저축하는\n기록통장 만들기",
                    style = H6_B,
                    color = White,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(White)
            .border(1.dp, Light_Gray, RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(160.dp),

    ) {
        Column (
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp)
        ) {

        Text(
            text = user.firstName,
            color = Black,
            style = B3_B
        )

        Spacer(modifier = Modifier.height(4.dp))


        Text(
            text = user.email,
            color = Black,
            style = B4_R
        )
    }

        Spacer(modifier = Modifier.height(10.dp))

        AsyncImage(
            model = user.avatar,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 20.dp, start = 100.dp)
                .size(60.dp)
                .clip(RoundedCornerShape(30.dp)
                )
        )


    }
}
