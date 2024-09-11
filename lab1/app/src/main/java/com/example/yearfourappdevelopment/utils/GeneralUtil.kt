package com.example.yearfourappdevelopment.utils

fun toDoubleOrZero(value: String): Double {
    val converted = value.toDoubleOrNull()
    if (converted == null) {
        return 0.0
    }
    return converted
}