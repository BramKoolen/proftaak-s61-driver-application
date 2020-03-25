package nl.fhict.denarkroadtax.domain.account

import io.reactivex.Completable
import nl.fhict.denarkroadtax.domain.account.data.AccountManager
import nl.fhict.denarkroadtax.domain.account.error.InvalidPassword
import nl.fhict.denarkroadtax.domain.account.error.InvalidUsername
import javax.inject.Inject

class LoginUser @Inject constructor(private val accountManager: AccountManager) {

    operator fun invoke(username: String, password: String): Completable {
        if (!isUsernameValid(username)) return Completable.error(InvalidUsername(username))
        if (!isPasswordValid(password)) return Completable.error(InvalidPassword())

        return Completable.complete()
    }

    /* username requirements
    Be between 8 and 20 characters long
     no _ or . at the beginning
     no __ or _. or ._ or .. inside
    no _ or . at the end*/
    private fun isUsernameValid(username: String): Boolean {
        val regex = "^(?=.{8,20}\$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])\$".toRegex()
        return regex.matches(username)
    }

    /* password requirements
    min 8 chars
    Contain at least one digit.
    Contain at least one lower case character.
    Contain at least one upper case character.
    Contain at least on special character from [ @ # $ % ! . ].*/
    private fun isPasswordValid(password: String): Boolean {
        val regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$".toRegex()
        return regex.matches(password)
    }
}
