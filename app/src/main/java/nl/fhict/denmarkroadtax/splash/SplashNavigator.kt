package nl.fhict.denmarkroadtax.splash

import android.app.Activity
import android.content.Context
import javax.inject.Inject

class SplashNavigator @Inject constructor(private val activity: Activity) : SplashContract.Navigator {

    override fun navigateToDashboard() {
        //TODO
    }

    override fun navigateToLoginScreen() {
        //TODO
    }
}