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
            MainMenu("Android", navController)
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
            DeliveryStatusDetail(navController)
        }
        composable("deliveryNavigation") {
            DeliveryNavigation(navController)
        }
        composable("deliveryList") {
            DeliveryList(navController)
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