package nl.fhict.denarkroadtax.domain

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import javax.inject.Inject

class GetLocalRideRecapFromDays @Inject constructor(private val rideRepository: RideRepository) {

    operator fun invoke(): Observable<List<RideRecapOfDay>> {
        return rideRepository.fetchLocalRideRecapFromDays()
    }
}