package nl.fhict.denmarkroadtax.rides

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.ride.GetRideRecapFromDay
import nl.fhict.denmarkroadtax.generic.extension.convertToDate
import nl.fhict.denmarkroadtax.generic.extension.dateString
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject

class RidesPresenter @Inject constructor(private val view: RidesContract.View,
                                         private val getRideRecapFromDay: GetRideRecapFromDay,
                                         private val rideMapper: RideMapper) :
    RidesContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
        view.showRideRecapOfDayList(createDateList())
    }

    override fun getRideRecapFromDay(rideRecapOfDayViewModel: RideRecapOfDayViewModel){
        disposable?.dispose()
        disposable = getRideRecapFromDay.invoke(DateTime().convertToDate(rideRecapOfDayViewModel.date))
            .map { it.map { rideMapper.mapToRideRecapOfDayViewModel(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onRideRecapFromDayFetched, ::onRideRecapFromDayFetchedFailed)
    }

    private fun onRideRecapFromDayFetched(recapOfDayViewModel: List<RideRecapOfDayViewModel>){
        view.updateRideRecapOfDayList(recapOfDayViewModel)
    }

    private fun onRideRecapFromDayFetchedFailed(throwable: Throwable){
        Timber.e(throwable)
    }

    override fun stopPresenting() {
        disposable?.dispose()
    }

    override fun onPreviousDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
        view.showPreviousPage()
    }

    override fun onNextDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
       view.showNextPage()
    }

    private fun createDateList(): List<RideRecapOfDayViewModel>{
        val rideRecapOfDayViewModelList = mutableListOf<RideRecapOfDayViewModel>()
        for ( i in 0..100){
            val date = DateTime.now().minusDays(i)
            rideRecapOfDayViewModelList.add(RideRecapOfDayViewModel(DateTime().dateString(date),null,null,null,null,null))
        }
        return rideRecapOfDayViewModelList
    }
}