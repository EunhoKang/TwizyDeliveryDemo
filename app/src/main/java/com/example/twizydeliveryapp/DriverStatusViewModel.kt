package com.example.twizydeliveryapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twizydeliveryapp.data.XandarAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DriverStatusViewModel : ViewModel() {
    data class DriverData(
        val rr: List<Int> = listOf(0, 0, 0, 0, 0), //분당 호흡수
        val bpm: List<Int> = listOf(0, 0, 0, 0, 0), //분당 심박수
        val heartRateLevel: Int = 0, //심박 수준
        val respiratoryLevel: Int = 0, //호흡 수준
        val movementScore: Int = 0
    )

    private var _currentState = MutableStateFlow(DriverData())
    val currentState : StateFlow<DriverData> = _currentState

    init {
        viewModelScope.launch {
            startLoop()
        }
    }

    suspend fun startLoop() {
        while (true) {
            delay(1000)
            getData()
        }
    }

    private suspend fun getData() {
        try {
            val data = XandarAPI.xandarAPI.getInfo()
            val values = data.body()?.split('/') ?: return
            _currentState.value = DriverData(
                _currentState.value.rr.drop(1).plus(values[1].toInt()),
                _currentState.value.bpm.drop(1).plus(values[0].toInt()),
                values[2].toInt(),
                values[3].toInt(),
                values[4].toInt()
            )
        } catch (e: Exception) {
            Log.e("Not Connected", "Server Offline : $e")
        }
    }
}