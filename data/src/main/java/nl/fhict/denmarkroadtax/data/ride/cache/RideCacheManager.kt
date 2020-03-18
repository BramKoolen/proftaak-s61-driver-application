package nl.fhict.denmarkroadtax.data.ride.cache

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.generic.dagger.DataContext
import nl.fhict.denmarkroadtax.data.generic.room.RoomDRTDatabase
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRecapOfDayRoom
import nl.fhict.denmarkroadtax.data.ride.cache.room.RideRoom
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.abs

@Singleton
class RideCacheManager @Inject constructor(
    @DataContext context: Context,
    private val rideDatabase: RoomDRTDatabase,
    private val rideCacheMapper: RideCacheMapper
) {

    private val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(RIDE_STORAGE, Context.MODE_PRIVATE)
    }

    var lastRideUpdateInMillis: Long
        get() = sharedPrefs.getLong(LAST_RIDE_UPDATE_DATE_TIME, 0)
        set(value) {
            sharedPrefs.edit()
                .putLong(LAST_RIDE_UPDATE_DATE_TIME, value)
                .apply()
        }

    fun getRideRecapFromDay(date: DateTime): RideRecapOfDay? {
        return rideCacheMapper.mapToRideRecapOfDay(fetchRideRecapFromDay(date), fetchRidesFromDay(date))
    }

    fun deleteRideRecapFromDay(date: DateTime){
        rideDatabase.roomRideRecapFromDayDao().deleteAllRideRecapFromDay(date.toString("dd/MM/yyyy"))
        rideDatabase.roomRideDao().deleteAllRidesFromDay(date.toString("dd/MM/yyyy"))
    }

    fun saveRideRecapFromDay(rideRecapOfDay: RideRecapOfDay) {
        rideDatabase.roomRideRecapFromDayDao()
            .saveRideRecapFromDay(rideCacheMapper.mapToRoomRideRecapOfDay(rideRecapOfDay))
        rideDatabase.roomRideDao()
            .saveRidesFromDay(rideRecapOfDay.rides.map { rideCacheMapper.mapToRoomRide(it) })
    }

    fun isCacheValid(maxValidCacheTimeMilliseconds: Int): Boolean {
        val diffInMillis = abs(DateTime().millis - (lastRideUpdateInMillis))
        return diffInMillis < maxValidCacheTimeMilliseconds
    }

    private fun fetchRideRecapFromDay(date: DateTime): RideRecapOfDayRoom?{
        return rideDatabase.roomRideRecapFromDayDao().getRideRecapFromDay(date.toString("dd/MM/yyyy"))
    }

    private fun fetchRidesFromDay(date: DateTime):List<RideRoom>?{
        return rideDatabase.roomRideDao().getRidesFromDay(date.toString("dd/MM/yyyy"))
    }

    companion object {

        const val RIDE_STORAGE = "RIDE_STORAGE"
        const val LAST_RIDE_UPDATE_DATE_TIME = "LAST_RIDE_UPDATE_DATE_TIME"
    }
}