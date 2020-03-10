package nl.fhict.denmarkroadtax.rides

interface RidesContract {

    interface View {

    }

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
    }
}