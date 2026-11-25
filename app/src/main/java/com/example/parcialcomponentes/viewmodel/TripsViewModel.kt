package com.example.parcialcomponentes.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.parcialcomponentes.data.Category
import com.example.parcialcomponentes.data.Expense
import com.example.parcialcomponentes.data.Trip
import com.example.parcialcomponentes.data.defaultCategories
import com.example.parcialcomponentes.data.sampleExpenses
import com.example.parcialcomponentes.data.sampleTrips

class TripsViewModel : ViewModel() {

    val trips = mutableStateListOf<Trip>().apply { addAll(sampleTrips) }
    val expenses = mutableStateListOf<Expense>().apply { addAll(sampleExpenses) }
    val categories: List<Category> = defaultCategories

    private var nextTripId = (trips.maxOfOrNull { it.id } ?: 0) + 1
    private var nextExpenseId = (expenses.maxOfOrNull { it.id } ?: 0) + 1

    fun getTrip(id: Int): Trip? = trips.firstOrNull { it.id == id }

    fun expensesOf(tripId: Int): List<Expense> =
        expenses.filter { it.tripId == tripId }

    fun totalOf(tripId: Int): Double =
        expensesOf(tripId).sumOf { it.amount }

    fun addTrip(name: String, destination: String, startDate: String, endDate: String): Trip {
        val trip = Trip(
            id = nextTripId++,
            name = name.trim(),
            destination = destination.trim(),
            startDate = startDate.trim(),
            endDate = endDate.trim()
        )
        trips.add(0, trip)
        return trip
    }

    fun addExpense(
        tripId: Int,
        description: String,
        amount: Double,
        categoryId: Int,
        expenseDate: String
    ) {
        val categoryName = categories.firstOrNull { it.id == categoryId }?.name ?: "Otros"
        expenses.add(
            Expense(
                id = nextExpenseId++,
                tripId = tripId,
                description = description.trim(),
                amount = amount,
                categoryId = categoryId,
                categoryName = categoryName,
                expenseDate = expenseDate.trim()
            )
        )
    }
}
