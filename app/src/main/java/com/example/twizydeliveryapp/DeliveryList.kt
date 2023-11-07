package com.example.twizydeliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.data.DeliveryInfo
import com.example.twizydeliveryapp.data.DeliverySetInfo
import com.example.twizydeliveryapp.data.DeliveryType
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryList(viewModel: DeliveryViewModel, navController: NavController) {
    val openAlertDialog = remember { mutableStateOf(false) }
    val openBottomSheet = remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val isFinished = remember { mutableStateOf(false) }

    val items = viewModel.deliverySets.collectAsState()
    val isActive = viewModel.isActive.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryListHeader(navController)
        if (isActive.value) DeliveryActiveList(
            { openBottomSheet.value = true },
            items.value[0][0].deliveries
        )
        else DeliveryListBody(items.value[0][0])
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isActive.value) {
            if (openBottomSheet.value) {
                ModalBottomSheet(
                    containerColor = backgroundColor,
                    sheetState = sheetState,
                    onDismissRequest = {
                        openBottomSheet.value = false
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "마지막으로 배송정보를 확인 후\n고객에게 배송 완료 문자를 보내세요",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = mediumText,
                            modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                        )
                        Divider(color = titleTextColor, thickness = 1.dp)
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            DeliveryItem(items.value[0][0].deliveries[0])
                        }
                        Row(
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, bottom = 36.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                ClickableText(
                                    text = AnnotatedString("닫기"),
                                    style = TextStyle(fontSize = smallText, color = titleTextColor),
                                    onClick = {
                                        openBottomSheet.value = false
                                    })
                            }
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    onClick = {
                                        isFinished.value = true
                                    },
                                    contentPadding = PaddingValues(4.dp),
                                    colors = ButtonDefaults.buttonColors(buttonBlue),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 16.dp)
                                ) {
                                    Text(
                                        text = "배송 완료 문자 보내기",
                                        color = textColor,
                                        fontSize = smallText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (openAlertDialog.value) {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.Center) {
                    AlertDialogForStart(
                        onDismissRequest = {
                            openAlertDialog.value = false
                        },
                        onConfirmation = {
                            viewModel.activateDelivery()
                            openAlertDialog.value = false
                            navController.popBackStack()
                        }
                    )
                }
            }
            Button(
                onClick = {
                    openAlertDialog.value = true
                },
                colors = ButtonDefaults.buttonColors(buttonBlue),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "배송 받으러 가기", color = textColor, fontSize = smallMediumText)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isFinished.value) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                DialogForFinish {
                    isFinished.value = false
                    openBottomSheet.value = false
                    viewModel.finishOne()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryListHeader(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "배송 목록", color = textColor) },
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
fun DeliveryActiveList(callback: () -> Unit, list: List<DeliveryInfo>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "현재 배송중인 물품",
            color = titleTextColor,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        DeliveryItem(list[0])
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = {
                    callback.invoke()
                          },
                colors = ButtonDefaults.buttonColors(buttonGrey)
            ) {
                Text(text = "배송 완료", color = textColor)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "배송 예정 목록", color = titleTextColor)
    }
    Divider(color = titleTextColor)
    DeliveryItemList(list.drop(1))
}

@Composable
fun DeliveryListBody(info: DeliverySetInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
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
    Divider(color = textColor, thickness = 2.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "AI 추천순▾",
            color = textColor,
            fontSize = smallText,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
    Divider(color = titleTextColor)
    DeliveryItemList(info.deliveries)
}

@Composable
fun DeliveryItemList(list: List<DeliveryInfo>) {
    LazyColumn {
        items(list.count()) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                DeliveryItem(list[it])
            }
            Divider(color = titleTextColor)
        }
    }
}

@Composable
fun DeliveryItem(info: DeliveryInfo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(1.5f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = info.name, color = titleTextColor, fontSize = smallText)
            Text(text = info.location, color = textColor, fontSize = smallMediumText)
            Text(text = info.locationDetail, color = textColor, fontSize = smallMediumText)
            Text(text = info.deliveryNumber, color = titleTextColor, fontSize = smallText)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .weight(0.5f),
            horizontalAlignment = Alignment.End
        ) {
            when (info.deliveryType) {
                DeliveryType.DELIVERY -> Text(
                    text = "배송",
                    color = statusBlue,
                    fontSize = smallMediumText
                )

                DeliveryType.RETURN -> Text(
                    text = "반품",
                    color = alertRed,
                    fontSize = smallMediumText
                )
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DeliveryListPreview() {
    TwizyDeliveryAppTheme {
        DeliveryList(DeliveryViewModel(), rememberNavController())
    }
}