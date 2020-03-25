package nl.fhict.denmarkroadtax.data.account.network

import com.google.gson.Gson
import nl.fhict.denarkroadtax.domain.account.error.InvalidCredentialsError
import nl.fhict.denarkroadtax.domain.account.error.LoginError
import nl.fhict.denmarkroadtax.data.network.NetworkErrorMapper
import nl.fhict.denmarkroadtax.data.network.extension.isConflictException
import nl.fhict.denmarkroadtax.data.network.extension.isNotFoundException
import nl.fhict.denmarkroadtax.data.network.response.ErrorResponse
import retrofit2.HttpException
import javax.inject.Inject

class LoginNetworkMapper @Inject constructor(private val networkErrorMapper: NetworkErrorMapper) {

    /** Tries to map certain errors to specific errors, otherwise generic error.
     *  404 - Not found
     *  409 - Conflict
     *  4xx - Login Error
     */
    fun mapLoginError(throwable: Throwable): Throwable {
        return (throwable as? HttpException)?.let {
            when {
                it.isNotFoundException() || it.isConflictException() -> InvalidCredentialsError(it, it.code())
                it.code() in START_LOGIN_ERROR_CODE..END_LOGIN_ERROR_CODE -> handleLoginError(it)
                else -> it
            }
        } ?: networkErrorMapper.mapError(throwable)
    }

    private fun handleLoginError(httpException: HttpException): LoginError {
        val responseBody = Gson().fromJson(httpException.response()?.errorBody()?.charStream(), ErrorResponse::class.java)
        return LoginError(httpException, httpException.code(), responseBody.status.message)
    }

    companion object {

        private const val START_LOGIN_ERROR_CODE = 400
        private const val END_LOGIN_ERROR_CODE = 499
    }
}