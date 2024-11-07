package com.example.lab4

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

@Composable
fun CableCalculatorApp(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current

    // визначаю змінні

    var Unom by remember { mutableStateOf("10") }
    var Ik by remember { mutableStateOf("2.5") }
    var Tf by remember { mutableStateOf("2.5") }
    var Potuzhnist_TP by remember { mutableStateOf("2000") }
    var Sm by remember { mutableStateOf("1300") }
    var Tm by remember { mutableStateOf("4000") }
    var Ct by remember { mutableStateOf("92") }

    var Im by remember { mutableStateOf("") }
    var Impa by remember { mutableStateOf("") }
    var Sek by remember { mutableStateOf("") }
    var s_smin by remember { mutableStateOf("") }


    var conductorTypeExpanded by remember { mutableStateOf(false) }
    var conductorType by remember { mutableStateOf("Тип провідника") }
    val conductorTypeOptions = listOf(
        "Неізольовані проводи та шини",
        "Кабелі з паперовою і проводи з гумовою та полівінілхлоридною ізоляцією з жилами",
        "Кабелі з гумовою та пластмасовою ізоляцією з жилами"
    )

    var conductorMaterialExpanded by remember { mutableStateOf(false) }
    var conductorMaterial by remember { mutableStateOf("Матеріал провідника") }
    val conductorMaterialOptions = listOf(
        "мідні",
        "алюмінієві"
    )


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = Unom,
            onValueChange = { Unom = it },
            label = { Text("Unom") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Ik,
            onValueChange = { Ik = it },
            label = { Text("Ik") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tf,
            onValueChange = { Tf = it },
            label = { Text("Tf") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Potuzhnist_TP,
            onValueChange = { Potuzhnist_TP = it },
            label = { Text("Potuzhnist_TP") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Sm,
            onValueChange = { Sm = it },
            label = { Text("Sm") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tm,
            onValueChange = { Tm = it },
            label = { Text("Tm") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Dropdown menu
        Button(onClick = { conductorTypeExpanded = true }) {
            Text(conductorType)
        }
        DropdownMenu(
            expanded = conductorTypeExpanded,
            onDismissRequest = { conductorTypeExpanded = false }
        ) {
            conductorTypeOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        conductorType = option
                        conductorTypeExpanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { conductorMaterialExpanded = true }) {
            Text(conductorMaterial)
        }
        DropdownMenu(
            expanded = conductorMaterialExpanded,
            onDismissRequest = { conductorMaterialExpanded = false }
        ) {
            conductorMaterialOptions.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        conductorMaterial = option
                        conductorMaterialExpanded = false
                    },
                    text = { Text(option) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // кнопка для виконання обчислень
        Button(
            onClick = {
                val Sm = Sm.toDoubleOrNull() ?: 0.0
                val Unom = Unom.toDoubleOrNull() ?: 0.0
                val Tm = Tm.toDoubleOrNull() ?: 0.0
                val Ik = Ik.toDoubleOrNull() ?: 0.0

                var _Im = (Sm / 2.0) / (sqrt(3.0) * Unom)
                var _Impa = 2 * _Im

                val Jek = determineJek(conductorType, conductorMaterial, Tm)

                var _Sek = _Im / Jek

                _Im = Math.round(_Im * 100) / 100.0
                _Impa = Math.round(_Impa * 100) / 100.0
                _Sek = Math.round(_Sek * 100) / 100.0

                Im = _Im.toString()
                Impa = _Impa.toString()
                Sek = _Sek.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        // вивід результатів
        Text(text = "Розрахунковий струм для нормального режиму: " + Im,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Розрахунковий струм для післяаварійного режиму: " + Impa,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Економічний переріз (S ек): " + Sek,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Ct,
            onValueChange = { Ct = it },
            label = { Text("Ct") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                val Tf = Tf.toDoubleOrNull() ?: 0.0
                val Ik = Ik.toDoubleOrNull() ?: 0.0
                val Ct = Ct.toDoubleOrNull() ?: 0.0

                var _s_smin = (Ik * 1000 * sqrt(Tf)) / Ct

                _s_smin = Math.round(_s_smin * 100) / 100.0


                s_smin = _s_smin.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити переріз за термічною стійкістю до дії струмів КЗ")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Переріз за термічною стійкістю до дії струмів КЗ: " + s_smin,
            style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(48.dp))



//        profit.toDoubleOrNull()?.let {
//            Text(text = "Висновок: " + conclusion, style = MaterialTheme.typography.bodyLarge)
//        }

    }
}

fun determineJek(conductorType: String, conductorMaterial: String, Tm: Double): Double {
    // Define ranges and corresponding Jek values
    val jekValues = mapOf(
        "Неізольовані проводи та шини" to mapOf(
            "мідні" to listOf(1000.0 to 3000.0 to 2.5, 3000.0 to 5000.0 to 2.1, 5000.0 to Double.MAX_VALUE to 1.8),
            "алюмінієві" to listOf(1000.0 to 3000.0 to 1.3, 3000.0 to 5000.0 to 1.1, 5000.0 to Double.MAX_VALUE to 1.0)
        ),
        "Кабелі з паперовою і проводи з гумовою та полівінілхлоридною ізоляцією з жилами" to mapOf(
            "мідні" to listOf(1000.0 to 3000.0 to 3.0, 3000.0 to 5000.0 to 2.5, 5000.0 to Double.MAX_VALUE to 2.0),
            "алюмінієві" to listOf(1000.0 to 3000.0 to 1.6, 3000.0 to 5000.0 to 1.4, 5000.0 to Double.MAX_VALUE to 1.2)
        ),
        "Кабелі з гумовою та пластмасовою ізоляцією з жилами" to mapOf(
            "мідні" to listOf(1000.0 to 3000.0 to 3.5, 3000.0 to 5000.0 to 3.1, 5000.0 to Double.MAX_VALUE to 2.7),
            "алюмінієві" to listOf(1000.0 to 3000.0 to 1.9, 3000.0 to 5000.0 to 1.7, 5000.0 to Double.MAX_VALUE to 1.6)
        )
    )

    // Look up the Jek value based on input parameters
    return jekValues[conductorType]?.get(conductorMaterial)?.firstOrNull { (range, jek) ->
        Tm >= range.first && Tm < range.second
    }?.second ?: 0.0
}