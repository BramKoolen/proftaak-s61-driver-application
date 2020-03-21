package nl.fhict.denmarkroadtax.data.ride.netwerk.response

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class RideRecapFromDayResponse(

    @SerializedName("date")
    val date: DateTime,

    @SerializedName("costs")
    val costs: String,

    @SerializedName("average")
    val average: String,

    @SerializedName("drivenKilometers")
    val drivenKilometers: String,

    @SerializedName("drivenRides")
    val drivenRides: String,

    @SerializedName("rides")
    val rides: List<RideResponse>,

    @SerializedName("route")
    val route: String,

    @SerializedName("isAllDataFinal")
    val isAllDataFinal: Boolean
)