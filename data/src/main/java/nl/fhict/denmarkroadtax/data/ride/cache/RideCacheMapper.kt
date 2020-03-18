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

    fun mapToRideRecapOfDay(rideRecapOfDayRoom: RideRecapOfDayRoom?, ridesFromDay: List<RideRoom>?): RideRecapOfDay? {
        rideRecapOfDayRoom?.let {
            with(rideRecapOfDayRoom) {
                return RideRecapOfDay(
                    mapStringToDate(date),
                    costs,
                    average,
                    drivenKilometers,
                    drivenRides,
                    ridesFromDay?.let { it.map { mapToRide(it) } }?: emptyList(),
                    route,
                    isAllDataFinal
                )
            }
        }
        return null
    }

    fun mapToRoomRideRecapOfDay(rideRecapOfDay: RideRecapOfDay): RideRecapOfDayRoom {
        with(rideRecapOfDay){
            return RideRecapOfDayRoom(
                date.toString("dd/MM/yyyy"),
                costs,
                average,
                drivenKilometers,
                drivenRides,
                route,
                isAllDataFinal
            )
        }
    }

    fun mapToRoomRide(ride: Ride): RideRoom {
        with(ride){
            return RideRoom(
                id,
                date.toString("dd/MM/yyyy"),
                startTitle,
                startAddress,
                startTime,
                endTitle,
                endAddress,
                endTime,
                drivenKilometers,
                drivenTime,
                rideAddressType.toString()
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
}