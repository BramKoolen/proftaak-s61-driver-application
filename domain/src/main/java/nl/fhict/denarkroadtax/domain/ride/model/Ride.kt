package nl.fhict.denarkroadtax.domain.ride.model

import org.joda.time.DateTime

data class Ride(
    val id: Int,
    val date: DateTime,
    val startTitle: String,
    val startAddress: String,
    val startTime: String,
    val endTitle: String,
    val endAddress: String,
    val endTime: String,
    val drivenKilometers: String,
    val drivenTime: String,
    val rideAddressType: RideAddressType
)