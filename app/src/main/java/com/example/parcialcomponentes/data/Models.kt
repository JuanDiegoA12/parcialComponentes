package com.example.parcialcomponentes.data

data class Category(
    val id: Int,
    val name: String
)

data class Expense(
    val id: Int,
    val tripId: Int,
    val description: String,
    val amount: Double,
    val categoryId: Int,
    val categoryName: String,
    val expenseDate: String
)

data class Trip(
    val id: Int,
    val name: String,
    val destination: String,
    val startDate: String,
    val endDate: String
)


val defaultCategories = listOf(
    Category(1, "Alimentación"),
    Category(2, "Alojamiento"),
    Category(3, "Transporte"),
    Category(4, "Actividades"),
    Category(5, "Compras"),
    Category(6, "Salud"),
    Category(7, "Otros")
)


val sampleTrips = listOf(
    Trip(1, "Europa 2026", "Italia", "2026-06-10", "2026-06-20"),
    Trip(2, "Santa Marta", "Colombia", "2026-07-01", "2026-07-08"),
    Trip(3, "Bogotá Cultural", "Colombia", "2026-08-15", "2026-08-18")
)

val sampleExpenses = listOf(
    Expense(1, 1, "Cena en Trastevere", 28.50, 1, "Alimentación", "2026-06-12"),
    Expense(2, 1, "Hotel centro", 260.00, 2, "Alojamiento", "2026-06-10"),
    Expense(3, 1, "Tren Roma-Florencia", 7.00, 3, "Transporte", "2026-06-13")
)
