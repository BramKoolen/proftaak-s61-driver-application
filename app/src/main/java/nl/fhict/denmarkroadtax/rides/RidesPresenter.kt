package nl.fhict.denmarkroadtax.rides

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.GetLocalRideRecapFromDays
import nl.fhict.denarkroadtax.domain.ride.GetRideRecapFromDay
import nl.fhict.denmarkroadtax.generic.extension.convertToDate
import nl.fhict.denmarkroadtax.generic.extension.dateString
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject

class RidesPresenter @Inject constructor(
    private val view: RidesContract.View,
    private val getRideRecapFromDay: GetRideRecapFromDay,
    private val getLocalRideRecapFromDays: GetLocalRideRecapFromDays,
    private val rideMapper: RideMapper
) :
    RidesContract.Presenter {

    private var compositeDisposable = CompositeDisposable()

    override fun startPresenting() {
        view.showRideRecapOfDayList(createDateList())
        getLocalRideRecapFromDays()
    }

    private fun getLocalRideRecapFromDays(){
        getLocalRideRecapFromDays.invoke()
            .map { it.map { rideMapper.mapToRideRecapOfDayViewModel(it) } }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onLocalRideRecapFromDaysFetched, ::onLocalRideRecapFromDaysFetchedFailed)
            .addTo(compositeDisposable)
    }

    private fun onLocalRideRecapFromDaysFetched(rideRecapOfDayViewModelList: List<RideRecapOfDayViewModel>){
        view.showRideRecapOfDayList(rideRecapOfDayViewModelList)
    }

    private fun onLocalRideRecapFromDaysFetchedFailed(throwable: Throwable){
        Timber.i(throwable)
    }

    override fun getRideRecapFromDay(rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
            getRideRecapFromDay.invoke(DateTime().convertToDate(rideRecapOfDayViewModel.date))
                .map { rideMapper.mapToRideRecapOfDayViewModel(it)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { onRideRecapFromDayFetched(it) },
                    { onRideRecapFromDayFetchedFailed(it, rideRecapOfDayViewModel) })
                .addTo(compositeDisposable)
    }

    private fun onRideRecapFromDayFetched(recapOfDayViewModel: RideRecapOfDayViewModel) {
        view.updateRideRecapOfDayList(recapOfDayViewModel)
    }

    private fun onRideRecapFromDayFetchedFailed(throwable: Throwable, rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
        view.showErrorStateNoRidesForDate(rideRecapOfDayViewModel)
        Timber.e(throwable)
    }

    override fun stopPresenting() {
        compositeDisposable.clear()
    }

    override fun onPreviousDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
        view.showPreviousPage()
    }

    override fun onNextDayClicked(rideRecapOfDayViewModel: RideRecapOfDayViewModel) {
        view.showNextPage()
    }

    private fun createDateList(): List<RideRecapOfDayViewModel> {
        val rideRecapOfDayViewModelList = mutableListOf<RideRecapOfDayViewModel>()
        for (i in 100 downTo 0) {
            val date = DateTime.now().minusDays(i)
            rideRecapOfDayViewModelList.add(
                RideRecapOfDayViewModel(
                    DateTime().dateString(date),
                    null,
                    null,
                    null,
                    null,
                    null,
                    true
                )
            )
        }
        return rideRecapOfDayViewModelList
    }
}