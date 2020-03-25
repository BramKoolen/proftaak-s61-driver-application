package nl.fhict.denmarkroadtax.data.network.response

import com.google.gson.annotations.SerializedName

data class Response<T>(

        @SerializedName("data")
        val data: T,

        @SerializedName("status")
        val status: Status
) {
    data class Status(

            @SerializedName("code")
            val code: Int?,

            @SerializedName("message")
            val message: String
    )
}