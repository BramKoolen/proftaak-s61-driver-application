package nl.fhict.denmarkroadtax.data.network.token

import nl.fhict.denarkroadtax.domain.account.data.TokenManager
import nl.fhict.denmarkroadtax.data.network.storage.AccessTokenStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthTokenManager @Inject constructor(private val tokenStorage: AccessTokenStorage) :
    TokenManager {

    override var accessToken: String?
        get() = tokenStorage.accessToken
        set(value) {
            tokenStorage.accessToken = value
        }

    override fun clearToken() {
        tokenStorage.accessToken = null
    }
}