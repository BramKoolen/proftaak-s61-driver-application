package nl.fhict.denmarkroadtax.data.ride.network.response

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class RideRecapFromDayResponse(

    @SerializedName("date")
    val date: String,

    @SerializedName("costs")
    val costs: Double,

    @SerializedName("costsAverage")
    val average: Double,

    @SerializedName("drivenMeters")
    val drivenMeters: Int,

    @SerializedName("drivenRides")
    val drivenRides: Int,

    @SerializedName("rides")
    val rides: List<RideResponse>,

    @SerializedName("route")
    val route: String,

    @SerializedName("isAllDataFinal")
    val isAllDataFinal: Boolean
)