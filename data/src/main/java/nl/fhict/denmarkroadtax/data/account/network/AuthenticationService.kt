package nl.fhict.denmarkroadtax.data.account.network

import io.reactivex.Single
import nl.fhict.denmarkroadtax.data.account.network.request.LoginRequest
import nl.fhict.denmarkroadtax.data.account.network.response.IsAccessTokenValidResponse
import nl.fhict.denmarkroadtax.data.account.network.response.LoginResponse
import nl.fhict.denmarkroadtax.data.network.response.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {

    @POST("/v0/login")
    fun authenticateUser(@Body loginRequest: LoginRequest): Single<Response<LoginResponse>>

    @GET("api")
    fun isAccessTokenValid(): Single<Response<IsAccessTokenValidResponse>>
}