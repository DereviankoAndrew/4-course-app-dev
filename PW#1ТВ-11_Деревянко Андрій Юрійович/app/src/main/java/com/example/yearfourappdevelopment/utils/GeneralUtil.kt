package com.example.yearfourappdevelopment.utils

import java.math.BigDecimal
import java.math.RoundingMode

// конвертує стрінг в дабл або повертає 0
fun toDoubleOrZero(value: String): Double {
    val converted = value.toDoubleOrNull()
    if (converted == null) {
        return 0.0
    }
    return converted
}

// закруглює Double до значення з двома числами після крапки і конвертує в стрінг
fun roundTo2Decimals(value: Double): String {
    return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()
}