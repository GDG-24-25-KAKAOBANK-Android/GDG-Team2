package com.gdg.kakaobank

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gdg.kakaobank.ui.theme.KakaoBankTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen() // 스플래시 화면 설치
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 스플래시 화면 유지 시간 설정 (2.5초)
        splashScreen.setKeepOnScreenCondition {
            Handler(Looper.getMainLooper()).postDelayed({
                splashScreen.setKeepOnScreenCondition { false }
            }, 2500)
            true
        }

        setContent {
            KakaoBankTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KakaoBankTheme {
        Greeting("Android")
    }
}