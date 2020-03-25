package nl.fhict.denmarkroadtax.login

interface LoginContract {

    interface View {

        fun showWrongPasswordError()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Presenter {

        fun onLoginButtonClicked(username: String, password: String)
        fun stopPresenting()
    }

    interface Navigator {

        fun navigateToMainNavigation()
    }
}