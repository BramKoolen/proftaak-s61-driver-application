package nl.fhict.denmarkroadtax.login

import android.app.Activity
import nl.fhict.denmarkroadtax.mainnavigation.MainNavigationActivity
import javax.inject.Inject

class LoginNavigator @Inject constructor(private val activity: Activity): LoginContract.Navigator {

    override fun navigateToMainNavigation() {
        activity.startActivity(MainNavigationActivity.createIntent(activity))
    }
}