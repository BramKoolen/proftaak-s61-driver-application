package nl.fhict.denarkroadtax.domain.ride.model

import org.joda.time.DateTime

data class RideRecapOfDay(
    val date: DateTime,
    val costs: String,
    val average: String,
    val drivenKilometers: String,
    val drivenRides: String,
    val rides: List<Ride>,
    val route: String,
    val isAllDataFinal: Boolean
)