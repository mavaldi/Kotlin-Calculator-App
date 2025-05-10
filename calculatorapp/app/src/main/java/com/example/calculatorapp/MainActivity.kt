package com.example.calculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.ui.theme.CalculatorappTheme
import kotlin.math.PI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0.0) }

    var areaInput by remember { mutableStateOf("") }
    var areaResult by remember { mutableStateOf(0.0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Divider(modifier = Modifier.padding(vertical = 5.dp))

        // Basic Calculator Section
        Text("Basic Calculator", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("First number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Second number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    result = (number1.toDoubleOrNull() ?: 0.0) + (number2.toDoubleOrNull() ?: 0.0)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("+")
            }

            Button(
                onClick = {
                    result = (number1.toDoubleOrNull() ?: 0.0) - (number2.toDoubleOrNull() ?: 0.0)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("-")
            }

            Button(
                onClick = {
                    result = (number1.toDoubleOrNull() ?: 0.0) * (number2.toDoubleOrNull() ?: 0.0)
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("×")
            }

            Button(
                onClick = {
                    val num2 = number2.toDoubleOrNull() ?: 1.0
                    result = if (num2 != 0.0) {
                        (number1.toDoubleOrNull() ?: 0.0) / num2
                    } else {
                        Double.NaN // Handle division by zero
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("÷")
            }
        }

        Text(text = "Result:", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "$result",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 30.sp
        )

        Divider(modifier = Modifier.padding(vertical = 20.dp))

        // Area Calculator Section
        Text("Area Calculator", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = areaInput,
            onValueChange = { areaInput = it },
            label = { Text("Square side/Circle radius") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    val input = areaInput.toDoubleOrNull() ?: 0.0
                    areaResult = input * input // Square area
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Square Area □")
            }

            Button(
                onClick = {
                    val input = areaInput.toDoubleOrNull() ?: 0.0
                    areaResult = PI * input * input // Circle area
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Circle Area ◯")
            }
        }

        Text(text = "Area Result:", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "$areaResult",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 30.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
    CalculatorappTheme {
        CalculatorScreen()
    }
}