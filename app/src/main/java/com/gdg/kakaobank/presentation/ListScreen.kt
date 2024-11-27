package com.gdg.kakaobank.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.ui.theme.B2_B
import com.gdg.kakaobank.ui.theme.Black
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.H1_B
import com.gdg.kakaobank.ui.theme.H5_B
import com.gdg.kakaobank.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListScreen() {
    val pages = listOf("추천", "통장", "저축", "카드", "투자", "대출")
    val pagerState = rememberPagerState()

    LaunchedEffect(Unit) {
        pagerState.scrollToPage(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Top
    ) {
        ClipTabRow(pages = pages, pagerState = pagerState)
        ClipHorizontalPager(pages = pages, pagerState = pagerState)
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClipTabRow(

    pages: List<String>,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()

    Spacer(modifier = Modifier.height(30.dp))
    Text(
        text = "상품",
        style = H5_B,
        color = Black,
        modifier = Modifier.padding(horizontal = 16.dp)
    )

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = White, // 원하는 배경색 설정
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Black
            )
        },
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        pages.forEachIndexed { index, text ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                text = { Text(text = text, style= B2_B,
                        color = if (pagerState.currentPage == index) Black else Gray // 선택 여부에 따라 직접 색상 지정
                )  },
                selectedContentColor = Black,
                unselectedContentColor = Gray
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ClipHorizontalPager(
    pages: List<String>,
    pagerState: PagerState
) {
    HorizontalPager(
        count = pages.size,
        state = pagerState
    ) { index ->
        Box(modifier = Modifier.fillMaxSize()) {
            SearchResultTab(page = pages[index])
        }
    }
}

@Composable
fun SearchResultTab(page: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Content for $page", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    ListScreen()
}