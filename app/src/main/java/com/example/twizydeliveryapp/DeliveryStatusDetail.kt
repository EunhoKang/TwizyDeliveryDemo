package com.example.twizydeliveryapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.data.DeliverySetInfo
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.alertRed
import com.example.twizydeliveryapp.ui.theme.backgroundColor
import com.example.twizydeliveryapp.ui.theme.buttonBlue
import com.example.twizydeliveryapp.ui.theme.greetingShapeColor
import com.example.twizydeliveryapp.ui.theme.mediumText
import com.example.twizydeliveryapp.ui.theme.smallMediumText
import com.example.twizydeliveryapp.ui.theme.smallText
import com.example.twizydeliveryapp.ui.theme.textColor
import com.example.twizydeliveryapp.ui.theme.titleTextColor
import com.example.twizydeliveryapp.ui.theme.topAppBarColor

@Composable
fun DeliveryStatusDetail(viewModel: DeliveryViewModel, info: DeliverySetInfo, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryStatusDetailHeader(navController)
        DeliveryMap()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryStatusBottomSheet(viewModel, info, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryStatusBottomSheet(viewModel: DeliveryViewModel, info: DeliverySetInfo, navController: NavController) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(true)
    }

    if(openAlertDialog.value) {
        Column(verticalArrangement = Arrangement.Center) {
            AlertDialogForStart(
                onDismissRequest = { openAlertDialog.value = false },
                onConfirmation = {
                    viewModel.activateDelivery()
                    openAlertDialog.value = false
                }
            )
        }
    }

    Button(
        onClick = { isSheetOpen = true },
        colors = ButtonDefaults.buttonColors(buttonBlue),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "배송 받으러 가기", color = textColor, fontSize = smallMediumText)
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = backgroundColor,
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "${info.remainHour}시간 ${info.remainMinute}분 후 마감", color = alertRed)
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = info.companyName,
                        color = textColor,
                        fontSize = mediumText,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = "${info.distance}km", color = titleTextColor, fontSize = smallText)
                }
                Row(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "배송 수량",
                        fontSize = smallMediumText,
                        color = titleTextColor,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        text = "${info.packageCount}개",
                        color = textColor,
                        fontSize = smallMediumText,
                        modifier = Modifier.padding(end = 64.dp)
                    )
                    Text(
                        text = "도착 시간",
                        fontSize = smallMediumText,
                        color = titleTextColor,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(text = info.arrivalTime, color = textColor, fontSize = smallMediumText)
                }
            }
            Divider(color = titleTextColor, thickness = 1.dp)
            ClickableText(
                text = AnnotatedString("배송 목록 확인하기 △"),
                onClick = {
                    //isSheetOpen = false
                    navController.navigate("deliveryList")
                },
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    color = titleTextColor
                ),
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Button(
                onClick = {
                    openAlertDialog.value = true
                },
                colors = ButtonDefaults.buttonColors(buttonBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 36.dp)
            ) {
                Text(text = "배송 받으러 가기", color = textColor, fontSize = smallMediumText)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryStatusDetailHeader(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "배송 요청 상세", color = textColor) },
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

@Composable
fun DeliveryMap() {
    Image(
        painter = painterResource(id = R.drawable.map_example),
        contentDescription = "map",
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DeliveryStatusDetailPreview() {
    TwizyDeliveryAppTheme {
        DeliveryStatusDetail(
            DeliveryViewModel(),
            DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00"),
            rememberNavController()
        )
    }
}