package com.example.pw3_11_

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.exp
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.PI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SolarCalculatorApp()
        }
    }
}

@Composable
fun SolarCalculatorApp() {

    // визначаю змінні
    var Pc by remember { mutableStateOf("") }
    var energy_price by remember { mutableStateOf("") }
    var sigma by remember { mutableStateOf("") }

    var revenue by remember { mutableStateOf("") }
    var fine by remember { mutableStateOf("") }
    var profit by remember { mutableStateOf("") }


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = Pc,
            onValueChange = { Pc = it },
            label = { Text("Середньодобова потужність, Pc (МВт)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = energy_price,
            onValueChange = { energy_price = it },
            label = { Text("Вартість електроенергії, В (грн/кВт*год)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = sigma,
            onValueChange = { sigma = it },
            label = { Text("Cередньоквадратичне відхилення, σ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // кнопка для виконання обчислень
        Button(
            onClick = {
                val power_average = Pc.toDoubleOrNull() ?: 0.0
                val price = energy_price.toDoubleOrNull() ?: 0.0
                val deviation = sigma.toDoubleOrNull() ?: 0.0

                // частка енергії, що генерується без дизбалансів
                var noImbalancesEnergyShare = integration(power_average, 5000,  deviation)
                var _revenue = power_average * 24 * noImbalancesEnergyShare * price
                var _fine = power_average * 24 * (1-noImbalancesEnergyShare) * price
                var _profit = _revenue - _fine

                revenue = _revenue.toString()
                fine = _fine.toString()
                profit = _profit.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // вивід результатів
        Text(text = "Дохід: " + revenue, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Штраф: " + fine, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Прибуток: " + profit, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        val conclusion = if ((profit.toDoubleOrNull() ?: 0.0) > 0) {
            "Модель є рентабельна"
        } else {
            "Модель не є рентабельна"
        }
        profit.toDoubleOrNull()?.let {
            Text(text = "Висновок: " + conclusion, style = MaterialTheme.typography.bodyLarge)
        }

    }
}

// нормальний закон розподілу потужності
fun normalPowerDistributionLaw(p: Double, pC: Double, sigma1: Double): Double {
    val coefficient = 1 / (sigma1 * sqrt(2 * PI))
    val exponent = -(p - pC).pow(2) / (2 * sigma1.pow(2))
    return coefficient * exp(exponent)
}

// частка енергії, що генерується без дизбалансів
fun integration(
    power_average: Double,
    numSteps: Int,
    deviation: Double
): Double {
    // знаходжу межі
    var lowerBound = power_average - power_average * 0.05
    var upperBound = power_average + power_average * 0.05
    val stepSize = (upperBound - lowerBound) / numSteps
    var res = 0.0

    // інтегрую
    for (i in 0 until numSteps) {
        val p1 = lowerBound + i * stepSize
        val p2 = p1 + stepSize
        val npdl1 = normalPowerDistributionLaw(p1, power_average, deviation)
        val npdl2 = normalPowerDistributionLaw(p2, power_average, deviation)
        res += 0.5 * (npdl1 + npdl2) * stepSize
    }

    return res
}

@Preview(showBackground = true)
@Composable
fun SolarCalculatorAppPreview() {
    SolarCalculatorApp()
}
