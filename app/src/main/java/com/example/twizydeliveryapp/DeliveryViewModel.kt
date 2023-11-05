package com.example.twizydeliveryapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeliveryViewModel: ViewModel() {
    private var _isActive = MutableStateFlow(false)
    val isActive: StateFlow<Boolean> = _isActive

    fun activateDelivery() {
        _isActive.value = true
    }
}