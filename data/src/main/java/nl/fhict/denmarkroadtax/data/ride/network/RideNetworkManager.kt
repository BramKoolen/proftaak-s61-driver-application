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

    fun fetchRideRecapFromDay(selectedDate: DateTime): Observable<RideRecapOfDay> {
        return Observable.just(
            RideRecapOfDay(
                DateTime.now(),
                14.20,
                0.12,
                22866,
                3,
                listOf(
                    Ride(
                        1,
                        DateTime.now(),
                        "Frederiksberg, København",
                        "Kastanievej 6A",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("09:30"),
                        "Viberup, København",
                        "Gyldenvang Alle 17",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("09:50"),
                        11346,
                        20,
                        RideAddressType.START,
                        "uryrI{`qkAl@oEx@_GpBlAvBrATPxBfBEXFFRNbJ`EZNHeBlBk@v@Yx@k@b@QbBwAZ]JO^WjAk@\\KNBl@XrCpAv@Z|DtBjChAfDxAxCvAr@b@p@ZxDhBzDfBz@`@h@ZFHJ@`@TNLxAf@\\Px@\\jBv@VHnEvBhJnErB~@`GpCz@Zz@R^DhAFjACj@Cr@OnAa@nAi@lBy@jAe@`@I^IzDiBpAm@jFyBhD{ArD_BjBy@p@QhBu@vBw@b@M|@g@zFmGnA{Aj@{@bBwCp@wAx@}BbCsHxAiFh@}BhAgGXqBJw@HMLi@Jm@l@}CRq@n@sAb@m@d@e@^W^Q^Mf@Gz@@n@NXJp@b@n@p@f@v@pAnCh@z@NZzAhD|DvIFb@|@zBhArCl@lBVjAXjB`AtG\\vA^lA`@fAf@~@h@z@l@r@l@j@p@b@v@^fATdAFZ?f@Gr@Mr@Wv@c@l@e@n@s@h@u@h@aAl@uAt@iC^{BPyAL{AHmB@mCE{Ae@yJIyC?eCRkM?k@GkAnAmp@n@a\\nBeaAJ}J@uICaIKeIQyHO}Ee@iKiBkYk@oJB{@KeF]sVI_Mj@W~@_@z@S`A]l@M~FaAxBEjBKbE[tAGpACHS^IPAHC@CB}ADiQDqFrLI"
                    ),
                    Ride(
                        2,
                        DateTime.now(),
                        "Viberup, København",
                        "Gyldenvang Alle 17",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("09:50"),
                        "København",
                        "Adriansvej 20",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("10:09"),
                        3863,
                        9,
                        RideAddressType.STOPOVER,
                        "innrIqv}kAsLH?MBoJBaLBeMFyQNyb@?wEBeBCWiDvAsBz@iFlBmAj@sBt@}Bz@iAf@_EzAuAf@i@TaBh@e@LKFm@VeBr@qB|@aAXo@ZiBv@[LcI|CuClA{FxBmFvBoGdC_G|B_FnBKNiFpBgAb@w@yGiAkJu@cG"
                    ),
                    Ride(
                        3,
                        DateTime.now(),
                        "København",
                        "Adriansvej 20",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("10:18"),
                        "Frederiksberg, København",
                        "Kastanievej 6A",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("15:26"),
                        7657,
                        22,
                        RideAddressType.STOPOVER,
                        ""
                    ),
                    Ride(
                        4,
                        DateTime.now(),
                        "Frederiksberg, København",
                        "Kastanievej 6A",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("15:48"),
                        "",
                        "",
                        DateTimeFormat.forPattern("HH:mm").parseDateTime("15:48"),
                        0,
                        0,
                        RideAddressType.END,
                        ""
                    )
                ),
                false
            )
        )
        /*return retrofitRideService.fetchRideRecapFromDay(userId, dateTime.toString("dd/MM/yyyy"))
            .map  { rideNetworkMapper.mapToRideRecapOfDay(it)  }*/
    }
}