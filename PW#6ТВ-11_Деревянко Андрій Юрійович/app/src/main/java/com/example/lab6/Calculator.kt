package com.example.lab6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.tooling.preview.Preview
import kotlin.math.ceil

@Stable
data class EpData(
    var name: String = "",
    var nu_n: String = "",
    var cos_phi: String = "",
    var Un: String = "",
    var n: String = "",
    var Pn: String = "",
    var Kv: String = "",
    var tg_phi: String = "",

    var n_mult_Pn: String = "",
    var Ip: String = "",
)

@Composable
fun EpDataForm(epData: EpData) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = epData.name,
        onValueChange = { epData.name = it },
        label = { Text("Найменування ЕП") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.nu_n,
        onValueChange = { epData.nu_n = it },
        label = { Text("Номінальне значення ККД (ηн)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.cos_phi,
        onValueChange = { epData.cos_phi = it },
        label = { Text("Коефіцієнт потужн навантаж (cos φ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.Un,
        onValueChange = { epData.Un = it },
        label = { Text("Напруга навантаження (Uн, кВ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.n,
        onValueChange = { epData.n = it },
        label = { Text("Кількість ЕП (n, шт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.Pn,
        onValueChange = { epData.Pn = it },
        label = { Text("Номінальна потужність ЕП (Рн, кВт)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.Kv,
        onValueChange = { epData.Kv = it },
        label = { Text("Коефіцієнт використання (КВ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
    OutlinedTextField(
        value = epData.tg_phi,
        onValueChange = { epData.tg_phi = it },
        label = { Text("Коефіцієнт реактивної потужн (tgφ)") },
        modifier = Modifier
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
    )
}
@Preview
@Composable
fun Calculator() {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    var epDataList by remember { mutableStateOf(listOf(
        EpData(
            name = "Шліфувальний верстат",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "4",
            Pn = "20",
            Kv = "0.15",
            tg_phi = "1.33"
        ),
        EpData(
            name = "Свердлильний верстат",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "2",
            Pn = "14",
            Kv = "0.12",
            tg_phi = "1"
        ),
        EpData(
            name = "Фугувальний верстат",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "4",
            Pn = "42",
            Kv = "0.15",
            tg_phi = "1.33"
        ),
        EpData(
            name = "Циркулярна пила",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "1",
            Pn = "36",
            Kv = "0.3",
            tg_phi = "1.52"
        ),
        EpData(
            name = "Прес",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "1",
            Pn = "20",
            Kv = "0.5",
            tg_phi = "0.75"
        ),
        EpData(
            name = "Полірувальний верстат",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "1",
            Pn = "40",
            Kv = "0.2",
            tg_phi = "1"
        ),
        EpData(
            name = "Фрезерний верстат",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "2",
            Pn = "32",
            Kv = "0.2",
            tg_phi = "1"
        ),
        EpData(
            name = "Вентилятор",
            nu_n = "0.92",
            cos_phi = "0.9",
            Un = "0.38",
            n = "1",
            Pn = "20",
            Kv = "0.65",
            tg_phi = "0.75"
        ),
    )) }

    var Kr by remember { mutableStateOf("1.25") }
    var Kr2 by remember { mutableStateOf("0.7") }

    var sum_of_n_Pn_Kv_product_41 by remember { mutableStateOf(0.0) }

    var kv_group by remember { mutableStateOf("") }
    var eff_ep_amount by remember { mutableStateOf("") }

    var total_department_util_coef by remember { mutableStateOf("") }
    var eff_ep_department_amount by remember { mutableStateOf("") }
    var rozrah_act_nav by remember { mutableStateOf("") }
    var rozrah_react_nav by remember { mutableStateOf("") }
    var full_power by remember { mutableStateOf("") }
    var rozrah_group_strum_shr1 by remember { mutableStateOf("") }

    var rozrah_act_nav_shin by remember { mutableStateOf("") }
    var rozrah_react_nav_shin by remember { mutableStateOf("") }
    var full_power_shin by remember { mutableStateOf("") }
    var rozrah_group_strum_shin by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(scrollState)
    ) {
        Button(
            onClick = { epDataList = epDataList + EpData() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Додати ЕП")
        }

        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(epDataList) { epData ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    EpDataForm(epData = epData)
                }
            }
        }

        Button(
            onClick = {
                var sum_of_n_Pn_Kv_product = 0.0
                var sum_of_n_Pn_product = 0.0
                var sum_of_n_Pn_Pn_product = 0.0
                var group_util_coefficient = 0.0
                var effective_ep_amount = 0.0

                epDataList.forEach { epData ->
                    val n = epData.n.toDouble()
                    val Pn = epData.Pn.toDouble()
                    epData.n_mult_Pn = "${n * Pn}"
                    var Ip = epData.n_mult_Pn.toDouble() / (sqrt(3.0) * epData.Un.toDouble() * epData.cos_phi.toDouble() * epData.nu_n.toDouble())
                    epData.Ip = Ip.toString()

                    sum_of_n_Pn_Kv_product = sum_of_n_Pn_Kv_product + epData.n_mult_Pn.toDouble() * epData.Kv.toDouble()
                    sum_of_n_Pn_product = sum_of_n_Pn_product + epData.n_mult_Pn.toDouble()
                    sum_of_n_Pn_Pn_product = sum_of_n_Pn_Pn_product + epData.n.toDouble() * epData.Pn.toDouble() * epData.Pn.toDouble()
                }

                sum_of_n_Pn_Kv_product_41 = sum_of_n_Pn_Kv_product

                group_util_coefficient = sum_of_n_Pn_Kv_product / sum_of_n_Pn_product

                println(group_util_coefficient)

                effective_ep_amount = ceil(
                    (sum_of_n_Pn_product * sum_of_n_Pn_product) / sum_of_n_Pn_Pn_product
                )

                println(effective_ep_amount)

                kv_group = group_util_coefficient.toString()
                eff_ep_amount = effective_ep_amount.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "груповий коефіцієнт використання: " + kv_group,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ефективна кількість ЕП: " + eff_ep_amount,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = Kr,
            onValueChange = { Kr = it },
            label = { Text("розрахунковий коеф активної потужності") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Button(
            onClick = {

                val KvValue = kv_group.toDouble()
                val KrValue = Kr.toDouble()
                val PH = 23.0 // за варіантом 14
                val tan_phi = 1.58 // за варіантом 14
                val Un = 0.38

                val Pp = KrValue * sum_of_n_Pn_Kv_product_41
                val Qp = KvValue * PH * tan_phi
                val Sp = sqrt((Pp * Pp) + (Qp * Qp))
                val Ip = Pp / Un


                val KvDepartment = 752.0 / 2330.0
                val n_e = 2330.0 * 2330.0 / 96399.0

                println(KvDepartment)

                rozrah_act_nav = Pp.toString()
                rozrah_react_nav = Qp.toString()
                full_power = Sp.toString()
                rozrah_group_strum_shr1 = Ip.toString()
                total_department_util_coef = KvDepartment.toString()
                eff_ep_department_amount = n_e.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахункове активне навантаження: " + rozrah_act_nav,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахункове реактивне навантаження: " + rozrah_react_nav,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "повна потужність: " + full_power,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахунковий груповий струм ШР1: " + rozrah_group_strum_shr1,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "коефіцієнт використання цеху в цілому: " + total_department_util_coef,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ефективна кількість ЕП цеху в цілому: " + eff_ep_department_amount,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = Kr2,
            onValueChange = { Kr2 = it },
            label = { Text("розрахунковий коеф активної потужності") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { focusManager.clearFocus() }
        )

        Button(
            onClick = {

                val KvValue = Kr2.toDouble()

                var Pp = KvValue * 752.0
                var Qp = KvValue * 657.0
                val Sp = sqrt((Pp * Pp) + (Qp * Qp))
                val Ip = Pp / 0.38

                rozrah_act_nav_shin = Pp.toString()
                rozrah_react_nav_shin = Qp.toString()
                full_power_shin = Sp.toString()
                rozrah_group_strum_shin = Ip.toString()

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Обчислити")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахункове активне навантаження на шинах 0,38 кВ ТП: " + rozrah_act_nav_shin,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахункове реактивне навантаження на шинах 0,38 кВ ТП: " + rozrah_react_nav_shin,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "повна потужність на шинах 0,38 кВ ТП: " + full_power_shin,
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "розрахунковий груповий струм на шинах 0,38 кВ ТП: " + rozrah_group_strum_shin,
            style = MaterialTheme.typography.bodyLarge)

    }
}