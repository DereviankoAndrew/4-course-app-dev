package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

// Ф-ція для розрахунку показника емісії
fun calculateEmission(
    Qi: Double,
    a_viv: Double,
    Ar: Double,
    G_viv: Double,
    eta_luz: Double,
    k_ms: Double
): Double {
    val factor1 = 1_000_000 / Qi
    val factor2 = a_viv * (Ar / (100 - G_viv)) * (1 - eta_luz)

    return factor1 * factor2 + k_ms
}

// Ф-ція для розрахунку валових викидів
fun calculateGrossEmission(
    ktv: Double,
    Qi: Double,
    B: Double
): Double {
    return 1e-6 * ktv * Qi * B
}

@Composable
fun Calculator(modifier: Modifier = Modifier) {

    var QiInput by remember { mutableStateOf("") }
    var aVivInput by remember { mutableStateOf("") }
    var ArInput by remember { mutableStateOf("") }
    var GVivInput by remember { mutableStateOf("") }
    var etaLuzInput by remember { mutableStateOf("") }
    var kMsInput by remember { mutableStateOf("") }
    var BInput by remember { mutableStateOf("") }

    var emissionResult by remember { mutableStateOf<Double?>(null) }
    var grossEmissionResult by remember { mutableStateOf<Double?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    fun areInputsValid(): Boolean {
        return QiInput.isNotEmpty() && aVivInput.isNotEmpty() && ArInput.isNotEmpty() &&
                GVivInput.isNotEmpty() && etaLuzInput.isNotEmpty() && kMsInput.isNotEmpty()
                && BInput.isNotEmpty()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Калькулятор викидів")

        // інпути
        OutlinedTextField(
            value = QiInput,
            onValueChange = { QiInput = it },
            label = { Text("Q'_i (Qi)") }
        )
        OutlinedTextField(
            value = aVivInput,
            onValueChange = { aVivInput = it },
            label = { Text("a_вив (a_viv)") }
        )
        OutlinedTextField(
            value = ArInput,
            onValueChange = { ArInput = it },
            label = { Text("A^r (Ar)") }
        )
        OutlinedTextField(
            value = GVivInput,
            onValueChange = { GVivInput = it },
            label = { Text("Г_вив (G_viv)") }
        )
        OutlinedTextField(
            value = etaLuzInput,
            onValueChange = { etaLuzInput = it },
            label = { Text("η_луз (eta_luz)") }
        )
        OutlinedTextField(
            value = kMsInput,
            onValueChange = { kMsInput = it },
            label = { Text("k_мс (k_ms)") }
        )
        OutlinedTextField(
            value = BInput,
            onValueChange = { BInput = it },
            label = { Text("B (Маса палива)") }
        )

        // Кнопка для обчислень
        Button(
            onClick = {
                if (areInputsValid()) {
                    emissionResult = calculateEmission(
                        Qi = QiInput.toDouble(),
                        a_viv = aVivInput.toDouble(),
                        Ar = ArInput.toDouble(),
                        G_viv = GVivInput.toDouble(),
                        eta_luz = etaLuzInput.toDouble(),
                        k_ms = kMsInput.toDouble()
                    )

                    grossEmissionResult = calculateGrossEmission(
                        ktv = emissionResult!!.toDouble(),
                        Qi = QiInput.toDouble(),
                        B = BInput.toDouble()
                    )
                } else {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Будь ласка, заповніть всі поля")
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Обчислити")
        }

        // Відобразити результат
        emissionResult?.let {
            Text(text = "Показник емісії: $it")
        }
        // Відобразити результат для розрахунку валових викидів
        grossEmissionResult?.let {
            Text(text = "Валові викиди: $it")
        }
    }

        // Snackbar для повідомлень про помилки
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

