package nl.fhict.denmarkroadtax.data.ride.network

import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.ride.network.response.RideRecapFromDayResponse
import nl.fhict.denmarkroadtax.data.ride.network.response.RideResponse
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import javax.inject.Inject

class RideNetworkMapper @Inject constructor() {

    fun mapToRideRecapOfDay(rideRecapFromDayResponse: RideRecapFromDayResponse): RideRecapOfDay {
        with(rideRecapFromDayResponse) {
            return RideRecapOfDay(
                mapStringToDate(date),
                costs,
                average,
                drivenMeters,
                drivenRides,
                rides.map { mapToRide(it) },
                isAllDataFinal
            )
        }
    }

    private fun mapToRide(rideResponse: RideResponse): Ride {
        with(rideResponse) {
            return Ride(
                id,
                mapStringToDate(date),
                startTitle,
                startAddress,
                mapStringToTimeStamp(startTime),
                endTitle,
                endAddress,
                mapStringToTimeStamp(endTime),
                drivenMeters,
                drivenTime,
                mapStringToRideAddressType(rideAddressType),
                route
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

    private fun mapStringToDate(stringDate: String): DateTime {
        return DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(stringDate)
    }

    private fun mapStringToTimeStamp(stringTimeStamp: String): DateTime {
        return DateTimeFormat.forPattern("HH:mm").parseDateTime(stringTimeStamp)
    }
}