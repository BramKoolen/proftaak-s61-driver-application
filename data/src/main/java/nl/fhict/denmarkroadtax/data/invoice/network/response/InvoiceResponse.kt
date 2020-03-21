package nl.fhict.denmarkroadtax.data.invoice.network.response

import com.google.gson.annotations.SerializedName

data class InvoiceResponse (

    @SerializedName("id")
    val id: Long,

    @SerializedName("month")
    val month: Int,

    @SerializedName("costs")
    val costs: Double,

    @SerializedName("paymentStatus")
    val paymentStatus: String
)