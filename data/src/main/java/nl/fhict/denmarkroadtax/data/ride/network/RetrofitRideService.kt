package nl.fhict.denmarkroadtax.data.ride.network

import io.reactivex.Observable
import nl.fhict.denmarkroadtax.data.ride.network.response.RideRecapFromDayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitRideService {

    @GET("api")//TODO
    fun fetchRideRecapFromDay(@Query("results") userId: Int, date: String): Observable<RideRecapFromDayResponse>
}