package nl.fhict.denmarkroadtax.rides

interface RidesContract {

    interface View {

        fun showRideRecapOfDayList(rideRecapOfDayViewModelList: List<RideRecapOfDayViewModel>)
        fun updateRideRecapOfDayList(rideRecapOfDayViewModel: RideRecapOfDayViewModel)
        fun showPreviousPage()
        fun showNextPage()
        fun showErrorStateNoRidesForDate(rideRecapOfDayViewModel: RideRecapOfDayViewModel)
    }

    interface Presenter {

        fun startPresenting()
        fun getRideRecapFromDay(rideRecapOfDayViewModel: RideRecapOfDayViewModel)
        fun stopPresenting()
        fun onPreviousDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel)
        fun onNextDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel)
    }

    interface StringProvider {

        val rideCurrencie: String
        val rideAverageEuroKm: String
        val rideDistanceKm: String
        val rideRides: String
        val rideMin: String
    }
}