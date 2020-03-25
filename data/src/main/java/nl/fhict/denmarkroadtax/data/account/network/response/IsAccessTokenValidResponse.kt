package nl.fhict.denmarkroadtax.data.account.network.response

import com.google.gson.annotations.SerializedName

data class IsAccessTokenValidResponse(
    
    @SerializedName("isAccessTokenValid")
    val isAccessTokenValid: Boolean

)