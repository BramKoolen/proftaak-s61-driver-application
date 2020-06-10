package nl.fhict.denmarkroadtax.data.ride.cache

import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.generic.room.RoomDRTDatabase
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRecapOfDayRoom
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRoom
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RideCacheManager @Inject constructor(private val rideDatabase: RoomDRTDatabase,
                                           private val rideCacheMapper: RideCacheMapper) {

    fun getRideRecapFromDays(): List<RideRecapOfDay> {
        return fetchRideRecapFromDays().map {
            rideCacheMapper.mapToRideRecapOfDay(it, fetchRidesFromDay(it.date) )
        }
    }

    fun saveRideRecapFromDay(rideRecapOfDay: RideRecapOfDay) {
        rideDatabase.roomRideRecapFromDayDao()
            .saveRideRecapFromDay(rideCacheMapper.mapToRoomRideRecapOfDay(rideRecapOfDay))
        rideDatabase.roomRideDao()
            .saveRidesFromDay(rideRecapOfDay.rides.map { rideCacheMapper.mapToRoomRide(it) })
    }

    private fun fetchRideRecapFromDays(): List<RideRecapOfDayRoom> {
        return rideDatabase.roomRideRecapFromDayDao().getRideRecapFromDays()?: emptyList()
    }

    private fun fetchRidesFromDay(date: String): List<RideRoom> {
        return rideDatabase.roomRideDao().getRidesFromDay(date)?: emptyList()
    }
}