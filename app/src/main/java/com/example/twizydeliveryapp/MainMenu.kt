package com.example.twizydeliveryapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*
import com.example.twizydeliveryapp.ui.theme.textColor

val mainMenuStroke = BorderStroke(
    width = 1.dp,
    color = mainMenuStrokeColor
)

@Composable
fun MainMenu(viewModel: DeliveryViewModel, name: String, navController: NavController) {
    val isDeliveryStart = viewModel.isActive.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainHeader()
        Greeting(name = name)
        MainBody(isDeliveryStart.value, navController)
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
                fontSize = mediumText,
                color = textColor,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MainBody(isDeliverStart: Boolean, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        DeliveryStatusSummary(isDeliverStart, navController)
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
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            MobilityStatusSummary()
        }
        Button(
            onClick = { navController.navigate("driverStatus") },
            modifier = Modifier
                .weight(1f)
                .height(160.dp)
                .padding(start = 8.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            DriverStatusSummary()
        }
    }
    Button(
        onClick = { /**/ },
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
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
fun DeliveryStatusSummary(isDeliverStart: Boolean, navController: NavController) {
    if (isDeliverStart) {
        Button(
            onClick = {
                navController.navigate("deliveryStatus")
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 32.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 0.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.wrapContentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.thumbnail_map),
                    contentDescription = "map",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.width(10000.dp)
                )
                Text(text = "총 3개의 상품을 배송 중입니다.", modifier = Modifier.padding(16.dp), fontSize = smallMediumText)
            }
        }
    } else {
        Button(
            onClick = {
                navController.navigate("deliveryStatus")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(horizontal = 32.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "배송 상태", color = titleTextColor)
                Text(text = "배정된 상품이 없습니다.")
            }
        }
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
        MainMenu(DeliveryViewModel(),"Android", rememberNavController())
    }
}