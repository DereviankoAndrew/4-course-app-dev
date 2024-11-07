package com.example.lab4

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.foundation.clickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    // стейт для індексу обраного табу
    var selectedTabIndex by remember { mutableStateOf(0) }

    // визначаємо таби
    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Define tabs
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = { selectedTabIndex = 0 },
                    text = { Text("Калькулятор 1") }
                )
                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = { selectedTabIndex = 1 },
                    text = { Text("Калькулятор 2") }
                )
                Tab(
                    selected = selectedTabIndex == 2,
                    onClick = { selectedTabIndex = 2 },
                    text = { Text("Калькулятор 3") }
                )
            }
        }
    ) { innerPadding ->
        // показуємо composable в залежності від обраного табу
        when (selectedTabIndex) {
            0 -> CableCalculatorApp(modifier = Modifier.padding(innerPadding))
            1 -> CurrentCalculatorApp(modifier = Modifier.padding(innerPadding))
            2 -> Calculator3App(modifier = Modifier.padding(innerPadding))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenAppPreview() {
    MainScreen()
}
