package nl.fhict.denmarkroadtax.splash

interface SplashContract {

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
    }

    interface Navigator {

        fun navigateToDashboard()
        fun navigateToLoginScreen()
    }
}