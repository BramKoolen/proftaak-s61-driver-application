package nl.fhict.denmarkroadtax.rides

import org.joda.time.DateTime

interface RidesContract {

    interface View {

        fun showRideRecapOfDayList(rideRecapOfDayViewModelList: List<RideRecapOfDayViewModel>)
        fun showLoadingIndicator()
        fun showPreviousPage()
        fun showNextPage()
    }

    interface Presenter {

        fun startPresenting()
        fun getRideRecapFromDay(userId: Int, date: DateTime)
        fun stopPresenting()
        fun onPreviousDayClicked(int: Int)
        fun onNextDayClicked(int: Int)
    }
}