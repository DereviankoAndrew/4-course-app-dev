package com.example.lab4

import android.util.Log
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
import kotlin.math.sqrt
import kotlin.math.exp
import kotlin.math.ln
import androidx.compose.ui.Modifier

@Composable
fun Calculator3App(modifier: Modifier = Modifier) {

    val focusManager = LocalFocusManager.current

    // визначаю змінні
    var S_b by remember { mutableStateOf("1000") }
    var U_b by remember { mutableStateOf("6.3") }
    var S_k by remember { mutableStateOf("200") }
    var U_k_perc by remember { mutableStateOf("7.5") }
    var S_nom_t by remember { mutableStateOf("4") }
    var X0 by remember { mutableStateOf("0.08") }
    var l by remember { mutableStateOf("0.3") }
    var X_d_perc by remember { mutableStateOf("23.8") }
    var S_nom_d by remember { mutableStateOf("1.94") }
    var Ec by remember { mutableStateOf("1") }
    var Ed by remember { mutableStateOf("1.07") }
    var t by remember { mutableStateOf("0.065") }
    var Yt by remember { mutableStateOf("0.71") }
    var Tac by remember { mutableStateOf("0.03") }
    var Tad by remember { mutableStateOf("0.037") }
    var Tpz by remember { mutableStateOf("0.5") }
    var Tpv by remember { mutableStateOf("0.1") }


    var Ib by remember { mutableStateOf("") }
    var Xc by remember { mutableStateOf("") }
    var Xt by remember { mutableStateOf("") }
    var Xl by remember { mutableStateOf("") }
    var Xd by remember { mutableStateOf("") }
    var U_c_n by remember { mutableStateOf("") }
    var X_E_c by remember { mutableStateOf("") }
    var X_E_d by remember { mutableStateOf("") }
    var Ip0c by remember { mutableStateOf("") }
    var Ip0d by remember { mutableStateOf("") }
    var Iptc by remember { mutableStateOf("") }
    var Iptd by remember { mutableStateOf("") }
    var Iac by remember { mutableStateOf("") }
    var Iad by remember { mutableStateOf("") }
    var Iuds by remember { mutableStateOf("") }
    var Iudd by remember { mutableStateOf("") }
    var Tach by remember { mutableStateOf("") }
    var Tpd by remember { mutableStateOf("") }
    var Tvidk by remember { mutableStateOf("") }
    var Vk by remember { mutableStateOf("") }


    // розмітка застосунку
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = S_b,
            onValueChange = { S_b = it },
            label = { Text("S_b") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = U_b,
            onValueChange = { U_b = it },
            label = { Text("U_b") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = S_k,
            onValueChange = { S_k = it },
            label = { Text("S_k") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = U_k_perc,
            onValueChange = { U_k_perc = it },
            label = { Text("U_k_perc") },
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
        OutlinedTextField(
            value = X0,
            onValueChange = { X0 = it },
            label = { Text("X0") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = l,
            onValueChange = { l = it },
            label = { Text("l") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = X_d_perc,
            onValueChange = { X_d_perc = it },
            label = { Text("X_d_perc") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = S_nom_d,
            onValueChange = { S_nom_d = it },
            label = { Text("S_nom_d") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Ec,
            onValueChange = { Ec = it },
            label = { Text("Ec") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Ed,
            onValueChange = { Ed = it },
            label = { Text("Ed") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = t,
            onValueChange = { t = it },
            label = { Text("t") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Yt,
            onValueChange = { Yt = it },
            label = { Text("Yt") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tac,
            onValueChange = { Tac = it },
            label = { Text("Tac") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tad,
            onValueChange = { Tad = it },
            label = { Text("Tad") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tpz,
            onValueChange = { Tpz = it },
            label = { Text("Tpz") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = Tpv,
            onValueChange = { Tpv = it },
            label = { Text("Tpv") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // кнопка для виконання обчислень
        Button(
            onClick = {
                val _S_b = S_b.toDoubleOrNull() ?: 0.0
                val _U_b = U_b.toDoubleOrNull() ?: 0.0
                val _S_k = S_k.toDoubleOrNull() ?: 0.0
                val _U_k_perc = U_k_perc.toDoubleOrNull() ?: 0.0
                val _S_nom_t = S_nom_t.toDoubleOrNull() ?: 0.0
                val _X0 = X0.toDoubleOrNull() ?: 0.0
                val _l = l.toDoubleOrNull() ?: 0.0
                val _X_d_perc = X_d_perc.toDoubleOrNull() ?: 0.0
                val _S_nom_d = S_nom_d.toDoubleOrNull() ?: 0.0
                val _Ec = Ec.toDoubleOrNull() ?: 0.0
                val _Ed = Ed.toDoubleOrNull() ?: 0.0
                val _t = t.toDoubleOrNull() ?: 0.0
                val _Yt = Yt.toDoubleOrNull() ?: 0.0
                val _Tac = Tac.toDoubleOrNull() ?: 0.0
                val _Tad = Tad.toDoubleOrNull() ?: 0.0
                val _Tpz = Tpz.toDoubleOrNull() ?: 0.0
                val _Tpv = Tpv.toDoubleOrNull() ?: 0.0

                var _Ib = Ib.toDoubleOrNull() ?: 0.0
                var _Xc = Xc.toDoubleOrNull() ?: 0.0
                var _Xt = Xt.toDoubleOrNull() ?: 0.0
                var _Xl = Xl.toDoubleOrNull() ?: 0.0
                var _Xd = Xd.toDoubleOrNull() ?: 0.0
                var _U_c_n = U_c_n.toDoubleOrNull() ?: 0.0
                var _X_E_c = X_E_c.toDoubleOrNull() ?: 0.0
                var _X_E_d = X_E_d.toDoubleOrNull() ?: 0.0
                var _Ip0c = Ip0c.toDoubleOrNull() ?: 0.0
                var _Ip0d = Ip0d.toDoubleOrNull() ?: 0.0
                var _Iptc = Iptc.toDoubleOrNull() ?: 0.0
                var _Iptd = Iptd.toDoubleOrNull() ?: 0.0
                var _Iac = Iac.toDoubleOrNull() ?: 0.0
                var _Iad = Iad.toDoubleOrNull() ?: 0.0
                var _Iuds = Iuds.toDoubleOrNull() ?: 0.0
                var _Iudd = Iudd.toDoubleOrNull() ?: 0.0
                var _Tach = Tach.toDoubleOrNull() ?: 0.0
                var _Tpd = Tpd.toDoubleOrNull() ?: 0.0
                var _Tvidk = Tvidk.toDoubleOrNull() ?: 0.0
                var _Vk = Vk.toDoubleOrNull() ?: 0.0


                _Ib = _S_b / (sqrt(3.0) * _U_b)
                _Xc = _S_b / _S_k
                _Xt = _U_k_perc / 100 * _S_b / _S_nom_t
                _U_c_n = _U_b * _U_b
                _Xl = _X0 * _l * _S_b / _U_c_n
                _Xd =  _X_d_perc / 100 * _S_b / _S_nom_d
                _X_E_c = _Xc + _Xt
                _X_E_d = _Xd + _Xl
                _Ip0c = _Ec / _X_E_c * _Ib
                _Ip0d = _Ed / _X_E_d * _Ib
                _Iptc = _Ip0c
                _Iptd = _Ip0d * _Yt

                _Iac = sqrt(2.0) * _Ip0c * exp(-_t / _Tac)
                _Iad = sqrt(2.0) * _Ip0d * exp(-_t / _Tad)

                _Iuds = sqrt(2.0) * _Ip0c * (1 + exp(-0.01 / _Tac))
                _Iudd = sqrt(2.0) * _Ip0d * (1 + exp(-0.01 / _Tad))

                _Tach = (_Tac * _Ip0c + _Tad * _Ip0d) / (_Ip0c + _Ip0d)

                _Tpd =  -_t / (ln(_t))

                _Tvidk = _Tpz + _Tpv

                _Vk = (_Ip0c * _Ip0c * (_Tvidk + _Tach)) + (_Ip0d * _Ip0d * (0.5 * _Tpd + _Tach)) + (2 * _Ip0c * _Ip0d * (_Tpd + _Tach))

                Log.d("CalculationResults", "_Ib = $_Ib")
                Log.d("CalculationResults", "_Xc = $_Xc")
                Log.d("CalculationResults", "_Xt = $_Xt")
                Log.d("CalculationResults", "_U_c_n = $_U_c_n")
                Log.d("CalculationResults", "_Xl = $_Xl")
                Log.d("CalculationResults", "_Xd = $_Xd")
                Log.d("CalculationResults", "_X_E_c = $_X_E_c")
                Log.d("CalculationResults", "_X_E_d = $_X_E_d")
                Log.d("CalculationResults", "_Ip0c = $_Ip0c")
                Log.d("CalculationResults", "_Ip0d = $_Ip0d")
                Log.d("CalculationResults", "_Iptc = $_Iptc")
                Log.d("CalculationResults", "_Iptd = $_Iptd")
                Log.d("CalculationResults", "_Iac = $_Iac")
                Log.d("CalculationResults", "_Iad = $_Iad")
                Log.d("CalculationResults", "_Iuds = $_Iuds")
                Log.d("CalculationResults", "_Iudd = $_Iudd")
                Log.d("CalculationResults", "_Tach = $_Tach")
                Log.d("CalculationResults", "_Tpd = $_Tpd")
                Log.d("CalculationResults", "_Tvidk = $_Tvidk")
                Log.d("CalculationResults", "_Vk = $_Vk")

                Ib = roundToTwoDecimalString(_Ib)
                Xc = roundToTwoDecimalString(_Xc)
                Xt = roundToTwoDecimalString(_Xt)
                U_c_n = roundToTwoDecimalString(_U_c_n)
                Xl = roundToTwoDecimalString(_Xl)
                Xd = roundToTwoDecimalString(_Xd)
                X_E_c = roundToTwoDecimalString(_X_E_c)
                X_E_d = roundToTwoDecimalString(_X_E_d)
                Ip0c = roundToTwoDecimalString(_Ip0c)
                Ip0d = roundToTwoDecimalString(_Ip0d)
                Iptc = roundToTwoDecimalString(_Iptc)
                Iptd = roundToTwoDecimalString(_Iptd)
                Iac = roundToTwoDecimalString(_Iac)
                Iad = roundToTwoDecimalString(_Iad)
                Iuds = roundToTwoDecimalString(_Iuds)
                Iudd = roundToTwoDecimalString(_Iudd)
                Tach = roundToTwoDecimalString(_Tach)
                Tpd = roundToTwoDecimalString(_Tpd)
                Tvidk = roundToTwoDecimalString(_Tvidk)
                Vk = roundToTwoDecimalString(_Vk)

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        // вивід результатів
        Text(text = "Ib: " + Ib, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Xc: " + Xc, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Xt: " + Xt, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "U_c_n: " + U_c_n, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Xl: " + Xl, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Xd: " + Xd, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "X_E_c: " + X_E_c, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "X_E_d: " + X_E_d, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ip0c: " + Ip0c, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Ip0d: " + Ip0d, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iptc: " + Iptc, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iptd: " + Iptd, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iac: " + Iac, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iad: " + Iad, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iuds: " + Iuds, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Iudd: " + Iudd, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Tach: " + Tach, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Tpd: " + Tpd, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Tvidk: " + Tvidk, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Vk: " + Vk, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))


    }
}
fun roundToTwoDecimalString(value: Double): String {
    return (Math.round(value * 100) / 100.0).toString()
}