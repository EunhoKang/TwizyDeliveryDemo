package com.example.twizydeliveryapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.greetingShapeColor
import com.example.twizydeliveryapp.ui.theme.textColor
import com.example.twizydeliveryapp.ui.theme.topAppBarColor

@Composable
fun DeliveryStatus(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryStatusHeader(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryStatusHeader(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "배송 상태", color = textColor) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = topAppBarColor),
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = stringResource(R.string.mainmenu_description_notification),
                    tint = greetingShapeColor
                )
            }
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DeliveryStatusPreview() {
    TwizyDeliveryAppTheme {
        DeliveryStatus(rememberNavController())
    }
}