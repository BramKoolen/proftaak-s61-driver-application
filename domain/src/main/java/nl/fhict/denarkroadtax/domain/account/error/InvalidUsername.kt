package nl.fhict.denarkroadtax.domain.account.error

class InvalidUsername(username: String) : IllegalArgumentException("username: $username is invalid")