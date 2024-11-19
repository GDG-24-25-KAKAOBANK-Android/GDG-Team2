package com.gdg.kakaobank.presentation.navigator

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.gdg.kakaobank.R

sealed class KakaoNav(
    val route: String,
    @StringRes val resourceId: Int,
    @DrawableRes val icon: Int,
) {
    data object Home: KakaoNav(
        route = "home",
        resourceId = R.string.home,
        icon = R.drawable.home,
    )

    data object Tag: KakaoNav(
        route = "tag",
        resourceId = R.string.tag,
        icon = R.drawable.tag,
    )
    data object List: KakaoNav(
        route = "list",
        resourceId = R.string.list,
        icon = R.drawable.list,
    )
    data object More: KakaoNav(
        route = "more",
        resourceId = R.string.more,
        icon = R.drawable.more,
    )
}