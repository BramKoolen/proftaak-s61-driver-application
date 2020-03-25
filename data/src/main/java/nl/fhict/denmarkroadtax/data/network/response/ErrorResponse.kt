package nl.fhict.denmarkroadtax.data.network.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

        @SerializedName("status")
        val status: Response.Status
)