package com.jlw.unitconverter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlw.unitconverter.ui.theme.UnitConverterTheme
import com.jlw.unitconverter.ui.theme.Blue

const val TAG = "UnitConverter"
val SPACER_SIZE = 16.dp
val PADDING_DP = 8.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {
    val unitConverterUtils = UnitConverterUtils()
    var primaryInput by remember { mutableStateOf("") }
    var secondaryInput by remember { mutableStateOf("") }
    var primaryInputUnit by remember { mutableStateOf(Distance.MILLIMETRE) }
    var secondaryInputUnit by remember { mutableStateOf(Distance.METRE) }
    var primaryDropdownExpanded by remember { mutableStateOf(false) }
    var secondaryDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.size(SPACER_SIZE))

        // Container for the 'primary' input text, button & dropdown

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = primaryInput,
                onValueChange = {
                    if (unitConverterUtils.isInputTextPermitted(it)) {
                        primaryInput = it
                        val conversionFactor = Distance.getConversionFactor(primaryInputUnit, secondaryInputUnit)
                        val input =
                            (primaryInput.toDoubleOrNull()?.times(conversionFactor)) ?: 0.0
                        secondaryInput = unitConverterUtils.getCleanOutput(input)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Box(modifier = Modifier.padding(PADDING_DP), contentAlignment = Alignment.Center) {
                Button(
                    onClick = { primaryDropdownExpanded = true },
                    shape = TextFieldDefaults.outlinedShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                    contentPadding = ButtonDefaults.TextButtonContentPadding
                ) {
                    Text(primaryInputUnit.name)
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = primaryDropdownExpanded,
                    onDismissRequest = { primaryDropdownExpanded = false }
                ) {
                    enumValues<Distance>().forEach {
                        DropdownMenuItem(text = { Text(it.name) },
                            onClick = {
                                primaryDropdownExpanded = false
                                if (primaryInputUnit != it) {
                                    primaryInputUnit = it
                                    val conversionFactor = Distance.getConversionFactor(primaryInputUnit, secondaryInputUnit)
                                    val input =
                                        (primaryInput.toDoubleOrNull()?.times(conversionFactor)) ?: 0.0
                                    secondaryInput = unitConverterUtils.getCleanOutput(input)
                                }
                            }
                        )
                    }  // Primary Dropdown Items
                }  // Primary Dropdown Menu
            }  // Primary Button Box Container
        }  // Primary Box Container

        Spacer(modifier = Modifier.size(SPACER_SIZE))

        // Container for the 'secondary' input text, button & dropdown

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = secondaryInput,
                onValueChange = {
                    if (unitConverterUtils.isInputTextPermitted(it)) {
                        secondaryInput = it
                        val conversionFactor = Distance.getConversionFactor(secondaryInputUnit, primaryInputUnit)
                        val input =
                            (secondaryInput.toDoubleOrNull()?.times(conversionFactor)) ?: 0.0
                        primaryInput = unitConverterUtils.getCleanOutput(input)
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true
            )

            Box(modifier = Modifier.padding(PADDING_DP), contentAlignment = Alignment.Center) {
                Button(
                    onClick = { secondaryDropdownExpanded = true },
                    shape = TextFieldDefaults.outlinedShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Blue),
                    contentPadding = ButtonDefaults.TextButtonContentPadding
                ) {
                    Text(secondaryInputUnit.name)
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = null)
                }

                DropdownMenu(
                    expanded = secondaryDropdownExpanded,
                    onDismissRequest = {secondaryDropdownExpanded = false }) {
                    enumValues<Distance>().forEach {
                        DropdownMenuItem(text = { Text(it.name) },
                            onClick = {
                                secondaryDropdownExpanded = false
                                if (secondaryInputUnit != it) {
                                    secondaryInputUnit = it
                                    val conversionFactor = Distance.getConversionFactor(secondaryInputUnit, primaryInputUnit)
                                    val input =
                                        (secondaryInput.toDoubleOrNull()?.times(conversionFactor)) ?: 0.0
                                    primaryInput = unitConverterUtils.getCleanOutput(input)
                                }
                            }
                        )
                    }  // Secondary Dropdown Items
                }  // Secondary Dropdown Menu
            }  // Secondary Button Box Container
        }  // Secondary Box Container
    }  // Column
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}