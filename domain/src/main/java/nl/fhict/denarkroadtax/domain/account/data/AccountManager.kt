package nl.fhict.denarkroadtax.domain.account.data

import io.reactivex.Completable
import io.reactivex.Single

interface AccountManager {

    fun login(username: String, password: String): Completable
    fun isLoginCredentialsValid(): Single<Boolean>
}