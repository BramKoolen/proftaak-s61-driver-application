package nl.fhict.denmarkroadtax.data.ride.network

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import javax.inject.Inject

class RideNetworkManager @Inject constructor(
    private val rideService: RideService,
    private val rideNetworkMapper: RideNetworkMapper
) {

    fun fetchRideRecapFromDay(selectedDate: DateTime): Observable<List<RideRecapOfDay>> {
        return Observable.just(
            listOf(
                RideRecapOfDay(
                    DateTime.now(),
                    0.0,
                    0.12,
                    1200,
                    4,
                    listOf(
                        Ride(
                            1,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:30"),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:50"),
                            13500,
                            20,
                            RideAddressType.START,
                            ""
                        ),
                        Ride(
                            2,
                            DateTime.now(),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:55"),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("10:17"),
                            15800,
                            22,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            3,
                            DateTime.now(),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:04"),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            24100,
                            28,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            4,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            "",
                            "",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            0,
                            0,
                            RideAddressType.END,
                            ""
                        )
                    ),
                    false
                ),
                RideRecapOfDay(
                    DateTime.now().minusDays(1),
                    1.1,
                    0.12,
                    1200,
                    4,
                    listOf(
                        Ride(
                            1,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:30"),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:50"),
                            13500,
                            20,
                            RideAddressType.START,
                            ""
                        ),
                        Ride(
                            2,
                            DateTime.now(),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:55"),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("10:17"),
                            15800,
                            22,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            3,
                            DateTime.now(),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:04"),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            24100,
                            28,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            4,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            "",
                            "",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            0,
                            0,
                            RideAddressType.END,
                            ""
                        )
                    ),
                    false
                ),
                RideRecapOfDay(
                    DateTime.now().minusDays(2),
                    2.2,
                    0.12,
                    1200,
                    4,
                    listOf(
                        Ride(
                            1,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:30"),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:50"),
                            13500,
                            20,
                            RideAddressType.START,
                            ""
                        ),
                        Ride(
                            2,
                            DateTime.now(),
                            "Helmond",
                            "Stationsplein 7",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:55"),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("10:17"),
                            15800,
                            22,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            3,
                            DateTime.now(),
                            "Eindhoven Strijp T",
                            "Achtseweg zuid 151, 5651 GW Eindhoven",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:04"),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            24100,
                            28,
                            RideAddressType.STOPOVER,
                            ""
                        ),
                        Ride(
                            4,
                            DateTime.now(),
                            "Asten",
                            "Helmkruid 20",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            "",
                            "",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:37"),
                            0,
                            0,
                            RideAddressType.END,
                            ""
                        )
                    ),
                    false
                )
            )
        )
        /*return retrofitRideService.fetchRideRecapFromDay(userId, dateTime.toString("dd/MM/yyyy"))
            .map  { rideNetworkMapper.mapToRideRecapOfDay(it)  }*/
    }
}