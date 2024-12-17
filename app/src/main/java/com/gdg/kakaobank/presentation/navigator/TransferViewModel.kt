// TransferViewModel.kt
package com.gdg.kakaobank.presentation.navigator

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TransferViewModel : ViewModel() {
    private val _transfer = mutableStateListOf<Transfer>()
    val transfer: List<Transfer> get() = _transfer

    init {
        _transfer.addAll(
            listOf(
                Transfer("이가을", "10,000원"),
                Transfer("김나현", "20,000원"),
                Transfer("백서현", "300,000원"),
                Transfer("이현진", "400원"),
                Transfer("조영서", "5000원")
            )
        )
    }

    fun addTransfer(transfer: Transfer) {
        _transfer.add(transfer)
    }
}