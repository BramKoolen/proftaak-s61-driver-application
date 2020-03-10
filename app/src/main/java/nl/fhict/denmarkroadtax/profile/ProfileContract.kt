package nl.fhict.denmarkroadtax.profile

interface ProfileContract {

    interface View {

    }

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
    }
}