package nl.fhict.denmarkroadtax.data.invoice.network

import io.reactivex.Observable
import nl.fhict.denmarkroadtax.data.invoice.network.response.InvoiceResponse
import retrofit2.http.GET

interface RetrofitInvoiceModule {

    @GET("api")//TODO
    fun fetchInvoices(): Observable<InvoiceResponse>
}