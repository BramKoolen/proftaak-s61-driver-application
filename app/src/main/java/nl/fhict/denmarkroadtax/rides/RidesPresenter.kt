package nl.fhict.denmarkroadtax.rides

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.ride.GetRideRecapFromDay
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import org.joda.time.DateTime
import timber.log.Timber
import javax.inject.Inject

class RidesPresenter @Inject constructor(private val view: RidesContract.View,
                                         private val getRideRecapFromDay: GetRideRecapFromDay,
                                         private val rideMapper: RideMapper) :
    RidesContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
        getRideRecapFromDay(1,DateTime.now())
    }

    override fun getRideRecapFromDay(userId: Int, date: DateTime){
        disposable?.dispose()
        disposable = getRideRecapFromDay.invoke(userId, date)
            .map { rideMapper.mapToRideRecapOfDayViewModel(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoadingIndicator() }
            .subscribe(::onRideRecapFromDayFetched, ::onRideRecapFromDayFetchedFailed)
    }

    private fun onRideRecapFromDayFetched(recapOfDayViewModel: RideRecapOfDayViewModel){
        view.showRideRecapOfDayList(listOf((recapOfDayViewModel)))
    }

    private fun onRideRecapFromDayFetchedFailed(throwable: Throwable){
        Timber.e(throwable)
    }

    override fun stopPresenting() {
        disposable?.dispose()
    }

    override fun onPreviousDayClicked(int: Int) {
        view.showPreviousPage()
    }

    override fun onNextDayClicked(int: Int) {
       view.showNextPage()
    }
}