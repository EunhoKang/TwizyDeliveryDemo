package com.example.twizydeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
    private val deliveryViewModel: DeliveryViewModel by viewModels()
    private val driverStatusViewModel: DriverStatusViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TwizyDeliveryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor
                ) {
                    MainScreen(deliveryViewModel, driverStatusViewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(deliveryViewModel: DeliveryViewModel, driverStatusViewModel: DriverStatusViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainMenu(deliveryViewModel,"김배달", navController)
        }
        composable("mobilityStatus") {
            MobilityStatus(navController)
        }
        composable("driverStatus") {
            DriverStatus(driverStatusViewModel, navController)
        }
        composable("deliveryStatus") {
            DeliveryStatus(deliveryViewModel, navController)
        }
        composable("deliveryStatusDetail") {
            DeliveryStatusDetail(deliveryViewModel, navController)
        }
        composable("deliveryNavigation") {
            DeliveryNavigation(deliveryViewModel, navController)
        }
        composable("deliveryList") {
            DeliveryList(deliveryViewModel, navController)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF2D2D2D)
@Composable
fun Preview() {
    TwizyDeliveryAppTheme {
        MainScreen(DeliveryViewModel(), DriverStatusViewModel())
    }
}