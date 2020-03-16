package nl.fhict.denmarkroadtax.rides

interface RidesContract {

    interface View {

        fun showRides(rides: List<RideRecapOfDayViewModel>)
        fun showPreviousPage()
        fun showNextPage()
    }

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
        fun onPreviousDayClicked(int: Int)
        fun onNextDayClicked(int: Int)
    }
}