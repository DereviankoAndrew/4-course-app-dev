package com.example.yearfourappdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.yearfourappdevelopment.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import com.example.yearfourappdevelopment.sharedComposables.PercentageInput
import com.example.yearfourappdevelopment.utils.toDoubleOrZero
import java.math.BigDecimal
import java.math.RoundingMode

class Variable(
    value: Double = 0.0,
    val description: String = "",
    val sign: String = ""
) {
    var value by mutableStateOf(value)
}

@Composable
fun CalculatorOne(modifier: Modifier = Modifier) {

    // State variables for each input
    val CarbonWorking = Variable(
        value = 0.0,
        description = "Вуглець (робоче паливо)",
        sign = "Cw"
    )
    val HydrogenWorking = Variable(
        value = 0.0,
        description = "Водень (робоче паливо)",
        sign = "Hw"
    )
    val SulfurWorking = Variable(
        value = 0.0,
        description = "Сірка (робоче паливо)",
        sign = "Sw"
    )
    val OxygenWorking = Variable(
        value = 0.0,
        description = "Кисень (робоче паливо)",
        sign = "Ow"
    )
    val NitrogenWorking = Variable(
        value = 0.0,
        description = "Азот (робоче паливо)",
        sign = "Nw"
    )
    val Moisture = Variable(
        value = 0.0,
        description = "Волога",
        sign = "W"
    )
    val Ash = Variable(
        value = 0.0,
        description = "Зола",
        sign = "A"
    )

    var HydrogenWorkingInput by remember { mutableStateOf("1.9") }
    var CarbonWorkingInput by remember { mutableStateOf("21.1") }
    var SulfurWorkingInput by remember { mutableStateOf("2.6") }
    var NitrogenWorkingInput by remember { mutableStateOf("0.2") }
    var OxygenWorkingInput by remember { mutableStateOf("7.1") }
    var MoistureInput by remember { mutableStateOf("53") }
    var AshInput by remember { mutableStateOf("14.1") }

    var showResults by remember { mutableStateOf(false) }

    // Snackbar state and coroutine scope
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var ConversionRatioFromWorkingToDryMass by remember { mutableDoubleStateOf(0.0) }
    var ConversionRatioFromWorkingToCombustibleMass by remember { mutableDoubleStateOf(0.0) }

    var CarbonDry by remember { mutableDoubleStateOf(0.0) }
    var HydrogenDry by remember { mutableDoubleStateOf(0.0) }
    var SulfurDry by remember { mutableDoubleStateOf(0.0) }
    var OxygenDry by remember { mutableDoubleStateOf(0.0) }
    var NitrogenDry by remember { mutableDoubleStateOf(0.0) }
    var AshDry by remember { mutableDoubleStateOf(0.0) }

    var CarbonCombustile by remember { mutableDoubleStateOf(0.0) }
    var HydrogenCombustile by remember { mutableDoubleStateOf(0.0) }
    var SulfurCombustile by remember { mutableDoubleStateOf(0.0) }
    var OxygenCombustile by remember { mutableDoubleStateOf(0.0) }
    var NitrogenCombustile by remember { mutableDoubleStateOf(0.0) }

    var CombustionHeat by remember { mutableDoubleStateOf(0.0) }

    var LowerCalorificValueForDryMass by remember { mutableDoubleStateOf(0.0) }
    var LowerCalorificValueForCombustibleMass by remember { mutableDoubleStateOf(0.0) }

    fun resetResults() {
        showResults = false
    }

    // Function to check if all inputs are not empty
    fun areAllInputsFilled(): Boolean {
        return HydrogenWorkingInput.isNotEmpty() &&
                CarbonWorkingInput.isNotEmpty() &&
                SulfurWorkingInput.isNotEmpty() &&
                NitrogenWorkingInput.isNotEmpty() &&
                OxygenWorkingInput.isNotEmpty() &&
                MoistureInput.isNotEmpty() &&
                AshInput.isNotEmpty()
    }

    fun calculateInputSum(): Double {
        return toDoubleOrZero(CarbonWorkingInput) +
                toDoubleOrZero(HydrogenWorkingInput) +
                toDoubleOrZero(SulfurWorkingInput) +
                toDoubleOrZero(NitrogenWorkingInput) +
                toDoubleOrZero(OxygenWorkingInput) +
                toDoubleOrZero(MoistureInput) +
                toDoubleOrZero(AshInput)
    }

    fun roundTo2Decimals(value: Double): String {
        return BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()
    }

    fun Calculate(
        CarbonWorking: Double,
        HydrogenWorking: Double,
        SulfurWorking: Double,
        OxygenWorking: Double,
        NitrogenWorking: Double,
        Moisture: Double,
        Ash: Double,
    ) {

        ConversionRatioFromWorkingToDryMass = 100/(100-Moisture)
        ConversionRatioFromWorkingToCombustibleMass = 100/(100-Moisture-Ash)

        CarbonDry = CarbonWorking * ConversionRatioFromWorkingToDryMass
        HydrogenDry = HydrogenWorking * ConversionRatioFromWorkingToDryMass
        SulfurDry = SulfurWorking * ConversionRatioFromWorkingToDryMass
        OxygenDry = OxygenWorking * ConversionRatioFromWorkingToDryMass
        NitrogenDry = NitrogenWorking * ConversionRatioFromWorkingToDryMass
        AshDry = Ash * ConversionRatioFromWorkingToDryMass

        var sum = CarbonDry +
                HydrogenDry +
                SulfurDry +
                OxygenDry +
                NitrogenDry +
                AshDry

        CarbonCombustile = CarbonWorking * ConversionRatioFromWorkingToCombustibleMass
        HydrogenCombustile = HydrogenWorking * ConversionRatioFromWorkingToCombustibleMass
        SulfurCombustile = SulfurWorking * ConversionRatioFromWorkingToCombustibleMass
        OxygenCombustile = OxygenWorking * ConversionRatioFromWorkingToCombustibleMass
        NitrogenCombustile = NitrogenWorking * ConversionRatioFromWorkingToCombustibleMass

        var sum2 = CarbonCombustile +
                HydrogenCombustile +
                SulfurCombustile +
                OxygenCombustile +
                NitrogenCombustile

        CombustionHeat = 339 * CarbonWorking + 1030 * HydrogenWorking - 108.8 *
                (OxygenWorking - SulfurWorking) - 25 * Moisture

        LowerCalorificValueForDryMass =
            (CombustionHeat / 1000 + 0.025 * Moisture) *
                    (100 / (100 - Moisture))

        LowerCalorificValueForCombustibleMass =
            (CombustionHeat / 1000 + (0.025 * Moisture)) *
                    (100 / (100 - Moisture - Ash))

    }



    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(text = "Розрахунок складу сухої та горючої маси палива та нижчої теплоти згоряння для" +
                " робочої, сухої та горючої маси")

        PercentageInput(
            label = "${HydrogenWorking.description} (${HydrogenWorking.sign})",
            value = HydrogenWorkingInput,
            onValueChange = {
                HydrogenWorkingInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${CarbonWorking.description} (${CarbonWorking.sign})",
            value = CarbonWorkingInput,
            onValueChange = {
                CarbonWorkingInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${SulfurWorking.description} (${SulfurWorking.sign})",
            value = SulfurWorkingInput,
            onValueChange = {
                SulfurWorkingInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${NitrogenWorking.description} (${NitrogenWorking.sign})",
            value = NitrogenWorkingInput,
            onValueChange = {
                NitrogenWorkingInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${OxygenWorking.description} (${OxygenWorking.sign})",
            value = OxygenWorkingInput,
            onValueChange = {
                OxygenWorkingInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${Moisture.description} (${Moisture.sign})",
            value = MoistureInput,
            onValueChange = {
                MoistureInput = it
                resetResults()
            }
        )

        PercentageInput(
            label = "${Ash.description} (${Ash.sign})",
            value = AshInput,
            onValueChange = {
                AshInput = it
                resetResults()
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp), // Adjust padding as needed
            horizontalArrangement = Arrangement.SpaceBetween, // Spreads elements evenly in the row
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button to validate inputs and log them
            Button(
                onClick = {
                    if (!areAllInputsFilled()) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Будь ласка, заповніть усі " +
                                    "аргументи.")
                        }
                        return@Button
                    }

                    if (calculateInputSum() != 100.0) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Запевніться, що сума аргументів " +
                                    "дорівнює 100.")
                        }
                        return@Button
                    }

                    Calculate(
                        CarbonWorking = toDoubleOrZero(CarbonWorkingInput),
                        HydrogenWorking = toDoubleOrZero(HydrogenWorkingInput),
                        SulfurWorking = toDoubleOrZero(SulfurWorkingInput),
                        OxygenWorking = toDoubleOrZero(OxygenWorkingInput),
                        NitrogenWorking = toDoubleOrZero(NitrogenWorkingInput),
                        Moisture = toDoubleOrZero(MoistureInput),
                        Ash = toDoubleOrZero(AshInput),
                    )

                    showResults = true

                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Розрахувати")
            }

            Text(text = "Сума: " + roundTo2Decimals(calculateInputSum()))
        }

        if (showResults) {
            Results(
                HydrogenWorkingInput,
                CarbonWorkingInput,
                SulfurWorkingInput,
                NitrogenWorkingInput,
                OxygenWorkingInput,
                MoistureInput,
                AshInput,

                roundTo2Decimals(ConversionRatioFromWorkingToDryMass),
                roundTo2Decimals(ConversionRatioFromWorkingToCombustibleMass),

                roundTo2Decimals(CarbonDry),
                roundTo2Decimals(HydrogenDry),
                roundTo2Decimals(SulfurDry),
                roundTo2Decimals(OxygenDry),
                roundTo2Decimals(NitrogenDry),
                roundTo2Decimals(AshDry),

                roundTo2Decimals(CarbonCombustile),
                roundTo2Decimals(HydrogenCombustile),
                roundTo2Decimals(SulfurCombustile),
                roundTo2Decimals(OxygenCombustile),
                roundTo2Decimals(NitrogenCombustile),

                roundTo2Decimals(CombustionHeat),

                roundTo2Decimals(LowerCalorificValueForDryMass),
                roundTo2Decimals(LowerCalorificValueForCombustibleMass),

                )
        }




        // Snackbar Host to display messages
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun Results(
    HydrogenWorkingInput: String,
    CarbonWorkingInput: String,
    SulfurWorkingInput: String,
    NitrogenWorkingInput: String,
    OxygenWorkingInput: String,
    MoistureInput: String,
    AshInput: String,

    ConversionRatioFromWorkingToDryMass: String,
    ConversionRatioFromWorkingToCombustibleMass: String,

    CarbonDry: String,
    HydrogenDry: String,
    SulfurDry: String,
    OxygenDry: String,
    NitrogenDry: String,
    AshDry: String,

    CarbonCombustile: String,
    HydrogenCombustile: String,
    SulfurCombustile: String,
    OxygenCombustile: String,
    NitrogenCombustile: String,

    CombustionHeat: String,

    LowerCalorificValueForDryMass: String,
    LowerCalorificValueForCombustibleMass: String
) {

    Text(text = "Для палива з компонентним складом: " +
            "Водень - ${HydrogenWorkingInput}% \n" +
            "Вуглець - ${CarbonWorkingInput}% \n" +
            "Сірка - ${SulfurWorkingInput}% \n" +
            "Азот - ${NitrogenWorkingInput}% \n" +
            "Кисень - ${OxygenWorkingInput}% \n" +
            "Волога - ${MoistureInput}% \n" +
            "Зола - ${AshInput}% \n")

    Text(text = "1.1. Коефіцієнт переходу від робочої до сухої маси становить: ${ConversionRatioFromWorkingToDryMass}\n" +
            "\n" +
            "1.2. Коефіцієнт переходу від робочої до горючої маси становить: ${ConversionRatioFromWorkingToCombustibleMass}\n" +
            "\n" +
            "1.3. Склад сухої маси палива становитиме: \n" +
            "Водень = ${HydrogenDry}%\n" +
            "Вуглець = ${CarbonDry}%\n" +
            "Сірка = ${SulfurDry}%\n" +
            "Кисень = ${OxygenDry}%\n" +
            "Азот = ${NitrogenDry}%\n" +
            "Зола = ${AshDry}%\n" +
            "\n" +
            "1.4. Склад горючої маси палива становитиме: \n" +
            "Водень = ${HydrogenCombustile}%\n" +
            "Вуглець = ${CarbonCombustile}%\n" +
            "Сірка = ${SulfurCombustile}%\n" +
            "Кисень = ${OxygenCombustile}%\n" +
            "Азот = ${NitrogenCombustile}%\n" +
            "\n" +
            "1.5. Нижча теплота згоряння для робочої маси за заданим складом компонентів палива\n" +
            "становить: ${CombustionHeat.toDouble()/1000}, МДж/кг\n" +
            "\n" +
            "1.6. Нижча теплота згоряння для сухої маси за заданим складом компонентів палива\n" +
            "становить: ${LowerCalorificValueForDryMass}, МДж/кг;\n" +
            "\n" +
            "1.7. Нижча теплота згоряння для горючої маси за заданим складом компонентів палива\n" +
            "становить: ${LowerCalorificValueForCombustibleMass}, МДж/кг.")
}