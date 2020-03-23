package nl.fhict.denmarkroadtax.rides

import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import javax.inject.Inject

class RideMapper @Inject constructor(private val stringProvider: RidesContract.StringProvider){

    fun mapToRideRecapOfDayViewModel(rideRecapOfDay: RideRecapOfDay): RideRecapOfDayViewModel{
        with(rideRecapOfDay) {
            return RideRecapOfDayViewModel(
                date.toString("dd/MM/yyyy"),
                "$costs ${stringProvider.rideCurrencie}",
               "$average ${stringProvider.rideAverageEuroKm}",
                "${drivenMeters/ 1000} ${stringProvider.rideDistanceKm}",
                "$drivenRides ${stringProvider.rideRides} ",
                rides.map { mapToRideViewModel(it) },
                route
            )
        }
    }

    private fun mapToRideViewModel(ride: Ride): RideViewModel {
        with(ride) {
            return RideViewModel(
                id,
                startTitle,
                startAddress,
                startTime.toString("HH:mm"),
                endTitle,
                endAddress,
                endTime.toString("HH:mm"),
                "${drivenMeters /1000} ${stringProvider.rideDistanceKm}",
                "$drivenTime ${stringProvider.rideMin}",
                rideAddressType
            )
        }
    }
}