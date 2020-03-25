package nl.fhict.denmarkroadtax.data.invoice.network

import io.reactivex.Single
import nl.fhict.denmarkroadtax.data.invoice.network.response.InvoiceResponse
import retrofit2.http.GET

interface InvoiceService {

    @GET("api")//TODO
    fun fetchInvoices(): Single<List<InvoiceResponse>>
}