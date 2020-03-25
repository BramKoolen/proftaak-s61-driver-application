package nl.fhict.denmarkroadtax.data.network.extension

import nl.fhict.denmarkroadtax.data.network.extension.HttpExceptionExtensionConstant.MAXIMUM_SERVER_EXCEPTION_CODE
import nl.fhict.denmarkroadtax.data.network.extension.HttpExceptionExtensionConstant.MINIMUM_SERVER_EXCEPTION_CODE
import retrofit2.HttpException
import java.net.HttpURLConnection

/**
 * Checks if the HttpException is a 404 not found
 */
fun HttpException.isNotFoundException(): Boolean {
    return code() == HttpURLConnection.HTTP_NOT_FOUND
}

/**
 * Checks if the HttpException is a 400 bad request
 */
fun HttpException.isBadRequestException(): Boolean {
    return code() == HttpURLConnection.HTTP_BAD_REQUEST
}

/**
 * Checks if the HttpException is in the range of any possible server codes
 */
fun HttpException.isAnyServerException(): Boolean {
    return code() in MINIMUM_SERVER_EXCEPTION_CODE..MAXIMUM_SERVER_EXCEPTION_CODE
}

/**
 * Checks if the HttpException is unauthorized exception
 */
fun HttpException.isUnauthorizedException(): Boolean {
    return code() == HttpURLConnection.HTTP_UNAUTHORIZED
}

/**
 * Checks if the HttpException is a conflict exception
 */
fun HttpException.isConflictException(): Boolean {
    return code() == HttpURLConnection.HTTP_CONFLICT
}

private object HttpExceptionExtensionConstant {

    /** Exception 5xx codes indicate a server exception was the cause */
    const val MINIMUM_SERVER_EXCEPTION_CODE = 500
    const val MAXIMUM_SERVER_EXCEPTION_CODE = 599
}