package com.example.sih3

data class ResponseData(
    val TS: String,
    val humidity: String,
    val rain: Boolean,
    val soilmoisture: String,
    val temperature: String,
    val time: String
)