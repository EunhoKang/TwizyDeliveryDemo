package com.example.twizydeliveryapp

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
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
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun DriverStatus(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DriverStatusHeader(navController)
        DriverStatusBody()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /*Data changed*/ },
            colors = ButtonDefaults.buttonColors(buttonBlue),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "휴식 시작하기", color = textColor, fontSize = smallMediumText)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverStatusHeader(navController: NavController) {
    CenterAlignedTopAppBar(
        title = { Text(text = "운전자 상태", color = textColor) },
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

@OptIn(ExperimentalTextApi::class)
@Composable
fun DriverStatusBody() {
    val textMeasurer = rememberTextMeasurer()
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Canvas(
            modifier = Modifier.size(width = 320.dp, height = 320.dp),
            onDraw = {
                val sizeArc = size / 1.25F
                drawArc(
                    color = statusBlack,
                    startAngle = -170f,
                    sweepAngle = 160f,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - sizeArc.width) / 2f,
                        (size.height - sizeArc.height) / 2f - 50
                    ),
                    size = sizeArc,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )

                drawArc(
                    brush = Brush.linearGradient(
                        colors = listOf(gradiantGreenFirst, gradiantGreenSecond),
                        start = Offset.Zero,
                        end = Offset.Infinite,
                    ),
                    startAngle = -170f,
                    sweepAngle = 120f,
                    useCenter = false,
                    topLeft = Offset(
                        (size.width - sizeArc.width) / 2f,
                        (size.height - sizeArc.height) / 2f - 50
                    ),
                    size = sizeArc,
                    style = Stroke(width = 20f, cap = StrokeCap.Round)
                )

                drawText(
                    textMeasurer = textMeasurer,
                    style = TextStyle(color = titleTextColor, fontSize = smallText),
                    topLeft = Offset(
                        (size.width - sizeArc.width / 5f * 2f) / 2f,
                        (size.height - sizeArc.height / 2f) / 2f - 75
                    ),
                    text = "운전자 주의 수준"
                )

                drawText(
                    textMeasurer = textMeasurer,
                    style = TextStyle(color = textColor, fontSize = bigText),
                    topLeft = Offset(
                        (size.width - sizeArc.width / 5f * 1f) / 2f,
                        (size.height - sizeArc.height / 7f * 2f) / 2f - 75
                    ),
                    text = "좋음"
                )
            })
        Column(
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.breath_full),
                contentDescription = "breath",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(id = R.drawable.heart_full),
                contentDescription = "heart",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun TitleAndDescriptionWithIconVertical(title: String, description: String, icon: Int) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            color = titleTextColor,
            fontSize = smallText,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "breath",
                modifier = Modifier
                    .size(width = 18.dp, height = 18.dp),
                tint = textColor
            )
            Text(
                text = description,
                color = textColor,
                fontSize = bigText,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DriverStatusPreview() {
    TwizyDeliveryAppTheme {
        DriverStatus(rememberNavController())
    }
}