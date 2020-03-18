package nl.fhict.denmarkroadtax.rides

import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import javax.inject.Inject

class RideMapper @Inject constructor(){

    fun mapToRideRecapOfDayViewModel(rideRecapOfDay: RideRecapOfDay): RideRecapOfDayViewModel{
        with(rideRecapOfDay) {
            return RideRecapOfDayViewModel(
                date.toString("dd/MM/yyyy"),
                costs,
                average,
                drivenKilometers,
                drivenRides,
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
                startTime,
                endTitle,
                endAddress,
                endTime,
                drivenKilometers,
                drivenTime,
                rideAddressType
            )
        }
    }
}