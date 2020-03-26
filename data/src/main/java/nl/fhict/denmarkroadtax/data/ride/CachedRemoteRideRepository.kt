package nl.fhict.denmarkroadtax.data.ride

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.ride.cache.RideCacheManager
import nl.fhict.denmarkroadtax.data.ride.network.RideNetworkManager
import org.joda.time.DateTime
import org.joda.time.LocalDate
import javax.inject.Inject

class CachedRemoteRideRepository @Inject constructor(
    private val rideCacheManager: RideCacheManager,
    private val rideNetworkManager: RideNetworkManager
) : RideRepository {

        override fun fetchRideRecapFromDay(selectedDate: DateTime): Observable<List<RideRecapOfDay>> {
            return getRideRecapFromDayFromNetwork(selectedDate)
       /* val rideRecapFromDayFromDatabase = getRideRecapFromDayFromLocalDatabase(selectedDate)
        return if (rideRecapFromDayFromDatabase != null) {
            if (isDateToday(selectedDate)) {
                if (rideCacheManager.isCacheValid(MAX_VALID_CACHE_TIME_MILLISECONDS)) {
                    Observable.just(emptyList())
                } else {
                    getRideRecapFromDayFromNetwork(selectedDate)
                }
            } else {
                if (rideRecapFromDayFromDatabase.isAllDataFinal) {
                    Observable.just(emptyList())
                } else {
                    getRideRecapFromDayFromNetwork(selectedDate)
                }
            }
        } else {
            getRideRecapFromDayFromNetwork(selectedDate)
        }*/
    }

    private fun getRideRecapFromDayFromLocalDatabase(date: DateTime): RideRecapOfDay? {
        return rideCacheManager.getRideRecapFromDay(date)
    }

    private fun getRideRecapFromDayFromNetwork(selectedDate: DateTime): Observable<List<RideRecapOfDay>> {
        return rideNetworkManager.fetchRideRecapFromDay(selectedDate).doOnNext {
            rideCacheManager.deleteRideRecapFromDay(selectedDate)
            //saveRideRecapFromDayToLocalDatabase(it)
            rideCacheManager.lastRideUpdateInMillis = DateTime.now().millis
        }
    }

    private fun saveRideRecapFromDayToLocalDatabase(rideRecapOfDay: RideRecapOfDay) {
        rideCacheManager.saveRideRecapFromDay(rideRecapOfDay)
    }

    private fun isDateToday(date: DateTime): Boolean {
        return (LocalDate.now().toString("dd/MM/yyyy") == date.toString("dd/MM/yyyy"))
    }

    companion object {

        private const val MAX_VALID_CACHE_TIME_MILLISECONDS = 900000
    }
}