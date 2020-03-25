package nl.fhict.denarkroadtax.domain.account.data

interface TokenManager {

    var accessToken : String?

    fun clearToken()
}