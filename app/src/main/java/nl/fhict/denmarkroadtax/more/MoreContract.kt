package nl.fhict.denmarkroadtax.more

interface MoreContract {

    interface View {

    }

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
    }
}