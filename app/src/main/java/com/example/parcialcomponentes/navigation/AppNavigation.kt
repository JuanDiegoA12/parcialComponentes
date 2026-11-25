package com.example.parcialcomponentes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcialcomponentes.screens.CrearViajeScreen
import com.example.parcialcomponentes.screens.DetalleViajeScreen
import com.example.parcialcomponentes.screens.MisViajesScreen
import com.example.parcialcomponentes.screens.RegistrarGastoScreen
import com.example.parcialcomponentes.viewmodel.TripsViewModel

private sealed interface Screen {
    data object MisViajes : Screen
    data object CrearViaje : Screen
    data class DetalleViaje(val tripId: Int) : Screen
    data class RegistrarGasto(val tripId: Int) : Screen
}


@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModel: TripsViewModel = viewModel()
) {
    var current by remember { mutableStateOf<Screen>(Screen.MisViajes) }

    when (val screen = current) {
        is Screen.MisViajes -> MisViajesScreen(
            trips = viewModel.trips,
            onTripClick = { trip -> current = Screen.DetalleViaje(trip.id) },
            onCreateTrip = { current = Screen.CrearViaje },
            modifier = modifier
        )

        is Screen.CrearViaje -> CrearViajeScreen(
            onBack = { current = Screen.MisViajes },
            onSave = { name, destination, startDate, endDate ->
                viewModel.addTrip(name, destination, startDate, endDate)
                current = Screen.MisViajes
            },
            modifier = modifier
        )

        is Screen.DetalleViaje -> {
            val trip = viewModel.getTrip(screen.tripId)
            if (trip == null) {
                current = Screen.MisViajes
            } else {
                DetalleViajeScreen(
                    trip = trip,
                    expenses = viewModel.expensesOf(trip.id),
                    total = viewModel.totalOf(trip.id),
                    onBack = { current = Screen.MisViajes },
                    onAddExpense = { current = Screen.RegistrarGasto(trip.id) },
                    modifier = modifier
                )
            }
        }

        is Screen.RegistrarGasto -> RegistrarGastoScreen(
            categories = viewModel.categories,
            onBack = { current = Screen.DetalleViaje(screen.tripId) },
            onSave = { description, amount, categoryId, expenseDate ->
                viewModel.addExpense(screen.tripId, description, amount, categoryId, expenseDate)
                current = Screen.DetalleViaje(screen.tripId)
            },
            modifier = modifier
        )
    }
}
