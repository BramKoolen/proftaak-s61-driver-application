package nl.fhict.denmarkroadtax.data.ride.network.response

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

data class RideResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: String,

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

    @SerializedName("drivenMeters")
    val drivenMeters: Int,

    @SerializedName("drivenTime")
    val drivenTime: Int,

    @SerializedName("rideAddressType")
    val rideAddressType: String
)