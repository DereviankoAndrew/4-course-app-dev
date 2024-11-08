package com.example.lab5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import androidx.compose.ui.Modifier

data class EquipmentReliability(
    val failureRate: Double,
    val averageRepairTime: Int,
    val frequency: Double,
    val averageRecoveryTime: Int?
)

val data = mapOf(
    "T-110 kV" to EquipmentReliability(0.015, 100, 1.0, 43),
    "T-35 kV" to EquipmentReliability(0.02, 80, 1.0, 28),
    "T-10 kV (кабельна мережа 10 кВ)" to EquipmentReliability(0.005, 60, 0.5, 10),
    "T-10 kV (повітряна мережа 10 кВ)" to EquipmentReliability(0.05, 60, 0.5, 10),
    "B-110 kV (елегазовий)" to EquipmentReliability(0.01, 30, 0.1, 30),
    "B-10 kV (малолойний)" to EquipmentReliability(0.02, 15, 0.33, 15),
    "B-10 kV (вакуумний)" to EquipmentReliability(0.05, 15, 0.33, 15),
    "Збірні шини 10 кВ на 1 приєднання" to EquipmentReliability(0.03, 2, 0.33, 15),
    "АВ-0,38 кВ" to EquipmentReliability(0.05, 20, 1.0, 15),
    "ЕД 6,10 кВ" to EquipmentReliability(0.1, 50, 0.5, 0),
    "ЕД 0,38 кВ" to EquipmentReliability(0.1, 50, 0.5, 0),
    "ПЛ-110 кВ" to EquipmentReliability(0.007, 10, 0.167, 35),
    "ПЛ-35 кВ" to EquipmentReliability(0.02, 8, 0.167, 35),
    "ПЛ-10 кВ" to EquipmentReliability(0.02, 10, 0.167, 35),
    "КЛ-10 кВ (траншея)" to EquipmentReliability(0.03, 44, 1.0, 9),
    "КЛ-10 кВ (кабельний канал)" to EquipmentReliability(0.005, 18, 1.0, 9)
)

