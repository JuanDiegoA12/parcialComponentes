package com.example.parcialcomponentes.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialcomponentes.data.Trip


@Composable
fun MisViajesScreen(
    trips: List<Trip>,
    onTripClick: (Trip) -> Unit,
    onCreateTrip: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        SimpleHeader(title = "Mis viajes")

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(trips, key = { it.id }) { trip ->
                TripRow(trip = trip, onClick = { onTripClick(trip) })
            }
        }

        FlatButton(
            text = "Crear viaje",
            onClick = onCreateTrip,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
private fun TripRow(trip: Trip, onClick: () -> Unit) {
    Card(
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = trip.name, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(text = trip.destination, fontSize = 14.sp)
        }
    }
}
