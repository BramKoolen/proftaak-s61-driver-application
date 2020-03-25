package nl.fhict.denmarkroadtax.data.network.interceptor

import nl.fhict.denarkroadtax.domain.account.data.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OAuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return tokenManager.accessToken?.let {
            chain.proceed(chain.request().newBuilder()
                    .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_PREFIX + it)
                    .build())
        } ?: chain.proceed(chain.request())
    }

    companion object {

        private const val AUTHORIZATION_HEADER_NAME = "Authorization"
        private const val AUTHORIZATION_HEADER_PREFIX = "Bearer "
    }
}