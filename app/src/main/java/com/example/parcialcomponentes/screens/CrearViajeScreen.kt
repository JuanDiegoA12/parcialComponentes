package com.example.parcialcomponentes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun CrearViajeScreen(
    onBack: () -> Unit,
    onSave: (name: String, destination: String, startDate: String, endDate: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var destination by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    val valid = name.isNotBlank() && destination.isNotBlank() &&
        startDate.isNotBlank() && endDate.isNotBlank()

    Column(modifier = modifier.fillMaxSize()) {
        SimpleHeader(title = "Crear viaje", onBack = onBack)

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FieldLabel("Nombre del viaje")
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            FieldLabel("Destino")
            OutlinedTextField(
                value = destination,
                onValueChange = { destination = it },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    FieldLabel("Fecha de inicio")
                    OutlinedTextField(
                        value = startDate,
                        onValueChange = { startDate = it },
                        singleLine = true,
                        placeholder = { Text("2026-06-10") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    FieldLabel("Fecha de fin")
                    OutlinedTextField(
                        value = endDate,
                        onValueChange = { endDate = it },
                        singleLine = true,
                        placeholder = { Text("2026-06-20") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        FlatButton(
            text = "Guardar",
            onClick = { onSave(name, destination, startDate, endDate) },
            enabled = valid,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun FieldLabel(text: String) {
    Text(text = text)
}
