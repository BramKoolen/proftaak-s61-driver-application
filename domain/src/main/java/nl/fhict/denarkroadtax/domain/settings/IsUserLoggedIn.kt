package nl.fhict.denarkroadtax.domain.settings

import io.reactivex.Single
import javax.inject.Inject

class IsUserLoggedIn @Inject constructor() {

    operator fun invoke(): Single<Boolean> {
        //TODO implement useCase
        return Single.just(true)
    }
}