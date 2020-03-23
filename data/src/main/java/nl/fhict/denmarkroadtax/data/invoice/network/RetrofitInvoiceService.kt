package nl.fhict.denmarkroadtax.data.invoice.network

import io.reactivex.Single
import nl.fhict.denmarkroadtax.data.invoice.network.response.InvoiceResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface RetrofitInvoiceService {

    @GET("api")//TODO
    fun fetchInvoices(): Single<List<InvoiceResponse>>
}