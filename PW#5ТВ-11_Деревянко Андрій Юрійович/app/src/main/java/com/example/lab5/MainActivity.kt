
package com.example.lab5
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

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
            }
        }
    ) { innerPadding ->
        // показуємо composable в залежності від обраного табу
        when (selectedTabIndex) {
            0 -> CalculatorOne(modifier = Modifier.padding(innerPadding))
            1 -> CalculatorTwo(modifier = Modifier.padding(innerPadding))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenAppPreview() {
    MainScreen()
}