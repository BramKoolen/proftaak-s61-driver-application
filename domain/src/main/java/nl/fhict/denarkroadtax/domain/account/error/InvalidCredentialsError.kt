package nl.fhict.denarkroadtax.domain.account.error

class InvalidCredentialsError(cause: Throwable? = null, val errorCode: Int) : Exception("Invalid login credentials! ${cause?.message}")