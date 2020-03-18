package nl.fhict.denmarkroadtax.data.ride.netwerk

import io.reactivex.Observable
import io.reactivex.Single
import nl.fhict.denmarkroadtax.data.ride.netwerk.response.RideRecapFromDayResponse
import nl.fhict.denmarkroadtax.data.ride.netwerk.response.RideResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRideService {

    @GET("api")//TODO
    fun fetchRideRecapFromDay(@Query("results") userId: Int, date: String): Observable<RideRecapFromDayResponse>
}