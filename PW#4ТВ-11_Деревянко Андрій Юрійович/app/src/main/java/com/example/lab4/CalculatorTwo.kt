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
fun CurrentCalculatorApp(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current

    // визначаю змінні
    var Ucn by remember { mutableStateOf("10.5") }
    var Sk by remember { mutableStateOf("200") }
    var Uk_perc by remember { mutableStateOf("10.5") }
    var S_nom_t by remember { mutableStateOf("6.3") }

    var Xt by remember { mutableStateOf("") }
    var Xc by remember { mutableStateOf("") }
    var Xe by remember { mutableStateOf("") }
    var Ip0 by remember { mutableStateOf("") }


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = Ucn,
            onValueChange = { Ucn = it },
            label = { Text("Ucn") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Sk,
            onValueChange = { Sk = it },
            label = { Text("Sk") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Uk_perc,
            onValueChange = { Uk_perc = it },
            label = { Text("Uk_perc") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = S_nom_t,
            onValueChange = { S_nom_t = it },
            label = { Text("S_nom_t") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // кнопка для виконання обчислень
        Button(
            onClick = {
                val _Ucn = Ucn.toDoubleOrNull() ?: 0.0
                val _Sk = Sk.toDoubleOrNull() ?: 0.0
                val _Uk_perc = Uk_perc.toDoubleOrNull() ?: 0.0
                val _S_nom_t = S_nom_t.toDoubleOrNull() ?: 0.0

                var _Xc = Math.round((_Ucn * _Ucn / _Sk) * 100) / 100.0
                var _Xt = Math.round(((_Uk_perc / 100) * (_Ucn * _Ucn / _S_nom_t)) * 100) / 100.0
                var _Xe = _Xc + _Xt
                var _Ip0 = Math.round((_Ucn / (sqrt(3.0) * _Xe)) * 100) / 100.0

                Xt = _Xc.toString()
                Xc = _Xt.toString()
                Xe = _Xe.toString()
                Ip0 = _Ip0.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        // вивід результатів
        Text(text = "Xc: " + Xc,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Xt: " + Xt,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Сумарний опір точки К1: " + Xe,
            style = MaterialTheme.typography.bodyLarge)
        Text(text = "Початкове діюче значення струму трифазного КЗ: " + Ip0,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

    }
}