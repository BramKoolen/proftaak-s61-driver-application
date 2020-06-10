package nl.fhict.denmarkroadtax.data.ride

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import nl.fhict.denmarkroadtax.data.ride.cache.RideCacheManager
import nl.fhict.denmarkroadtax.data.ride.network.RideNetworkManager
import org.joda.time.DateTime
import javax.inject.Inject

class CachedRemoteRideRepository @Inject constructor(
    private val rideCacheManager: RideCacheManager,
    private val rideNetworkManager: RideNetworkManager
) : RideRepository {

    override fun fetchRideRecapFromDay(selectedDate: DateTime): Observable<RideRecapOfDay> {
        return getRideRecapFromDayFromNetwork(selectedDate)
    }

    override fun fetchLocalRideRecapFromDays(): Observable<List<RideRecapOfDay>> {
        return Observable.just(getRideRecapFromDaysFromLocalDatabase())
    }

    private fun getRideRecapFromDaysFromLocalDatabase(): List<RideRecapOfDay> {
        return rideCacheManager.getRideRecapFromDays()
    }

    private fun getRideRecapFromDayFromNetwork(selectedDate: DateTime): Observable<RideRecapOfDay> {
        return rideNetworkManager.fetchRideRecapFromDay(selectedDate).doOnNext {
            if (it.isAllDataFinal) {
                saveRideRecapFromDayToLocalDatabase(it)
            }
        }
    }

    private fun saveRideRecapFromDayToLocalDatabase(rideRecapOfDay: RideRecapOfDay) {
        rideCacheManager.saveRideRecapFromDay(rideRecapOfDay)
    }
}