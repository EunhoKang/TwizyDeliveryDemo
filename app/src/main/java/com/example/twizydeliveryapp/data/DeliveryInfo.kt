package com.example.twizydeliveryapp.data

enum class DeliveryType {
    DELIVERY,
    RETURN
}

data class DeliveryInfo(
    val name: String,
    val location: String,
    val locationDetail: String,
    val deliveryNumber: String,
    val deliveryType: DeliveryType
)
