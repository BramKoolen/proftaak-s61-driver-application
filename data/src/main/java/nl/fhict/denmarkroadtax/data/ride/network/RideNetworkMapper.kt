package nl.fhict.denmarkroadtax.data.ride.netwerk

import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.ride.netwerk.response.RideRecapFromDayResponse
import nl.fhict.denmarkroadtax.data.ride.netwerk.response.RideResponse
import timber.log.Timber
import javax.inject.Inject

class RideNetworkMapper @Inject constructor() {

    fun mapToRideRecapOfDay(rideRecapFromDayResponse: RideRecapFromDayResponse): RideRecapOfDay {
        with(rideRecapFromDayResponse) {
            return RideRecapOfDay(
                date,
                costs,
                average,
                drivenKilometers,
                drivenRides,
                rides.map { mapToRide(it) },
                route,
                isAllDataFinal
            )
        }
    }

    private fun mapToRide(rideResponse: RideResponse): Ride {
        with(rideResponse) {
            return Ride(
                id,
                date,
                startTitle,
                startAddress,
                startTime,
                endTitle,
                endAddress,
                endTime,
                drivenKilometers,
                drivenTime,
                mapStringToRideAddressType(rideAddressType)
            )
        }
    }

    private fun mapStringToRideAddressType(rideAddressTypeString: String): RideAddressType {
        return try {
            RideAddressType.valueOf(rideAddressTypeString)
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
            RideAddressType.STOPOVER
        }
    }
}