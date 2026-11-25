package com.example.parcialcomponentes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.example.parcialcomponentes.data.Category


@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun RegistrarGastoScreen(
    categories: List<Category>,
    onBack: () -> Unit,
    onSave: (description: String, amount: Double, categoryId: Int, expenseDate: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var expenseDate by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf(categories.firstOrNull()) }
    var expanded by remember { mutableStateOf(false) }

    val amountValue = amount.replace(',', '.').toDoubleOrNull()
    val valid = description.isNotBlank() &&
        amountValue != null && amountValue > 0 &&
        selectedCategory != null &&
        expenseDate.isNotBlank()

    Column(modifier = modifier.fillMaxSize()) {
        SimpleHeader(title = "Registrar gasto", onBack = onBack)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FieldLabel("Descripción")
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            FieldLabel("Valor")
            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                placeholder = { Text("28,50") },
                modifier = Modifier.fillMaxWidth()
            )

            FieldLabel("Categoría")
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedCategory?.name ?: "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category.name) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            FieldLabel("Fecha del gasto")
            OutlinedTextField(
                value = expenseDate,
                onValueChange = { expenseDate = it },
                singleLine = true,
                placeholder = { Text("2026-06-12") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        FlatButton(
            text = "Guardar",
            onClick = {
                val cat = selectedCategory ?: return@FlatButton
                val value = amountValue ?: return@FlatButton
                onSave(description, value, cat.id, expenseDate)
            },
            enabled = valid,
            modifier = Modifier.padding(16.dp)
        )
    }
}
