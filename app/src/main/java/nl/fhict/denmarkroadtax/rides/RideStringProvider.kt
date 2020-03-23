package nl.fhict.denmarkroadtax.rides

import android.content.Context
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.generic.dagger.ApplicationContext
import javax.inject.Inject

class RideStringProvider @Inject constructor(@ApplicationContext private val context: Context): RidesContract.StringProvider {

    override val rideCurrencie: String
        get() = context.getString(R.string.ride_currencies_euro)
    override val rideAverageEuroKm: String
        get() = context.getString(R.string.ride_average_euro_km)
    override val rideDistanceKm: String
        get() = context.getString(R.string.ride_distance_km)
    override val rideRides: String
        get() = context.getString(R.string.ride_rides)
    override val rideMin: String
        get() = context.getString(R.string.ride_min)
}