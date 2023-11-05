package com.example.twizydeliveryapp

import androidx.lifecycle.ViewModel
import com.example.twizydeliveryapp.data.DeliverySetInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeliveryViewModel: ViewModel() {
    private var _isActive = MutableStateFlow(false)
    val isActive: StateFlow<Boolean> = _isActive

    private var _currentState = MutableStateFlow(0)
    val currentState: StateFlow<Int> = _currentState

    private var _deliverySets = MutableStateFlow(listOf(listOf(
        DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00"),
        DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00")
    ), listOf(), listOf()))
    val deliverySets: StateFlow<List<List<DeliverySetInfo>>> = _deliverySets

    fun activateDelivery() {
        _isActive.value = true
        _deliverySets.value = listOf(listOf(
            DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00")
        ), listOf(DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00")), listOf())
    }

    fun setState(state: Int) {
        _currentState.value = state
    }
}