package nl.fhict.denarkroadtax.domain.ride.data

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import org.joda.time.DateTime

interface RideRepository {

    fun fetchRideRecapFromDay(userId: Int, date: DateTime): Observable<RideRecapOfDay>
}