@Composable
fun CalculatorOne(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current


    val amountMap = mapOf(
        "T-110 kV" to remember { mutableStateOf("1") },
        "T-35 kV" to remember { mutableStateOf("0") },
        "T-10 kV (кабельна мережа 10 кВ)" to remember { mutableStateOf("0") },
        "T-10 kV (повітряна мережа 10 кВ)" to remember { mutableStateOf("0") },
        "B-110 kV (елегазовий)" to remember { mutableStateOf("1") },
        "B-10 kV (малолойний)" to remember { mutableStateOf("1") },
        "B-10 kV (вакуумний)" to remember { mutableStateOf("0") },
        "Збірні шини 10 кВ на 1 приєднання" to remember { mutableStateOf("6") },
        "АВ-0,38 кВ" to remember { mutableStateOf("0") },
        "ЕД 6,10 кВ" to remember { mutableStateOf("0") },
        "ЕД 0,38 кВ" to remember { mutableStateOf("0") },
        "ПЛ-110 кВ" to remember { mutableStateOf("10") },
        "ПЛ-35 кВ" to remember { mutableStateOf("0") },
        "ПЛ-10 кВ" to remember { mutableStateOf("0") },
        "КЛ-10 кВ (траншея)" to remember { mutableStateOf("0") },
        "КЛ-10 кВ (кабельний канал)" to remember { mutableStateOf("0") }
    )

    var Woc by remember { mutableStateOf("") }
    var Tvoc by remember { mutableStateOf("") }
    var Kaoc by remember { mutableStateOf("") }
    var Kpoc by remember { mutableStateOf("") }
    var Wdk by remember { mutableStateOf("") }
    var Wds by remember { mutableStateOf("") }


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        OutlinedTextField(
            value = amountMap["T-110 kV"]?.value ?: "",
            onValueChange = { amountMap["T-110 kV"]?.value = it },
            label = { Text("T-110 kV") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["T-35 kV"]?.value ?: "",
            onValueChange = { amountMap["T-35 kV"]?.value = it },
            label = { Text("T-35 kV") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["T-10 kV (кабельна мережа 10 кВ)"]?.value ?: "",
            onValueChange = { amountMap["T-10 kV (кабельна мережа 10 кВ)"]?.value = it },
            label = { Text("T-10 kV (кабельна мережа 10 кВ)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["T-10 kV (повітряна мережа 10 кВ)"]?.value ?: "",
            onValueChange = { amountMap["T-10 kV (повітряна мережа 10 кВ)"]?.value = it },
            label = { Text("T-10 kV (повітряна мережа 10 кВ)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["B-110 kV (елегазовий)"]?.value ?: "",
            onValueChange = { amountMap["B-110 kV (елегазовий)"]?.value = it },
            label = { Text("B-110 kV (елегазовий)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["B-10 kV (малолойний)"]?.value ?: "",
            onValueChange = { amountMap["B-10 kV (малолойний)"]?.value = it },
            label = { Text("B-10 kV (малолойний)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["B-10 kV (вакуумний)"]?.value ?: "",
            onValueChange = { amountMap["B-10 kV (вакуумний)"]?.value = it },
            label = { Text("B-10 kV (вакуумний)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["Збірні шини 10 кВ на 1 приєднання"]?.value ?: "",
            onValueChange = { amountMap["Збірні шини 10 кВ на 1 приєднання"]?.value = it },
            label = { Text("Збірні шини 10 кВ на 1 приєднання") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["АВ-0,38 кВ"]?.value ?: "",
            onValueChange = { amountMap["АВ-0,38 кВ"]?.value = it },
            label = { Text("АВ-0,38 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["ЕД 6,10 кВ"]?.value ?: "",
            onValueChange = { amountMap["ЕД 6,10 кВ"]?.value = it },
            label = { Text("ЕД 6,10 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["ЕД 0,38 кВ"]?.value ?: "",
            onValueChange = { amountMap["ЕД 0,38 кВ"]?.value = it },
            label = { Text("ЕД 0,38 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["ПЛ-110 кВ"]?.value ?: "",
            onValueChange = { amountMap["ПЛ-110 кВ"]?.value = it },
            label = { Text("ПЛ-110 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["ПЛ-35 кВ"]?.value ?: "",
            onValueChange = { amountMap["ПЛ-35 кВ"]?.value = it },
            label = { Text("ПЛ-35 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["ПЛ-10 кВ"]?.value ?: "",
            onValueChange = { amountMap["ПЛ-10 кВ"]?.value = it },
            label = { Text("ПЛ-10 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["КЛ-10 кВ (траншея)"]?.value ?: "",
            onValueChange = { amountMap["КЛ-10 кВ (траншея)"]?.value = it },
            label = { Text("КЛ-10 кВ (траншея)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = amountMap["КЛ-10 кВ (кабельний канал)"]?.value ?: "",
            onValueChange = { amountMap["КЛ-10 кВ (кабельний канал)"]?.value = it },
            label = { Text("КЛ-10 кВ (кабельний канал)") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )


        // кнопка для виконання обчислень
        Button(
            onClick = {
                var w_0_c = 0.0
                var tvoc = 0.0
                var kaoc = 0.0
                var kpos = 0.0
                var wdk = 0.0
                var wds = 0.0

                amountMap.forEach { (key, value) ->
                    val amount = value.value.toIntOrNull() ?: 0

                    if (amount > 0) {
                        w_0_c += amount * data[key]?.failureRate!!

                        println(key)
                        println(amount * data[key]?.failureRate!!)
                        println(data[key]?.averageRepairTime!!)

                        tvoc += amount * data[key]?.averageRepairTime!! * data[key]?.failureRate!!
                    }

                }
                tvoc = tvoc / w_0_c
                kaoc = (tvoc * w_0_c) / 8760
                println(kaoc)
                kpos = 1.2 * 43 / 8760
                wdk = 2 * w_0_c * (kaoc + kpos)
                wds = wdk + 0.02

                Woc = roundToTwoDecimalString(w_0_c)
                Tvoc = roundToTwoDecimalString(tvoc)
                Kaoc = roundToTwoDecimalString(kaoc)
                Kpoc = roundToTwoDecimalString(kpos)
                Wdk = roundToTwoDecimalString(wdk)
                Wds = roundToTwoDecimalString(wds)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        // вивід результатів
        Text(text = "Частота відмов одноколової системи: " + Woc,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Середня тривалість відновлення: " + Tvoc,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Коефікієнт аварійного простою одноколової системи: " + Kaoc,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Коефікієнт планового простою одноколової системи: " + Kpoc,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Частота відмов одночасно двох кіл: " + Wdk,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Частота відмов двоколової системи з урахуванням секційного вимикача: " + Wds,
            style = MaterialTheme.typography.bodyLarge)

    }
}

fun roundToTwoDecimalString(value: Double): String {
//    return (Math.round(value * 1000) / 1000.0).toString()
    return value.toString()
}