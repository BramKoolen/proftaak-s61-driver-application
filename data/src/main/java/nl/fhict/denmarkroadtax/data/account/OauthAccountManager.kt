package nl.fhict.denmarkroadtax.data.account

import io.reactivex.Completable
import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.account.data.AccountManager
import nl.fhict.denmarkroadtax.data.account.network.AuthenticationService
import nl.fhict.denmarkroadtax.data.account.network.LoginNetworkMapper
import nl.fhict.denmarkroadtax.data.account.network.request.LoginRequest
import nl.fhict.denmarkroadtax.data.account.network.response.LoginResponse
import nl.fhict.denmarkroadtax.data.network.response.Response
import nl.fhict.denarkroadtax.domain.account.data.TokenManager
import javax.inject.Inject

class OauthAccountManager @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val tokenManager: TokenManager,
    private val loginNetworkMapper: LoginNetworkMapper
) : AccountManager {

    override fun login(username: String, password: String): Completable {
        return authenticate(username, password)
            .onErrorResumeNext { Single.error(loginNetworkMapper.mapLoginError(it)) }
            .ignoreElement()
    }

    override fun isLoginCredentialsValid(): Single<Boolean> {
        return authenticationService.isAccessTokenValid()
            .onErrorResumeNext { Single.error(loginNetworkMapper.mapLoginError(it)) }
            .map { it.data.isAccessTokenValid }
    }

    private fun authenticate(username: String, password: String): Single<Response<LoginResponse>> {
        return authenticationService.authenticateUser(
            LoginRequest(
                username,
                password
            )
        )
            .doOnSubscribe {
                tokenManager.clearToken()
            }
            .doOnSuccess {
                tokenManager.accessToken = it.data.accessToken
            }
    }
}