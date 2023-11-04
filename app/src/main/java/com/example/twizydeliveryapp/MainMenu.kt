package com.example.twizydeliveryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*
import com.example.twizydeliveryapp.ui.theme.textColor

val mainMenuStroke = BorderStroke(
    width = 1.dp,
    color = mainmenuStrokeColor
)

@Composable
fun MainMenu(name: String, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainHeader()
        Greeting(name = name)
        MainBody(navController)
    }
}

@Composable
fun MainHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End
    ) {
        IconButton(
            onClick = { /**/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.notification),
                contentDescription = stringResource(R.string.mainmenu_description_notification),
                tint = greetingShapeColor
            )
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(greetingShapeColor)
            )
            Text(
                text = stringResource(R.string.greeting, name),
                fontSize = greetingTextSize,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MainBody(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 32.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainmenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                DeliveryStatusSummary()
            }
        }
    }
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = {
                navController.navigate("mobilityStatus")
            },
            modifier = Modifier
                .weight(1f)
                .height(160.dp)
                .padding(end = 8.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainmenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            MobilityStatusSummary()
        }
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .weight(1f)
                .height(160.dp)
                .padding(start = 8.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainmenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            DriverStatusSummary()
        }
    }
    Button(
        onClick = { /**/ },
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainmenuButtonColor),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.setting),
                contentDescription = "setting",
                tint = greetingShapeColor,
                modifier = Modifier.height(20.dp)
            )
            Text(text = "추가 설정", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun DeliveryStatusSummary() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "배송 상태", color = titleTextColor)
        Text(text = "배정된 상품이 없습니다.")
    }
}

@Composable
fun MobilityStatusSummary() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "모빌리티 상태", color = titleTextColor, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "주행 가능 거리", fontSize = smallText)
        Text(text = "23 km", fontSize = bigText)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.battery),
                contentDescription = "battery",
                tint = greetingShapeColor,
                modifier = Modifier.height(12.dp)
            )
            Text(text = "62%", fontSize = smallText)
        }
    }
}

@Composable
fun DriverStatusSummary() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "운전자 상태", color = titleTextColor, modifier = Modifier.padding(bottom = 16.dp))
        Text(text = "운전자 주의 수준", fontSize = smallText)
        Text(text = "좋음", fontSize = bigText)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun MainMenuPreview() {
    TwizyDeliveryAppTheme {
        MainMenu("Android", rememberNavController())
    }
}