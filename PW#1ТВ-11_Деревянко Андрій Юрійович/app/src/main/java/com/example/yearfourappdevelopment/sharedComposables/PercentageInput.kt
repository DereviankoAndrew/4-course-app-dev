package com.example.yearfourappdevelopment.sharedComposables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

// інпут для відсоткових значень
@Composable
fun PercentageInput(label: String, value: String, onValueChange: (String) -> Unit) {
    // Helper function to validate inputs 1 to 6
    fun isValidInput(input: String): Boolean {
        val number = input.toFloatOrNull()
        return number != null && number in 0f..100f
    }

    TextField(
        value = value,
        onValueChange = { input ->
            if (isValidInput(input) || input.isEmpty()) {
                onValueChange(input)
            }
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
        isError = value.isNotEmpty() && !isValidInput(value),
        modifier = Modifier.fillMaxWidth()
    )
}