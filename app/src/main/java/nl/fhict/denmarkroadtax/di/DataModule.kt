@file:Suppress("unused")

package nl.fhict.denmarkroadtax.di

import dagger.Binds
import dagger.Module
import nl.fhict.denarkroadtax.domain.account.data.AccountManager
import nl.fhict.denarkroadtax.domain.invoice.data.InvoiceRepository
import nl.fhict.denarkroadtax.domain.ride.data.RideRepository
import nl.fhict.denmarkroadtax.data.invoice.RemoteInvoiceRepository
import nl.fhict.denmarkroadtax.data.ride.CachedRemoteRideRepository
import nl.fhict.denmarkroadtax.data.account.OauthAccountManager
import nl.fhict.denmarkroadtax.data.network.storage.AccessTokenStorage
import nl.fhict.denmarkroadtax.data.network.storage.SharedPreferenceAccessTokenStorage
import nl.fhict.denmarkroadtax.data.network.token.OAuthTokenManager
import nl.fhict.denarkroadtax.domain.account.data.TokenManager
import nl.fhict.denmarkroadtax.di.qualifier.RetrofitModule

@Module(includes = [RetrofitModule::class])
interface DataModule {

    @Binds
    fun bindTokenManager(tokenManager: OAuthTokenManager): TokenManager

    @Binds
    fun bindTokenStorage(tokenStorage: SharedPreferenceAccessTokenStorage): AccessTokenStorage

    @Binds
    fun bindAccountManager(oAuthAccountManager: OauthAccountManager): AccountManager

    @Binds
    fun bindRideRepository(cachedRemoteRideRepository: CachedRemoteRideRepository): RideRepository

    @Binds
    fun bindInvoiceRepository(remoteInvoiceRepository: RemoteInvoiceRepository): InvoiceRepository
}