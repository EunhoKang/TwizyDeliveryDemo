package com.example.twizydeliveryapp

import androidx.lifecycle.ViewModel
import com.example.twizydeliveryapp.data.DeliveryInfo
import com.example.twizydeliveryapp.data.DeliverySetInfo
import com.example.twizydeliveryapp.data.DeliveryType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeliveryViewModel : ViewModel() {
    private var _isActive = MutableStateFlow(false)
    val isActive: StateFlow<Boolean> = _isActive

    private var _currentState = MutableStateFlow(0)
    val currentState: StateFlow<Int> = _currentState

    private val items = listOf(
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.RETURN
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        ),
        DeliveryInfo(
            "홍길*",
            "상록구 항가울로28 (e편한세상상록)",
            "102동 1101호",
            "592301940502",
            DeliveryType.DELIVERY
        )
    )

    private var _deliverySets = MutableStateFlow(
        listOf(
            listOf(
                DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items),
                DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items)
            ), listOf(), listOf()
        )
    )
    val deliverySets: StateFlow<List<List<DeliverySetInfo>>> = _deliverySets

    fun activateDelivery() {
        _isActive.value = true
        _deliverySets.value = listOf(
            listOf(
                DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items)
            ), listOf(DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items)), listOf()
        )
    }

    fun finishOne() {
        _deliverySets.value = listOf(
            listOf(
                DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items.drop(1))
            ), listOf(DeliverySetInfo(7, 29, 1.2f, "CJ대한통운 사일대리점", 10, "18:00", items)), listOf()
        )
    }

    fun setState(state: Int) {
        _currentState.value = state
    }
}