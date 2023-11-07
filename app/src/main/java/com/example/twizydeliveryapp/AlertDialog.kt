package com.example.twizydeliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun AlertDialogForStart(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    var isEnabled = remember { mutableStateOf(false) }
    AlertDialog(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "아래 유의사항을 꼭 확인해주세요",
                    fontWeight = FontWeight.Bold,
                    fontSize = smallMediumText
                )
            }
        },
        text = {
            Column() {
                Text(text = "차량을 보유하지 않거나 배송물품을 받으러 오지 않는 경우 향후 활동에 제한될 수 있으며 발생하는 문제에 대해선 당사는 책임지지 않습니다.")
                Row(
                    modifier = Modifier.padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = isEnabled.value,
                        onClick = { isEnabled.value = true },
                        colors = RadioButtonDefaults.colors(buttonBlue)
                    )
                    Text(text = "위 유의사항을 확인했음에 동의합니다.", color = titleTextColor)
                }
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                },
                enabled = isEnabled.value,
                colors = ButtonDefaults.buttonColors(buttonBlue, titleTextColor, titleTextColor)
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
                Text("닫기", color = titleTextColor)
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