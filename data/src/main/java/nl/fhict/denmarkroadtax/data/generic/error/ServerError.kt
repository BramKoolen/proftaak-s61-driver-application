package nl.fhict.denmarkroadtax.data.generic.error

class ServerError(cause: Throwable? = null) : Exception("An error occured due to an server-side issue: ${cause?.message}", cause)