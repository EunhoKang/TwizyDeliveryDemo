package com.example.twizydeliveryapp

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.data.DeliveryInfo
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

@Composable
fun DeliveryNavigation(viewModel: DeliveryViewModel, navController: NavController) {
    val items = viewModel.deliverySets.collectAsState()
    DeliveryNavigationBottomSheet(items.value[0][0].deliveries[0], navController)
}

@Composable
fun DeliveryNavigationBottomSheet(
    info: DeliveryInfo,
    navController: NavController
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.navigation_example),
            contentDescription = "navigator",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(10000.dp)
                .weight(1.5f)
        )
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = { navController.navigate("deliveryList") },
            colors = ButtonDefaults.buttonColors(backgroundColor),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = "location",
                        tint = titleTextColor
                    )
                    Text(text = info.location, color = titleTextColor, fontSize = smallText)
                }
                Divider(color = titleTextColor, thickness = 1.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.reset),
                            contentDescription = "reset",
                            tint = textColor,
                            modifier = Modifier.size(36.dp)
                        )
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "오후",
                                color = textColor,
                                fontSize = smallText,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(text = "03:22", color = textColor, fontSize = bigText)
                        }
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "620",
                                color = textColor,
                                fontSize = bigText,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(text = "m", color = textColor, fontSize = smallText)
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.more_vert),
                            contentDescription = "more",
                            tint = textColor,
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun DeliveryNavigationPreview() {
    TwizyDeliveryAppTheme {
        DeliveryNavigation(DeliveryViewModel(), rememberNavController())
    }
}