@file:Suppress("unused")

package nl.fhict.denmarkroadtax.data.ride

import dagger.Binds
import dagger.Module
import dagger.Provides
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denmarkroadtax.data.ride.netwerk.RetrofitRideService
import nl.fontys.denmarkroadtax.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [RideModule.Bindings::class])
class RideModule {

    @Provides
    fun provideRideService(): RetrofitRideService {
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
            .create(RetrofitRideService::class.java)
    }

    @Module
    interface Bindings {

        @Binds
        fun bindRideRepository(cachedRemoteRideRepository: CachedRemoteRideRepository): RideRepository
    }
}