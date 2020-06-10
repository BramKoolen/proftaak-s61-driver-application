package nl.fhict.denmarkroadtax.data.ride.cache

import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRecapOfDayRoom
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRoom
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import timber.log.Timber
import javax.inject.Inject

class RideCacheMapper @Inject constructor() {

    fun mapToRideRecapOfDay(
        rideRecapOfDayRoom: RideRecapOfDayRoom,
        ridesFromDay: List<RideRoom>
    ): RideRecapOfDay {
        with(rideRecapOfDayRoom) {
            return RideRecapOfDay(
                mapStringToDate(date),
                costs,
                average,
                drivenMeters,
                drivenRides,
                ridesFromDay.map { mapToRide(it) },
                isAllDataFinal
            )
        }
    }

    fun mapToRoomRideRecapOfDay(rideRecapOfDay: RideRecapOfDay): RideRecapOfDayRoom {
        with(rideRecapOfDay) {
            return RideRecapOfDayRoom(
                date.toString("dd/MM/yyyy"),
                costs,
                average,
                drivenMeters,
                drivenRides,
                isAllDataFinal
            )
        }
    }

    fun mapToRoomRide(ride: Ride): RideRoom {
        with(ride) {
            return RideRoom(
                id,
                date.toString("dd/MM/yyyy"),
                startTitle,
                startAddress,
                startTime.toString("HH:mm"),
                endTitle,
                endAddress,
                endTime.toString("HH:mm"),
                drivenMeters,
                drivenTime,
                rideAddressType.toString(),
                route
            )
        }
    }

    private fun mapToRide(rideRoom: RideRoom): Ride {
        with(rideRoom) {
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

    private fun mapStringToDate(stringDate: String): DateTime {
        return DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(stringDate)
    }

    private fun mapStringToRideAddressType(rideAddressTypeString: String): RideAddressType {
        return try {
            RideAddressType.valueOf(rideAddressTypeString)
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
            RideAddressType.STOPOVER
        }
    }

    private fun mapStringToTimeStamp(stringTimeStamp: String): DateTime {
        return DateTimeFormat.forPattern("HH:mm").parseDateTime(stringTimeStamp)
    }
}