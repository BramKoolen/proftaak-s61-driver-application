package nl.fhict.denarkroadtax.domain.account

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.account.data.AccountManager
import nl.fhict.denarkroadtax.domain.account.data.TokenManager
import javax.inject.Inject

class IsLoginCredentialsValid @Inject constructor(
    private val accountManager: AccountManager,
    private val tokenManager: TokenManager
) {

    operator fun invoke(): Single<Boolean> {
        /*return if (tokenManager.accessToken != null || tokenManager.accessToken != "") {
            accountManager.isLoginCredentialsValid()
        } else {
            Single.just(false)
        }*/
        return Single.just(false)//TODO delete
    }
}