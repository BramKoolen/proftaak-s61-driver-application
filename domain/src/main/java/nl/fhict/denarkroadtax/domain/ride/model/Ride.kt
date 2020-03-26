package nl.fhict.denarkroadtax.domain.ride.model

import org.joda.time.DateTime

data class Ride(
    val id: Int,
    val date: DateTime,
    val startTitle: String,
    val startAddress: String,
    val startTime: DateTime,
    val endTitle: String,
    val endAddress: String,
    val endTime: DateTime,
    val drivenMeters: Int,
    val drivenTime: Int,
    val rideAddressType: RideAddressType,
    val route: String
)