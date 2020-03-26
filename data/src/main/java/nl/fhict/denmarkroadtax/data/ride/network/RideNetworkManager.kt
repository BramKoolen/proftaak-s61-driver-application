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
                            "uryrI{`qkAl@oEx@_GpBlAvBrATPxBfBEXFFRNbJ`EZNHeBlBk@v@Yx@k@b@QbBwAZ]JO^WjAk@\\KNBl@XrCpAv@Z|DtBjChAfDxAxCvAr@b@p@ZxDhBzDfBz@`@h@ZFHJ@`@TNLxAf@\\Px@\\jBv@VHnEvBhJnErB~@`GpCz@Zz@R^DhAFjACj@Cr@OnAa@nAi@lBy@jAe@`@I^IzDiBpAm@jFyBhD{ArD_BjBy@p@QhBu@vBw@b@M|@g@zFmGnA{Aj@{@bBwCp@wAx@}BbCsHxAiFh@}BhAgGXqBJw@HMLi@Jm@l@}CRq@n@sAb@m@d@e@^W^Q^Mf@Gz@@n@NXJp@b@n@p@f@v@pAnCh@z@NZzAhD|DvIFb@|@zBhArCl@lBVjAXjB`AtG\\vA^lA`@fAf@~@h@z@l@r@l@j@p@b@v@^fATdAFZ?f@Gr@Mr@Wv@c@l@e@n@s@h@u@h@aAl@uAt@iC^{BPyAL{AHmB@mCE{Ae@yJIyC?eCRkM?k@GkAnAmp@n@a\\nBeaAJ}J@uICaIKeIQyHO}Ee@iKiBkYk@oJB{@KeF]sVI_Mj@W~@_@z@S`A]l@M~FaAxBEjBKbE[tAGpACHS^IPAHC@CB}ADiQDqFrLI"                        ),
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
                ),
                RideRecapOfDay(
                    DateTime.now().minusDays(1),
                    7.45,
                    0.12,
                    10499,
                    3,
                    listOf(
                        Ride(
                            1,
                            DateTime.now(),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:36"),
                            "København",
                            "Peder Lykkes Vej 23",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:53"),
                            6335,
                            17,
                            RideAddressType.START,
                            "uryrI{`qkAl@oEx@_GkAu@qD{Bo@_@{BqAa@[s@a@iAo@kEoCg@UaBcAsAw@r@{Ez@qG`@}BRoARgB@aAE}AQgFMkE@g@CeBAw@Nm@f@mBNi@t@sCjAaE|BaId@cBXm@z@qAlAyAzC{DnIiJ~CkDl@w@j@oApFcNpB{Et@{AZu@vAoDRm@Ha@b@qAnEaLPa@NaA~@}B`FaM|@{Bd@iAFSJGJKt@oBj@cBj@cCToBhCmXHsAFcBJgGNgHHw@jCuPp@qDBc@Fs@\\yBf@cDrB{MNq@RYXc@BKZ]|@{@`A}@f@_@b@e@\\WdAaAdAcAfAwA~DkFlDsEr@eAvBsCxJkM|AmBt@{@vGyDpCcB~@m@PChCwAfDsAd@QCjAm@fQ"
                        ),
                        Ride(
                            2,
                            DateTime.now(),
                            "København",
                            "Peder Lykkes Vej 23",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("09:53"),
                            "København",
                            "Artillerivej 146",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("10:43"),
                            4092,
                            10,
                            RideAddressType.STOPOVER,
                            "_ytrIun}kAqA|_@GE[dC]fCyAjLYrCOhBSlFGvI?`DHbHAbDApDEnCCjB@z@@rA?h@EFEHCX?BOF[R]XIRa@^cAtA[^a@l@MLAAGCOBMTCZ@L?DMZ[l@ITCJwAfCoBnDSZq@~@ONWTYPu@`@EBACAEEIIEO?KJGXAL_ARg@HaAGa@IaJmCSKiA]_Aa@m@MwBg@eAUc@O]`C]jBL\\\\r@z@~Aj@dAbAbClAbDJd@vAdI`D`QdCtLf@tBl@~@b@l@DPFNr@dAdAxALDnNfS\\`@b@Vd@JT@bCIjAET?KtBE`AEXGV^RvAbA"
                        ),
                        Ride(
                            3,
                            DateTime.now(),
                            "København",
                            "Artillerivej 146",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("10:53"),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("12:50"),
                            72,
                            1,
                            RideAddressType.STOPOVER,
                            "{aurIooukAwAcA_@SOFQNKNSn@sAvHs@m@uAaAa@]SYyAgAcBkAOCoCmBwJ_HyAiAq@g@aSmY_QyVyEaHc@g@YS}@Ya@Oa@[Yo@[i@BI`@}@JMLSNc@r@wBHe@JsAFSNOF?BHNHBDPj@iAbD[bAOn@uAjDaB~DmExKWTgAfCyAzDkAvCe@`AORq@vAsA`DUz@s@dC}DxJwAnD{@zAS`@_ArA}@pA_@n@]`@{BdCqCzCc@^}AtAsBhCWRY^[`@u@tA]p@m@|AsAtEaBtFkAtEu@pCShC@F@JDv@FnADHDvARpGHjBBnAC|@i@lDc@jCaAfHa@hCGf@z@j@`Ah@dAl@j@XlEnC`Aj@r@b@ZVtC~AlG|DgBnM"
                        ),
                        Ride(
                            4,
                            DateTime.now(),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("12:51"),
                            "",
                            "",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("12:51"),
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
                    27.2,
                    0.16,
                    67545,
                    2,
                    listOf(
                        Ride(
                            1,
                            DateTime.now(),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("07:22"),
                            "Roskilde",
                            "Bøgevej 42",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("08:01"),
                            39119,
                            39,
                            RideAddressType.START,
                            "uryrI{`qkAl@oEx@_GpBlAvBrATPxBfBEXFFRNbJ`EZNHeBlBk@v@Yx@k@b@QbBwAf@m@^WjAk@\\K|@\\jElB|DtBjChAfDxAxCvAr@b@p@ZxDhBzDfBz@`@h@ZFHJ@`@TNLxAf@\\Px@\\jBv@fF`ChJnErB~@`GpCz@ZzAXhAFjACj@Cr@OnAa@nAi@lBy@^lBj@nDd@xCl@nDxArHpI|]PjAT`BFxADhDKd[CrKMhMQvOQ|TSzMUtIG|EKzCoAnYcAzNyAlNk@~Gc@~GSpDGpCSrKW~H@lGDtKNhOd@vIrBzb@b@pH^bG^zD\\`DhA|JrBfRr@~FxExc@bAnJt@pJ`C`[ZhFvA`SbB`Tj@fHb@xDpA|L~ApM~DfYvE~[dBjLLx@Lz@pBrOt@|H`@lFZnE\\pHTxJJxJ@lIIrIkAhj@EzA}Bl`Ao@n^UbUSjV?hTBbRFbH^xZXfNFrCtAhc@tBdm@R`KBxFGhGMzDg@vHw@|GiAxGs@zC}AlF_CdGmBnDuCjEkBzBg@|@yEjFsGfHmCzCaFpFgLlM_C|CaEdGqAzBoJjQuC|EYx@k@z@i@~@U\\wFtIiD|EkA~AiJ`MsCvE{@jB_CbGgB|Fi@tB_@nBw@rEu@rFWjC]hE]lIMjJE|F@bQHxKd@b]p@z_@P|LD`K?lNGvOQ~K_@xOcA|Yc@~NWhLOjP@fMNfMPzIb@vLl@tLpA~PpAfMlA`KfDlTdDxPvD~O|BzI~Pbo@|BdJr@xCvA~Hz@pFZhCz@fJh@tITtGLtJCjIGjE_@pJY`F_@rEeBxQy@bJQlCy@pQOvJ?bJP`K\\pIPvCn@xHhAjK`ClSjE|_@zCnYj@jGx@nLrDbr@vBha@|@|Jn@jF~@dGlAtGbAnElArEf@`BdEdNvCtJ|Nxe@vFfQjClHpCvGrApCvCtFx^hn@va@tr@`JnOpAjC|AxD`BfF~@pDx@|Dr@pEv@rGx@vKx@dN|G`iA`Cda@PxEH`HCrISxGUdEo@rJuBvY{AtSIhAW`A]tDG~@e@|EMv@Oh@i@v@YPQDg@Ce@Ua@m@YeAKyABiAHm@d@uApBiF_HoLuD}GyBqDQMgAmBuBsDmCuEeAuA_@_@oHkGmBgBe@{@Uy@G[Sp@QhAc@~DU?mA`@wDjAcCv@sGfC_GfBGOKIKAIDGHG\\AHsEr@wDt@qHvAkGfAClK?tBbCa@"
                        ),
                        Ride(
                            2,
                            DateTime.now(),
                            "Roskilde",
                            "Bøgevej 42",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("08:01"),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
                            DateTimeFormat.forPattern("HH:mm").parseDateTime("15:58"),
                            27826,
                            46,
                            RideAddressType.STOPOVER,
                            "syrrIklshAcC`@?uBBmK|P_DvDu@tEi@FZPNNCRs@AOhE}AlKyDrHcCd@CTHLiEPiARq@FZTx@d@z@vHvGdCzBrEjHhEzHnOpX`GxJvDvGb@nAFj@Kz@]v@y@n@w@?o@e@e@mAMqAd@}J@wBt@oKlAcQnByXlAuSLkGAkM_AqS{LsrBw@sJiCwPqCkKw@{BkEgJc]yk@mUy`@aVma@uGcMoD_JsFmP_Sso@_IqWuCaL}B}LgAiIwAuPuEe}@uA}UkByUuPk|Aq@qKc@mP?{JN{Jl@{MdBuShBuQp@aKh@}SAgKQsJs@kMoAsM{@iG}CwO}Oyl@wEiQmFmT{BuKoB{KoCwR}Ci[_BoXk@qRUwS@oQr@y^pAg`@v@w_@JcTWq_@k@_c@Ouh@LaPJcDVqIzAeSfCsOvAwFfBqFdE_JzKcTzBkEp@_BfCuE`DcGlCmFnOwYvH{KfGaHdGuFdEmDlJ{HpCyBlBiAnJuFtFuBzCy@vEg@`DE|DVtEbAbDnAtDxBjEfDtFbEjGfDzCnAvHbBzOnCnGhAfCz@pDbAdIxCh@HB_CDyKHyQh@}JtAgL|CmN~GcS~GgRxDsLpDuOh@uCpA~A~CfE^PxAtBrHjKSt@z@fAAd@i@`BWOW[VZVNh@aB@e@{@gARu@kFgHaDyEQs@iA}AuDoEMQ}AvIqAtFaBvF{AnE}EfLWp@q@~AK]{@}CuAyF_BsGk@iCe@iAcTshA{EqZwEe`@mAqLmBkVuAeUeAkUqAwi@mCorAwDmkBYcZVmLt@iKjB{M|B}J~BcHdCoFfF}HpEyEtKaJlJsHrIsHzEyFpB_DnA_Cp@{AdDgJ|CyM`AwGlAcN^qRWgNuCgg@aAmUOaR@iHr@y[rB_s@rEw|AfBqq@?wT]qMwAyP_ByJmCuKiCeHwCcG{DuF{B_CuFoEoH_FeGoEwBwBuF{FaCaD}BaF}BgHoBmE{AgBoCcCqGuFwCiDaEyGeEwIsIqR_LeWeFeLoB_EmAgBqAcAoAc@y@GiAFoA`@{AnAeAbB_@hAQjAYdDc@pDwAzHuBtHsBlGgC~FaC~DsBhCeGbGaGbCiMrFeCx@{Ap@ERg@Xq@Xw@`@}C`Ae@HyAh@aA\\}Az@eAd@s@Vg@LoCVqAEwAWqXeMgJoEaAYeBWu@Iq@e@wIcEeHgD}QuIaEkBWAaBt@k@f@oCzBmAv@eDdAIdB[O_EeBwDkBAa@_@[oB}AwBsAqBmAy@~Fm@nE"
                        ),
                        Ride(
                            3,
                            DateTime.now(),
                            "Frederiksberg, København",
                            "Kastanievej 6A",
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