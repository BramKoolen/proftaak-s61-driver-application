package nl.fhict.denmarkroadtax.rides

import nl.fhict.denarkroadtax.domain.ride.RideAddressType
import javax.inject.Inject

class RidesPresenter @Inject constructor(private val view: RidesContract.View) :
    RidesContract.Presenter {

    override fun startPresenting() {
        view.showRides(
            listOf(
                RideRecapOfDayViewModel(
                    "12-03-20",
                    "12,57 euro",
                    "0,12 euro/km",
                    "16 km",
                    "4 rides",
                    listOf(
                        RideViewModel(
                            1,
                            "Thuis",
                            "5721RD ASten",
                            "08:12",
                            "Helmond",
                            "",
                            "08:35",
                            "11 km",
                            "23 min",
                            RideAddressType.START
                        ),
                        RideViewModel(
                            2,
                            "Helmond",
                            "",
                            "08:45",
                            "Strijp TQ",
                            "5651 GW Eindhoven",
                            "09:12",
                            "15 km",
                            "27 min",
                            RideAddressType.STOPOVER
                        ),
                        RideViewModel(
                            3,
                            "Strijp TQ",
                            "5651 GW Eindhoven",
                            "16:19",
                            "thuis",
                            "5721 RD Asten",
                            "27:22",
                            "25 km",
                            "1 u 3 min",
                            RideAddressType.STOPOVER
                        ),
                        RideViewModel(
                            4,
                            "Thuis",
                            "571 RD",
                            "17:22",
                            "",
                            "",
                            "",
                            "",
                            "",
                            RideAddressType.END
                        )
                    )
                ),
                RideRecapOfDayViewModel(
                    "11-03-20",
                    "15,17 euro",
                    "0,11 euro/km",
                    "13 km",
                    "3 rides",
                    listOf(
                        RideViewModel(
                            1,
                            "Thuis",
                            "5721RD ASten",
                            "08:30",
                            "Strijp TQ",
                            "5651 GW Eindhoven",
                            "09:14",
                            "28 km",
                            "37 min",
                            RideAddressType.START
                        )
                    )
                ),
                RideRecapOfDayViewModel(
                    "10-03-20",
                    "34,88 euro",
                    "0,14 euro/km",
                    "57 km",
                    "2 rides",
                    listOf(
                        RideViewModel(
                            1,
                            "Thuis",
                            "5721RD ASten",
                            "08:30",
                            "Strijp TQ",
                            "5651 GW Eindhoven",
                            "09:14",
                            "28 km",
                            "37 min",
                            RideAddressType.START
                        )
                    )
                )
            )
        )
    }

    override fun stopPresenting() {
        //TODO
    }

    override fun onPreviousDayClicked(int: Int) {
        view.showPreviousPage()
    }

    override fun onNextDayClicked(int: Int) {
       view.showNextPage()
    }
}