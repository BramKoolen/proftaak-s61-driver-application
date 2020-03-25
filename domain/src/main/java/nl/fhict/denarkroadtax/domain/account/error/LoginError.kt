package nl.fhict.denarkroadtax.domain.account.error

class LoginError(cause: Throwable? = null, val errorCode: Int, val errorResponse: String) : Exception("Login error: ${cause?.message}")