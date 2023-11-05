package com.example.twizydeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.twizydeliveryapp.data.DeliverySetInfo
import com.example.twizydeliveryapp.ui.theme.TwizyDeliveryAppTheme
import com.example.twizydeliveryapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwizyDeliveryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainMenu("김배달", navController)
        }
        composable("mobilityStatus") {
            MobilityStatus(navController)
        }
        composable("driverStatus") {
            DriverStatus(navController)
        }
        composable("deliveryStatus") {
            DeliveryStatus(navController)
        }
        composable("deliveryStatusDetail") {
            DeliveryStatusDetail(DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00"), navController)
        }
        composable("deliveryNavigation") {
            DeliveryNavigation(true, navController)
        }
        composable("deliveryList") {
            DeliveryList(DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00"), navController)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun Preview() {
    TwizyDeliveryAppTheme {
        MainScreen()
    }
}