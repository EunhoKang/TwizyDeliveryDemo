package com.example.twizydeliveryapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.data.DeliverySetInfo
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun DeliveryStatus(viewModel: DeliveryViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeliveryStatusHeader(navController)
        DeliveryStatusBody(viewModel, navController)
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

@Composable
fun DeliveryStatusBody(viewModel: DeliveryViewModel, navController: NavController) {
    Column {
        DeliveryStatusTabRow(viewModel, listOf("배송 요청", "배송 중", "배송 완료"))
        DeliverySetList(viewModel, navController)
    }
}

@Composable
fun DeliveryStatusTabRow(viewModel: DeliveryViewModel, titles: List<String>) {
    var state by remember { mutableStateOf(0) }
    val lists = viewModel.deliverySets.collectAsState()
    TabRow(
        selectedTabIndex = state,
        containerColor = tabRowColor,
        contentColor = textColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[state]),
                color = textColor
            )
        },
        modifier = Modifier.padding(bottom = 16.dp)
    ) {
        titles.forEachIndexed { index, title ->
            Tab(
                selected = state == index,
                onClick = {
                    state = index
                    viewModel.setState(index)
                },
                selectedContentColor = textColor,
                unselectedContentColor = titleTextColor,
                text = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = title,
                            fontSize = smallMediumText,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Box(
                            modifier = Modifier
                                .width(30.dp)
                                .height(16.dp)
                                .clip(AbsoluteRoundedCornerShape(16.dp))
                                .background(if (state == index) textColor else titleTextColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = lists.value[index].count().toString(), color = backgroundColor)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun DeliverySetList(viewModel: DeliveryViewModel, navController: NavController) {
    val state = viewModel.currentState.collectAsState()
    val lists = viewModel.deliverySets.collectAsState()
    LazyColumn(contentPadding = PaddingValues(16.dp, 8.dp)) {
        items(lists.value[state.value].count()) {
            when(state.value) {
                1 -> DeliverySetItemGoing(lists.value[state.value][it], navController)
                else -> DeliverySetItem(lists.value[state.value][it], navController)
            }
        }
    }
}

@Composable
fun DeliverySetItem(info: DeliverySetInfo, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("deliveryStatusDetail")
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
        contentPadding = PaddingValues(all = 16.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "${info.remainHour}시간 ${info.remainMinute}분 후 마감", color = alertRed)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = info.companyName,
                    fontSize = smallMediumText,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "${info.distance}km", color = titleTextColor, fontSize = smallText)
            }
            Divider(Modifier.padding(vertical = 16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "배송 수량",
                    fontSize = smallMediumText,
                    color = titleTextColor,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "${info.packageCount}개", fontSize = smallMediumText)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "도착 시간",
                    fontSize = smallMediumText,
                    color = titleTextColor,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = info.arrivalTime, fontSize = smallMediumText)
            }
        }
    }
}

@Composable
fun DeliverySetItemGoing(info: DeliverySetInfo, navController: NavController) {
    Button(
        onClick = {
            navController.navigate("deliveryStatusDetail")
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 16.dp),
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
        contentPadding = PaddingValues(all = 8.dp),
        shape = MaterialTheme.shapes.small
    ) {
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
            Divider(color = titleTextColor)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "현재 배송지",
                    fontSize = smallMediumText,
                    color = titleTextColor,
                    modifier = Modifier.padding(end = 8.dp, bottom = 16.dp)
                )
                DeliveryItem(info.deliveries[0])
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DeliveryStatusPreview() {
    TwizyDeliveryAppTheme {
        DeliveryStatus(DeliveryViewModel(), rememberNavController())
    }
}