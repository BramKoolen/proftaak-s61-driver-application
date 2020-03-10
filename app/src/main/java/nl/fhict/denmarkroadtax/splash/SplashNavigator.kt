package nl.fhict.denmarkroadtax.splash

import android.app.Activity
import android.content.Context
import nl.fhict.denmarkroadtax.mainnavigation.MainNavigationActivity
import javax.inject.Inject

class SplashNavigator @Inject constructor(private val activity: Activity) : SplashContract.Navigator {

    override fun navigateToRidesScreen() {
        activity.startActivity(MainNavigationActivity.createIntent(activity))
        activity.finish()
    }

    override fun navigateToLoginScreen() {
        //TODO
    }
}