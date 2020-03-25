package nl.fhict.denmarkroadtax.data.generic.error

class ConnectivityError(cause: Throwable? = null) : Exception("An error occured due to connectivity issues: ${cause?.message}", cause)