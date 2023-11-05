package com.example.twizydeliveryapp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.backgroundColor
import com.example.twizydeliveryapp.ui.theme.buttonBlue
import com.example.twizydeliveryapp.ui.theme.textColor
import com.example.twizydeliveryapp.ui.theme.titleTextColor

@Composable
fun AlertDialogForStart(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "아래 유의사항을 꼭 확인해주세요")
        },
        text = {
            Text(text = "유의사항에 들어갈 텍스트는 직접 짜주시면 좋을 것 같습니다.\n(차량 보유상태, 시간, 노쇼나 중도 포기 시 패널티 등등에 대한 안내)")
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                },
                colors = ButtonDefaults.buttonColors(buttonBlue, titleTextColor)
            ) {
                Text("배송 받으러 가기", color = textColor)
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("닫기", color = buttonBlue)
            }
        },
        containerColor = backgroundColor,
        titleContentColor = textColor,
        textContentColor = textColor

    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun AlertDialogPreview() {
    TwizyDeliveryAppTheme {
        AlertDialogForStart(
            onDismissRequest = { /*TODO*/ },
            onConfirmation = { /*TODO*/ }
        )
    }
}