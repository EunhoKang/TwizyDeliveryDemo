package com.example.twizydeliveryapp

import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
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
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun DriverStatus(viewModel: DriverStatusViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DriverStatusHeader(navController)
        DriverStatusBody(viewModel)
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
fun DriverStatusBody(
    viewModel: DriverStatusViewModel
) {
    val textMeasurer = rememberTextMeasurer()
    val data = viewModel.currentState.collectAsState()
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
                if (data.value.movementScore >= 100) {
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
                }

                drawText(
                    textMeasurer = textMeasurer,
                    style = TextStyle(color = textColor, fontSize = bigText),
                    topLeft = Offset(
                        (size.width - sizeArc.width / 5f * 1f) / 2f - if (data.value.movementScore >= 100) 0 else 120,
                        (size.height - sizeArc.height / 7f * 2f) / 2f - 75
                    ),
                    text = if (data.value.movementScore >= 100) "좋음" else "측정 중입니다"
                )
            })
        if (data.value.movementScore >= 100) UIOnRating(data.value) else UIOnNotRating(data.value)
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun UIOnRating(
    data: DriverStatusViewModel.DriverData
) {
    val textMeasurer = rememberTextMeasurer()
    Column(
        modifier = Modifier
            .padding(top = 150.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val xPoints = listOf(800f, 640f, 480f, 320f, 160f)
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(220.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.4f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.breath),
                            contentDescription = "breath"
                        )
                        Text(
                            text = "분당 호흡수:",
                            fontSize = smallMediumText,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(
                            text = "${if (data.rr.isEmpty()) 0 else data.rr.last()} 회",
                            fontSize = mediumText
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.6f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "자세히 보기 ⊳", color = titleTextColor)
                    }
                }
                Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                    listOf(0, 5, 10, 15).forEach {
                        drawText(
                            textMeasurer = textMeasurer,
                            style = TextStyle(color = titleTextColor, fontSize = smallMediumText),
                            topLeft = Offset(
                                0f,
                                360f - it.toFloat() * 20
                            ),
                            text = it.toString()
                        )
                    }
                    var targets = data.rr.takeLast(5)
                    while (targets.size < 5) {
                        targets = listOf(0).plus(targets)
                    }
                    targets = targets.reversed()
                    val botY = 460f
                    val topY = 40f
                    val startX = xPoints.last()
                    val gradientColors = listOf(statusBlue, Color.Transparent)
                    val gradientBrush = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(startX, topY),
                        end = Offset(startX, botY),
                        tileMode = TileMode.Clamp
                    )
                    drawRect(
                        brush = gradientBrush,
                        topLeft = Offset(160f, topY),
                        size = Size(640f, botY - topY)
                    )
                    targets.forEachIndexed { idx, it ->
                        if (idx != targets.size - 1) {
                            val path = Path()
                            path.moveTo(xPoints[idx] + 1, topY - 1) // Move to the top-left corner
                            path.lineTo(xPoints[idx + 1] - 1, topY - 1) // Draw top side
                            path.lineTo(xPoints[idx + 1], 390f - targets[idx + 1] * 20f) // Draw right side
                            path.lineTo(xPoints[idx], 390f - targets[idx] * 20f) // Draw left side
                            path.close()
                            drawPath(
                                path = path,
                                color = mainMenuButtonColor
                            )
                            drawLine(
                                color = textColor,
                                start = Offset(xPoints[idx], 390f - it * 20f),
                                end = Offset(
                                    xPoints[idx + 1],
                                    390f - targets[idx + 1] * 20f
                                ),
                                strokeWidth = 5f
                            )
                        }
                        drawCircle(
                            color = textColor,
                            radius = 10f,
                            center = Offset(xPoints[idx], 390f - it * 20f)
                        )
                    }
                    drawCircle(
                        color = textColor,
                        alpha = 0.5f,
                        radius = 20f,
                        center = Offset(xPoints.first(), 390f - targets.first() * 20f)
                    )
                })
            }
        }
        Button(
            onClick = { /**/ },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(220.dp),
            border = mainMenuStroke,
            colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
            contentPadding = PaddingValues(all = 16.dp),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1.4f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "breath"
                        )
                        Text(
                            text = "분당 심박수:",
                            fontSize = smallMediumText,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Text(
                            text = "${if (data.bpm.isEmpty()) 0 else data.bpm.last()} Bpm",
                            fontSize = mediumText
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.6f),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "자세히 보기 ⊳", color = titleTextColor)
                    }
                }
                Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                    listOf(0, 40, 80, 120).forEach {
                        drawText(
                            textMeasurer = textMeasurer,
                            style = TextStyle(color = titleTextColor, fontSize = smallMediumText),
                            topLeft = Offset(
                                0f,
                                340f - it * 2.5f
                            ),
                            text = it.toString()
                        )
                    }
                    var targets = data.bpm.takeLast(5)
                    while (targets.size < 5) {
                        targets = listOf(80).plus(targets)
                    }
                    targets = targets.reversed()
                    val botY = 460f
                    val topY = 40f
                    val startX = xPoints.last()
                    val gradientColors = listOf(statusBlue, Color.Transparent)
                    val gradientBrush = Brush.linearGradient(
                        colors = gradientColors,
                        start = Offset(startX, topY),
                        end = Offset(startX, botY),
                        tileMode = TileMode.Clamp
                    )
                    drawRect(
                        brush = gradientBrush,
                        topLeft = Offset(160f, topY),
                        size = Size(640f, botY - topY)
                    )
                    targets.forEachIndexed { idx, it ->
                        if (idx != targets.size - 1) {
                            val path = Path()
                            path.moveTo(xPoints[idx] + 1, topY - 1) // Move to the top-left corner
                            path.lineTo(xPoints[idx + 1] - 1, topY - 1) // Draw top side
                            path.lineTo(xPoints[idx + 1], 370f - targets[idx + 1] * 2.5f) // Draw right side
                            path.lineTo(xPoints[idx], 370f - targets[idx] * 2.5f) // Draw left side
                            path.close()
                            drawPath(
                                path = path,
                                color = mainMenuButtonColor
                            )
                            drawLine(
                                color = textColor,
                                start = Offset(xPoints[idx], 370f - it * 2.5f),
                                end = Offset(
                                    xPoints[idx + 1],
                                    370f - targets[idx + 1] * 2.5f
                                ),
                                strokeWidth = 5f
                            )
                        }
                        drawCircle(
                            color = textColor,
                            radius = 10f,
                            center = Offset(xPoints[idx], 370f - it * 2.5f)
                        )
                    }
                    drawCircle(
                        color = textColor,
                        alpha = 0.5f,
                        radius = 20f,
                        center = Offset(xPoints.first(), 370f - targets.first() * 2.5f)
                    )
                })
            }
        }
    }
}

