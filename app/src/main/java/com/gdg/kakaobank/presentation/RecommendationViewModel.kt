package com.gdg.kakaobank.presentation

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.gdg.kakaobank.R

class RecommendationViewModel : ViewModel() {

    var recommendations by mutableStateOf(listOf<Recommendation>())
        private set


    init {
        loadRecommendations()
    }

    private fun loadRecommendations() {
        val recommendations = listOf(
            Recommendation(R.drawable.money, "정기예금", "실시간 이자로 돈 모으는 재미가 쏠쏠"),
            Recommendation(R.drawable.bank_banking_budget, "모임통장", "함께 쓰고 같이 보는 회비관리"),
            Recommendation(R.drawable.bank, "신용대출/중신용대출", "직장인이라면 쉽고 빠르게"),
            Recommendation(R.drawable.truck, "전월세보증금대출", "이사를 가거나 보증금이 오른다면"),
            Recommendation(R.drawable.dollar, "신용대출 갈아타기", "방문 없이 원하는 대출로 간편하게")
        )
    }
}

data class Recommendation(val icon: Int, val title: String, val description: String)
