package com.example.twizydeliveryapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun MobilityStatus(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MobilityStatusHeader(navController)
        MobilityStatusBody()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobilityStatusHeader(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "모빌리티 상태", color = textColor) },
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
fun MobilityStatusBody() {
    Image(
        painter = painterResource(id = R.drawable.mobility_example),
        contentDescription = "mobilityExample"
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(statusBlue)
        )
        Text(
            text = "트위지 라이프",
            fontSize = mediumText,
            color = textColor,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = "123가4567",
            fontSize = mediumText,
            color = titleTextColor
        )
    }
    Row(
        modifier = Modifier.padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TitleAndDescriptionVertical("주행 가능거리", "23km")
        VerticalDivider(64)
        TitleAndDescriptionVertical("AVMS 상태", "정상 작동")
        VerticalDivider(64)
        TitleAndDescriptionVertical("공조 상태", "23.0℃")
    }
    Button(
        onClick = { /**/ },
        modifier = Modifier
            .height(160.dp)
            .padding(horizontal = 16.dp),
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
        contentPadding = PaddingValues(all = 16.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        MobilityStatusReport()
    }
}

@Composable
fun TitleAndDescriptionVertical(title: String, description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = titleTextColor,
            fontSize = smallText,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(text = description, color = textColor, fontSize = mediumText)
    }
}

@Composable
fun VerticalDivider(length: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Divider(
            color = titleTextColor,
            thickness = 2.dp,
            modifier = Modifier
                .size(width = 1.dp, height = length.dp)
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun MobilityStatusReport() {
    val textMeasurer = rememberTextMeasurer()
    Row {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.4f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "점검 리포트",
                fontSize = mediumText,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Row {
                Text(text = "기본점검", fontSize = smallText, modifier = Modifier
                    .width(64.dp)
                    .padding(end = 8.dp))
                Text(text = "양호", color = titleTextColor, fontSize = smallText)
            }
            Row {
                Text(text = "배터리", fontSize = smallText, modifier = Modifier
                    .width(64.dp)
                    .padding(end = 8.dp))
                Text(text = "배터리 교체 완료", color = titleTextColor, fontSize = smallText)
            }
            Row {
                Text(text = "브레이크", fontSize = smallText, modifier = Modifier
                    .width(64.dp)
                    .padding(end = 8.dp))
                Text(text = "브레이크 액 교체 완료", color = titleTextColor, fontSize = smallText)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.6f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "2023.10.16 최근 점검",
                color = titleTextColor,
                modifier = Modifier.padding(bottom = 16.dp),
                fontSize = extraSmallText
            )
            Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                val sizeArc = size / 1.75F
                drawArc(
                    color = statusBlack,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset((size.width - sizeArc.width) / 2f, (size.height - sizeArc.height) / 2f),
                    size = sizeArc,
                    style = Stroke(width = 20f)
                )

                drawArc(
                    color = statusBlue,
                    startAngle = -90f,
                    sweepAngle = 300f,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - sizeArc.width) / 2f,
                        (size.height - sizeArc.height) / 2f
                    ),
                    size = sizeArc,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )

                drawText(
                    textMeasurer = textMeasurer,
                    style = TextStyle(color = textColor, fontSize = mediumText),
                    topLeft = Offset(
                        (size.width - sizeArc.width / 5f * 2f) / 2f,
                        (size.height - sizeArc.height / 2f) / 2f
                    ),
                    text = "90"
                )
            })
        }
    }
    Text(text = "자세히 보기")
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun MobilityStatusPreview() {
    TwizyDeliveryAppTheme {
        MobilityStatus(rememberNavController())
    }
}