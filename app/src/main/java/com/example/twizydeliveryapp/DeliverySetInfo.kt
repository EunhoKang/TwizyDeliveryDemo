package com.example.twizydeliveryapp

data class DeliverySetInfo(
    val remainHour: Int,
    val remainMinute: Int,
    val distance: Float,
    val companyName: String,
    val packageCount: Int,
    val arrivalTime: String,
)
