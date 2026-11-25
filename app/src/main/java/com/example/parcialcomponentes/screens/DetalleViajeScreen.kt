package com.example.parcialcomponentes.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialcomponentes.data.Expense
import com.example.parcialcomponentes.data.Trip
import java.util.Locale


@Composable
fun DetalleViajeScreen(
    trip: Trip,
    expenses: List<Expense>,
    total: Double,
    onBack: () -> Unit,
    onAddExpense: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        SimpleHeader(title = trip.name, onBack = onBack)

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(text = trip.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = trip.destination, fontSize = 14.sp)
            Text(
                text = "${trip.startDate}  –  ${trip.endDate}",
                fontSize = 13.sp
            )
        }

        Card(
            shape = RectangleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Total de gastos", fontSize = 14.sp)
                Text(
                    text = formatCurrency(total),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Text(
            text = "Gastos registrados (${expenses.size})",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(expenses, key = { it.id }) { expense ->
                ExpenseRow(expense)
            }
        }

        FlatButton(
            text = "Registrar gasto",
            onClick = onAddExpense,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun ExpenseRow(expense: Expense) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = expense.categoryName,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = expense.description, fontSize = 13.sp)
                Text(text = expense.expenseDate, fontSize = 12.sp)
            }
            Text(
                text = formatCurrency(expense.amount),
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
    }
}


fun formatCurrency(value: Double): String =
    "$ " + String.format(Locale.GERMANY, "%,.2f", value)
