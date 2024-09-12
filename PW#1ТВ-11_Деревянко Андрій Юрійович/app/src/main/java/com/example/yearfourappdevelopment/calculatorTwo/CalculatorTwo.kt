package com.example.yearfourappdevelopment.calculatorTwo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.yearfourappdevelopment.sharedComposables.PercentageInput
import com.example.yearfourappdevelopment.utils.roundTo2Decimals
import com.example.yearfourappdevelopment.utils.toDoubleOrZero
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun CalculatorTwo(modifier: Modifier = Modifier) {

    // визначаю змінні
    var carbon by remember { mutableStateOf("") }
    var hydrogen by remember { mutableStateOf("") }
    var oxygen by remember { mutableStateOf("") }
    var sulfur by remember { mutableStateOf("") }
    var lowerCalorificValueOfFuelOilCombustibleMass by remember { mutableStateOf("") }
    var moisture by remember { mutableStateOf("") }
    var ash by remember { mutableStateOf("") }
    var vanadium by remember { mutableStateOf("") }

    var carbonMazut by remember { mutableDoubleStateOf(0.0) }
    var hydrogenMazut by remember { mutableDoubleStateOf(0.0) }
    var oxygenMazut by remember { mutableDoubleStateOf(0.0) }
    var sulfurMazut by remember { mutableDoubleStateOf(0.0) }
    var ashMazut by remember { mutableDoubleStateOf(0.0) }
    var vanadiumMazut by remember { mutableDoubleStateOf(0.0) }

    var combustionHeat by remember { mutableDoubleStateOf(0.0) }

    var showResults by remember { mutableStateOf(false) }

    // Снекбар і корутина для нього, щоб виводити повідомлення про помилки
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // приховує composable "results" з результатами обчислень
    fun resetResults() {
        showResults = false
    }

    // перевіряє, чи всі значення введені
    fun areAllInputsFilled(): Boolean {
        return carbon.isNotEmpty() &&
                hydrogen.isNotEmpty() &&
                oxygen.isNotEmpty() &&
                sulfur.isNotEmpty() &&
                lowerCalorificValueOfFuelOilCombustibleMass.isNotEmpty() &&
                moisture.isNotEmpty() &&
                ash.isNotEmpty() &&
                vanadium.isNotEmpty()
    }

    // обчислює всі потрібні значення
    fun Calculate() {
        carbonMazut = toDoubleOrZero(carbon) * (100 - toDoubleOrZero(moisture) - toDoubleOrZero(ash)) / 100
        hydrogenMazut = toDoubleOrZero(hydrogen) * (100 - toDoubleOrZero(moisture) - toDoubleOrZero(ash)) / 100
        oxygenMazut = toDoubleOrZero(oxygen) * (100 - toDoubleOrZero(moisture) - toDoubleOrZero(ash)) / 100
        sulfurMazut = toDoubleOrZero(sulfur) * (100 - toDoubleOrZero(moisture) - toDoubleOrZero(ash)) / 100
        ashMazut = toDoubleOrZero(ash) * (100 - toDoubleOrZero(moisture)) / 100
        vanadiumMazut = toDoubleOrZero(vanadium) * (100 - toDoubleOrZero(moisture)) / 100

        combustionHeat = toDoubleOrZero(lowerCalorificValueOfFuelOilCombustibleMass) *
                ((100 - toDoubleOrZero(moisture) - toDoubleOrZero(ash)) / 100) -
                0.025 * toDoubleOrZero(moisture)

        println("carbonMazut: " + carbonMazut)
        println("hydrogenMazut: " + hydrogenMazut)
        println("oxygenMazut: " + oxygenMazut)
        println("sulfurMazut: " + sulfurMazut)
        println("ashMazut: " + ashMazut)
        println("vanadiumMazut: " + vanadiumMazut)
        println("combustionHeat: " + combustionHeat)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Перерахунок елементарного складу та нижчої\n" +
                "теплоти згоряння мазуту на робочу масу для складу горючої маси мазуту")


        PercentageInput(
            label = "Вуглець, %",
            value = carbon,
            onValueChange = {
                carbon = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Водень, %",
            value = hydrogen,
            onValueChange = {
                hydrogen = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Кисень, %",
            value = oxygen,
            onValueChange = {
                oxygen = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Сірка, %",
            value = sulfur,
            onValueChange = {
                sulfur = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Нижча теплота згоряння\n" +
                    "горючої маси мазуту, МДж/кг",
            value = lowerCalorificValueOfFuelOilCombustibleMass,
            onValueChange = {
                lowerCalorificValueOfFuelOilCombustibleMass = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Вологість робочої маси палива, %",
            value = moisture,
            onValueChange = {
                moisture = it
                resetResults()
            }
        )

        PercentageInput(
            label = "Зольність сухої маси, %",
            value = ash,
            onValueChange = {
                ash = it
                resetResults()
            }
        )

        // інпут для Double значень, що більше 0.
        TextField(
            value = vanadium,
            onValueChange = {
                if (it.isNotEmpty() && it.toDoubleOrNull() != null && it.toDouble() > 0) {
                    vanadium = it
                    resetResults()
                } else if (it.isEmpty()) {
                    vanadium = it
                }
            },
            label = { Text("Вміст ванадію, мг/кг") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
            isError = vanadium.isNotEmpty() && (vanadium.toDoubleOrNull() == null || vanadium.toDouble() <= 0),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // валідація введених значень
                if (!areAllInputsFilled()) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Будь ласка, заповніть усі " +
                                "аргументи.")
                    }
                    return@Button
                }

                Calculate()

                // виводить composable "results" з усіма обчисленими значеннями
                showResults = true

            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Розрахувати")
        }

        // снекбар для виводу повідомлень
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(top = 8.dp)
        )

        if (showResults) {
            Results(
                hydrogen,
                carbon,
                sulfur,
                oxygen,
                vanadium,
                lowerCalorificValueOfFuelOilCombustibleMass,
                moisture,
                ash,
                roundTo2Decimals(hydrogenMazut),
                roundTo2Decimals(carbonMazut),
                roundTo2Decimals(sulfurMazut),
                roundTo2Decimals(oxygenMazut),
                roundTo2Decimals(vanadiumMazut),
                roundTo2Decimals(ashMazut),
                roundTo2Decimals(combustionHeat)
                )
        }

    }
}

// composable для виводу результатів обчислень
@Composable
fun Results(
    hydrogen: String,
    carbon: String,
    sulfur: String,
    oxygen: String,
    vanadium: String,
    lowerCalorificValueOfFuelOilCombustibleMass: String,
    moisture: String,
    ash: String,
    hydrogenMazut: String,
    carbonMazut: String,
    sulfurMazut: String,
    oxygenMazut: String,
    vanadiumMazut: String,
    ashMazut: String,
    combustionHeat: String,
) {

    Text(text = "Для складу горючої маси мазуту з компонентним складом: \n" +
            "Водень - ${hydrogen}% \n" +
            "Вуглець - ${carbon}% \n" +
            "Сірка - ${sulfur}% \n" +
            "Кисень - ${oxygen}% \n" +
            "Ванадій - ${vanadium}мг/кг \n" +
            "Нижча теплота згоряння горючої маси мазуту - ${lowerCalorificValueOfFuelOilCombustibleMass}МДж/кг \n" +
            "Волога - ${moisture}% \n" +
            "Зола - ${ash}% \n")

    Text(text = "2.1. Склад робочої маси мазуту становитиме: \n" +
            "Водень - ${hydrogenMazut}%\n" +
            "Вуглець - ${carbonMazut}% \n" +
            "Сірка - ${sulfurMazut}% \n" +
            "Кисень - ${oxygenMazut}% \n" +
            "Ванадій - ${vanadiumMazut}мг/кг \n" +
            "Зола - ${ashMazut}% \n" +
            "\n" +
            "2.2 Нижча теплота згоряння мазуту на робочу масу для робочої маси за заданим складом\n" +
            "компонентів палива становить: ${combustionHeat} МДж/кг."
    )
}
