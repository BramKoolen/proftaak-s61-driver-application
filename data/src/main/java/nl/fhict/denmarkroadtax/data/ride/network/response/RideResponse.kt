package nl.fhict.denmarkroadtax.data.ride.netwerk.response

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class RideResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: DateTime,

    @SerializedName("startTitle")
    val startTitle: String,

    @SerializedName("startAddress")
    val startAddress: String,

    @SerializedName("startTime")
    val startTime: String,

    @SerializedName("endTitle")
    val endTitle: String,

    @SerializedName("endAddress")
    val endAddress: String,

    @SerializedName("endTime")
    val endTime: String,

    @SerializedName("drivenKilometers")
    val drivenKilometers: String,

    @SerializedName("drivenTime")
    val drivenTime: String,

    @SerializedName("rideAddressType")
    val rideAddressType: String
)