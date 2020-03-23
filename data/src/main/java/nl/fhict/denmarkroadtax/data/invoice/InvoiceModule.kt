@file:Suppress("unused")

package nl.fhict.denmarkroadtax.data.invoice

import dagger.Binds
import dagger.Module
import dagger.Provides
import nl.fhict.denarkroadtax.domain.invoice.data.InvoiceRepository
import nl.fhict.denmarkroadtax.data.invoice.network.RetrofitInvoiceService
import nl.fontys.denmarkroadtax.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [InvoiceModule.Bindings::class])
class InvoiceModule {

    @Provides
    fun provideInvoiceService(): RetrofitInvoiceService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient
                    .Builder()
                    .build()
            )
            .build()
            .create(RetrofitInvoiceService::class.java)
    }

    @Module
    interface Bindings {

        @Binds
        fun bindRideRepository(remoteInvoiceRepository: RemoteInvoiceRepository): InvoiceRepository
    }
}