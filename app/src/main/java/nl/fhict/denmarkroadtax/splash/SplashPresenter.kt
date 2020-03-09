package nl.fhict.denmarkroadtax.splash

import android.os.CountDownTimer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.settings.IsUserLoggedIn
import timber.log.Timber
import javax.inject.Inject

class SplashPresenter @Inject constructor(
    private val navigator: SplashContract.Navigator,
    private val isUserLoggedIn: IsUserLoggedIn
) : SplashContract.Presenter {

    private var compositeDisposable = CompositeDisposable()
    private var timer: CountDownTimer? = null

    override fun startPresenting() {
        countDownTimer()
        (timer as CountDownTimer).start()
    }

    override fun stopPresenting() {
        compositeDisposable.clear()
    }

    private fun countDownTimer() {
        timer = object : CountDownTimer(800, 800) {
            override fun onTick(millisUntilFinished: Long) {
                // no opt
            }

            override fun onFinish() {
                isUserLoggedIn()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(::isUserLoggedInSuccess, ::isUserLoggedInFailed)
                    .addTo(compositeDisposable)
            }
        }
    }

    private fun isUserLoggedInSuccess(isUserLoggedIn: Boolean) {
        if (isUserLoggedIn) {
            navigator.navigateToDashboard()
        } else {
            navigator.navigateToDashboard()
        }
    }

    private fun isUserLoggedInFailed(throwable: Throwable) {
        Timber.e(throwable)
    }
}