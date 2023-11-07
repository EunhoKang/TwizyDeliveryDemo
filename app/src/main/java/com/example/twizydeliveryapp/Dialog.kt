package com.example.twizydeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.backgroundColor
import com.example.twizydeliveryapp.ui.theme.textColor

@Composable
fun DialogForFinish(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Box(modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .wrapContentSize()
        ) {
            Column(
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "배송을 완료했습니다!", color = textColor, fontWeight = FontWeight.Bold)
                Image(
                    painter = painterResource(id = R.drawable.finish),
                    contentDescription = "finish"
                )
                Text(text = "+ 500원", color = textColor)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DialogPreview() {
    TwizyDeliveryAppTheme {
        DialogForFinish(
            onDismissRequest = { /*TODO*/ }
        )
    }
}