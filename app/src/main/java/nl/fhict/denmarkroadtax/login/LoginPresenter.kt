package nl.fhict.denmarkroadtax.login

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.account.LoginUser
import timber.log.Timber
import javax.inject.Inject

class LoginPresenter @Inject constructor(private val view: LoginContract.View,
                                         private val navigator: LoginContract.Navigator,
                                         private val loginUser: LoginUser): LoginContract.Presenter {

    private var disposable: Disposable? = null

    override fun onLoginButtonClicked(username: String, password: String) {
        disposable?.dispose()
        disposable = loginUser(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoadingIndicator() }
            .subscribe(
                { loginUserSuccess() },
                { loginUserFailed(it) }
            )
    }

    private fun loginUserSuccess(){
        navigator.navigateToMainNavigation()
    }

    private fun loginUserFailed(throwable: Throwable){
        Timber.i(throwable)
        view.hideLoadingIndicator()
        view.showWrongPasswordError()
    }

    override fun stopPresenting() {
        disposable?.dispose()
    }
}