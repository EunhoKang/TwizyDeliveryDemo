package com.example.twizydeliveryapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme

@Composable
fun MobilityStatus(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "hi")
    }
}



@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun MobilityStatusPreview() {
    TwizyDeliveryAppTheme {
        MobilityStatus(rememberNavController())
    }
}