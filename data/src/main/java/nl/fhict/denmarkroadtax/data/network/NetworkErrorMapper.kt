package nl.fhict.denmarkroadtax.data.network

import nl.fhict.denmarkroadtax.data.generic.error.ConnectivityError
import nl.fhict.denmarkroadtax.data.generic.error.ServerError
import nl.fhict.denmarkroadtax.data.network.extension.isAnyServerException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkErrorMapper @Inject constructor() {

    /**
     * Tries to map the given network error to a describing exception if this fails, the original
     * throwable is returned
     *
     * @param[throwable] Throwable to map
     */
    fun mapError(throwable: Throwable): Throwable {
        return if (throwable is SocketTimeoutException || throwable is UnknownHostException) {
            ConnectivityError(throwable)
        } else {
            (throwable as? HttpException)?.takeIf { it.isAnyServerException() }
                    ?.let { ServerError(it) } ?: throwable
        }
    }
}