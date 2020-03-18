package nl.fhict.denarkroadtax.domain.ride

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import org.joda.time.DateTime
import javax.inject.Inject

class GetRideRecapFromDay @Inject constructor(private val rideRepository: RideRepository) {

    operator fun invoke(userId: Int, date: DateTime): Observable<RideRecapOfDay> {
        return rideRepository.fetchRideRecapFromDay(userId, date)
    }
}