@Composable
fun UIOnNotRating(
    data: DriverStatusViewModel.DriverData
) {
    Button(
        onClick = { /**/ },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 150.dp)
            .fillMaxWidth()
            .height(240.dp),
        border = mainMenuStroke,
        colors = ButtonDefaults.buttonColors(containerColor = mainMenuButtonColor),
        contentPadding = PaddingValues(all = 0.dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(100.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.breath),
                        contentDescription = "breath"
                    )
                    Text(text = "분당 호흡수", fontSize = smallMediumText)
                    Text(
                        text = "-",
                        fontSize = bigText,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.heart),
                        contentDescription = "heart"
                    )
                    Text(text = "분당 심박수", fontSize = smallMediumText)
                    Text(
                        text = "-",
                        fontSize = bigText,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
            Divider(modifier = Modifier.padding(bottom = 16.dp), color = titleTextColor)
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(100.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1.1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = "심박 수준", fontSize = smallText)
                    Text(text = "호흡 수준", fontSize = smallText)
                    Text(text = "Movement Score", fontSize = smallText)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.9f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = data.heartRateLevel.toString(),
                        color = titleTextColor,
                        fontSize = smallText
                    )
                    Text(
                        text = data.respiratoryLevel.toString(),
                        color = titleTextColor,
                        fontSize = smallText
                    )
                    Text(
                        text = data.movementScore.toString(),
                        color = titleTextColor,
                        fontSize = smallText
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DriverStatusPreview() {
    TwizyDeliveryAppTheme {
        DriverStatus(DriverStatusViewModel(), rememberNavController())
    }
}