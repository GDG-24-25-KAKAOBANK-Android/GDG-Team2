package com.gdg.kakaobank.presentation

import MoreScreen
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.gdg.kakaobank.ui.theme.KakaoBankTheme
import com.gdg.kakaobank.presentation.navigator.KakaoNav
import com.gdg.kakaobank.ui.theme.B4_R
import com.gdg.kakaobank.ui.theme.Dark_Gray
import com.gdg.kakaobank.ui.theme.Gray
import com.gdg.kakaobank.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // 스플래시 화면 설치
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            KakaoBankTheme {
                MainScreen()
                BottomSheetWithBackHandler()
            }
        }

        // 스플래시 화면 유지 시간 설정 (2.5초)
        splashScreen.setKeepOnScreenCondition {
            Handler(Looper.getMainLooper()).postDelayed({
                splashScreen.setKeepOnScreenCondition { false }
            }, 2500)
            true
        }
    }
}

@Composable
fun MainScreen() {
    val items: List<KakaoNav> = listOf(
        KakaoNav.Home,
        KakaoNav.Tag,
        KakaoNav.List,
        KakaoNav.More,
    )
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
                BottomNavigation(
                    backgroundColor = White,
                    modifier = Modifier.padding(bottom = 30.dp)
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    items.forEach { screen ->
                        NavigationBarItem(
                            interactionSource = NoRippleInteractionSource,
                            label = {
                                Text(
                                    text = stringResource(id = screen.resourceId),
                                    style = B4_R
                                )
                            },
                            selected = currentRoute == screen.route,
                            colors = NavigationBarItemDefaults.colors(
                                selectedTextColor = Dark_Gray,
                                selectedIconColor = Dark_Gray,
                                unselectedTextColor = Gray,
                                unselectedIconColor = Gray,
                                indicatorColor = White
                            ),
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = screen.route,
                                    modifier = Modifier.size(24.dp) // 아이콘 크기 설정

                                )
                            },
                        )
                    }
                }

        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = KakaoNav.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = KakaoNav.Home.route) { HomeScreen(navController = navController) }
            composable(route = KakaoNav.Tag.route) { TagScreen() }
            composable(route = KakaoNav.List.route) { ListScreen() }
            composable(route = KakaoNav.More.route) { MoreScreen() }
            composable(route = "transfer") {
                TransferScreen(
                    onCloseClick = { navController.popBackStack() },
                    onNextClick = { receiverName ->
                        navController.navigate("TransferDetails/$receiverName")
                    }
                )
            }

            composable(route = "TransferDetails/{receiverName}") { backStackEntry ->
                val receiverName = backStackEntry.arguments?.getString("receiverName") ?: ""
                TransferDetailScreen(
                    receiverName = receiverName,
                    onCancelClick = { navController.popBackStack() },
                    onNextClick = {
                        navController.navigate("TransferComplete/$receiverName")
                    },
                    navController = navController
                )
            }

            composable(route = "TransferComplete/{receiverName}") { backStackEntry ->
                val receiverName = backStackEntry.arguments?.getString("receiverName") ?: ""
                TransferCompleteScreen(
                    receiverName = receiverName,
                    onBackClick = {
                        navController.navigate(KakaoNav.Home.route) {
                            popUpTo(KakaoNav.Home.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    }
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KakaoBankTheme {
        MainScreen()
    }
}