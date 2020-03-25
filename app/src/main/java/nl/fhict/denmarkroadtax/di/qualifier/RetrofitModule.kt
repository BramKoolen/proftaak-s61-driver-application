package nl.fhict.denmarkroadtax.di.qualifier

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import nl.fhict.denmarkroadtax.data.invoice.network.InvoiceService
import nl.fhict.denmarkroadtax.data.account.network.AuthenticationService
import nl.fhict.denmarkroadtax.data.ride.network.RideService
import nl.fhict.denmarkroadtax.di.OkHttpModule
import nl.fontys.denmarkroadtax.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpModule::class])
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @DefaultRetrofit
    fun provideElectrolyteRetrofit(
        callAdapter: RxJava2CallAdapterFactory,
        @OAuthOkHttpClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @AuthenticationRetrofit
    fun provideLoginRetrofit(
        callAdapter: RxJava2CallAdapterFactory,
        @BasicHttpClient okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }



    @Provides
    fun provideInvoiceService(@DefaultRetrofit retrofit: Retrofit): InvoiceService {
        return retrofit.create(InvoiceService::class.java)
    }

    @Provides
    fun provideRideService(@DefaultRetrofit retrofit: Retrofit): RideService {
        return retrofit.create(RideService::class.java)
    }

    @Provides
    fun provideAuthenticationService(@AuthenticationRetrofit retrofit: Retrofit): AuthenticationService {
        return retrofit.create(AuthenticationService::class.java)
    }
}
