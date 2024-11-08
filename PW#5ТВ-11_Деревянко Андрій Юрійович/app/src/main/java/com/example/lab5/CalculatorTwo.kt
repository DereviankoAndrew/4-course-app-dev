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
import androidx.compose.ui.Modifier


@Composable
fun CalculatorTwo(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current


    var omega by remember { mutableStateOf("0.01") }
    var tS by remember { mutableStateOf("0.045") }
    var pM by remember { mutableStateOf("5120") }
    var tM by remember { mutableStateOf("6451") }
    var zAvar by remember { mutableStateOf("23.6") }
    var zPlan by remember { mutableStateOf("17.6") }
    var kP by remember { mutableStateOf("0.004") }

    var mWnedA by remember { mutableStateOf("") }
    var mWnedP by remember { mutableStateOf("") }
    var mZ by remember { mutableStateOf("") }


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(56.dp))
        OutlinedTextField(
            value = omega,
            onValueChange = { omega = it },
            label = { Text("частота відмов") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tS,
            onValueChange = { tS = it },
            label = { Text("середній час відновлення трансформатора напругою 35 кВ") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pM,
            onValueChange = { pM = it },
            label = { Text("потужність") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tM,
            onValueChange = { tM = it },
            label = { Text("очікуваний час простою") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = zAvar,
            onValueChange = { zAvar = it },
            label = { Text("збитки у разі аварійного переривання") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = zPlan,
            onValueChange = { zPlan = it },
            label = { Text("збитки у разі запланованого переривання") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = kP,
            onValueChange = { kP = it },
            label = { Text("сер час планового простою") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // кнопка для виконання обчислень
        Button(
            onClick = {

                val omegaValue = omega.toDoubleOrNull() ?: 0.0
                val tSValue = tS.toDoubleOrNull() ?: 0.0
                val pMValue = pM.toDoubleOrNull() ?: 0.0
                val tMValue = tM.toDoubleOrNull() ?: 0.0
                val zAvarValue = zAvar.toDoubleOrNull() ?: 0.0
                val zPlanValue = zPlan.toDoubleOrNull() ?: 0.0
                val kPValue = kP.toDoubleOrNull() ?: 0.0

                val mWnedAValue = omegaValue * tSValue * pMValue * tMValue
                val mWnedPValue = kPValue * pMValue * tMValue
                val mZValue = zAvarValue * mWnedAValue + zPlanValue * mWnedPValue

                mWnedA = mWnedAValue.toString()
                mWnedP = mWnedPValue.toString()
                mZ = mZValue.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Очікувана відсутність енергопостачання в надзвичайних ситуаціях: " + mWnedA,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Очікуваний дефіцит енергії для запланованих: " + mWnedP,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Загальна очікувана вартість перерв у роботі: " + mZ,
            style = MaterialTheme.typography.bodyLarge)

    }
}