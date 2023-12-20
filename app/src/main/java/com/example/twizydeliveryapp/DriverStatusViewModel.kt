package com.example.twizydeliveryapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DriverStatusViewModel : ViewModel() {
    data class DriverData(
        val rr: Int = 0, //분당 호흡수
        val bpm: Int = 0, //분당 심박수
        val heartRateLevel: Int = 0, //심박 수준
        val respiratoryLevel: Int = 0, //호흡 수준
        val movementScore: Int = 0
    )

    private var _currentState = MutableStateFlow(DriverData())
    val currentState : StateFlow<DriverData> = _currentState
}