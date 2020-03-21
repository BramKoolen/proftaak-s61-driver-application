package nl.fhict.denarkroadtax.domain.ride.model

import org.joda.time.DateTime

data class RideRecapOfDay(
    val date: DateTime,
    val costs: Double,
    val average: Double,
    val drivenMeters: Int,
    val drivenRides: Int,
    val rides: List<Ride>,
    val route: String,
    val isAllDataFinal: Boolean
)