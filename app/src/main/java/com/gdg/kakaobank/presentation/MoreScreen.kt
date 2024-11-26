import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.kakaobank.R
import com.gdg.kakaobank.ui.theme.*
import androidx.compose.ui.window.DialogProperties
import androidx.compose.foundation.shape.RoundedCornerShape
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun MoreScreen() {
    val moreInfoList1 = listOf("서비스 이용약관", "개인정보 처리방침", "버전 정보")
    val moreInfoList2 = listOf("고객센터", "로그아웃", "회원 탈퇴")
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(top = 45.dp, start = 16.dp, end = 30.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp) //줄간격 속성
        ) {
            Text("눈송이님", style = H5_B)
            Text("카카오뱅크 서비스", style = H5_B, color = Main_Yellow)
            Text("관련 안내사항입니다", style = H5_B)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("이용안내", style = H6_SB)
        Spacer(modifier = Modifier.height(4.dp))
        moreInfoList1.forEach { info ->
            MoreInfo(text = info)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("기타", style = H6_SB)
        Spacer(modifier = Modifier.height(4.dp))
        //회원 탈퇴 및 로그아웃 팝업 알림 뜨도록 설정
        moreInfoList2.forEach { info ->
            if (info == "회원 탈퇴" || info == "로그아웃") {
                MoreInfo(text = info, onClick = {
                    showDialog = true
                    dialogType = info
                })
            } else {
                MoreInfo(text = info)
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = {
                Text(
                    "${dialogType} 하시겠습니까?",
                    style = H5_SB,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 30.dp, 16.dp, 10.dp)
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Gray, contentColor = White),
                        onClick = {
                            showDialog = false
                        },
                        modifier = Modifier.size(width = 110.dp, height = 45.dp)
                    ) {
                        Text("취소", style = H7_B)
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Main_Yellow, contentColor = Black),
                        onClick = {
                            showDialog = false
                            Toast.makeText(context, "${dialogType} 되었습니다", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(width = 110.dp, height = 45.dp)
                    ) {
                        Text(dialogType, style = H7_B)
                    }
                }
            },
            properties = DialogProperties(usePlatformDefaultWidth = false),
            containerColor = Light_Gray,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.size(width = 280.dp, height = 180.dp)
        )
    }
}

@Composable
fun MoreInfo(text: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(White)
            .padding(8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text, style = B1_SB, color = Dark_Gray)
        Image(painter = painterResource(id = R.drawable.icn_next), contentDescription = null)
    }
}

@Composable
@Preview(showBackground = true)
fun MoreScreenPreview() {
    MoreScreen()
}