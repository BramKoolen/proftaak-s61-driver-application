package nl.fhict.denmarkroadtax.rides

import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType

data class RideViewModel(
    val id: Int,
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