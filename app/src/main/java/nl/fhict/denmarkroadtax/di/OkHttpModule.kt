@file:Suppress("unused")

package nl.fhict.denmarkroadtax.di

import android.content.Context
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import nl.fhict.denmarkroadtax.BuildConfig
import nl.fhict.denmarkroadtax.data.generic.dagger.DataContext
import nl.fhict.denmarkroadtax.data.network.interceptor.ApiCredentialsInterceptor
import nl.fhict.denmarkroadtax.data.network.interceptor.OAuthInterceptor
import nl.fhict.denmarkroadtax.di.qualifier.BasicHttpClient
import nl.fhict.denmarkroadtax.di.qualifier.OAuthOkHttpClient
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class OkHttpModule {

    @Provides
    @OAuthOkHttpClient
    fun provideOAuthClient(
        apiCredentialsInterceptor: ApiCredentialsInterceptor,
        oAuthInterceptor: OAuthInterceptor,
        builder: OkHttpClient.Builder
    ): OkHttpClient {
        return builder
            .addInterceptor(apiCredentialsInterceptor)
            .addInterceptor(oAuthInterceptor)
            .build()
    }

    @Provides
    @BasicHttpClient
    fun provideBasicHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(@DataContext context: Context): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            connectTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)

            addInterceptor(ChuckInterceptor(context))
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }
    }
}