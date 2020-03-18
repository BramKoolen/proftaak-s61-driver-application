package nl.fhict.denmarkroadtax.data.ride.netwerk

import io.reactivex.Observable
import nl.fhict.denarkroadtax.domain.ride.model.Ride
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denarkroadtax.domain.ride.model.RideRecapOfDay
import org.joda.time.DateTime
import javax.inject.Inject

class RideNetworkManager @Inject constructor(
    private val retrofitRideService: RetrofitRideService,
    private val rideNetworkMapper: RideNetworkMapper
) {

    fun fetchRideRecapFromDay(userId: Int, dateTime: DateTime): Observable<RideRecapOfDay> {
        return Observable.just(
            RideRecapOfDay(
                DateTime.now(),
                "12,57 euro",
                "0,12 euro/km",
                "12 km",
                "4 rides",
                listOf(
                    Ride(
                        1,
                        DateTime.now(),
                        "Asten",
                        "Helmkruid 20",
                        "09:30",
                        "Helmond",
                        "Stationsplein 7",
                        "09:50",
                        "13,5 Kilometer",
                        "20 min",
                        RideAddressType.START
                    ),
                    Ride(
                        2,
                        DateTime.now(),
                        "Helmond",
                        "Stationsplein 7",
                        "09:55",
                        "Eindhoven Strijp T",
                        "Achtseweg zuid 151, 5651 GW Eindhoven",
                        "10:17",
                        "15,8 Kilometer",
                        "22 min",
                        RideAddressType.STOPOVER
                    ),
                    Ride(
                        3,
                        DateTime.now(),
                        "Eindhoven Strijp T",
                        "Achtseweg zuid 151, 5651 GW Eindhoven",
                        "15:04",
                        "Asten",
                        "Helmkruid 20",
                        "15:37",
                        "24,1 Kilometer",
                        "28 min",
                        RideAddressType.STOPOVER
                    ),
                    Ride(
                        4,
                        DateTime.now(),
                        "Asten",
                        "Helmkruid 20",
                        "15:37",
                        "",
                        "",
                        "",
                        "",
                        "",
                        RideAddressType.END
                    )
                ),
                "isvxHqfdb@VnGTNnBNC|BEtAUjAUXg@TiBp@gAh@w@p@cAvAwEbIaAfB}@~Ai@d@GMIEQ@MGKSe@{@eAcBaAcA}BqAMMoCeAoDaAqA[mC{@}DoAeHwB{EaBkBiAACAGGGI?A@kCeDeBgCkAcAi@Q[?iAPsBt@qAS{@SAj@_@`JgAnVg@pIqAxQm@vJ[tGu@vUW~PChUPbWBv_@[rYU~JmAl_@cA~XoDtbAaBre@mAd^i@xUg@`]W`f@i@vyASth@?tRVpOhBtd@pPtuDpDxx@jB~VfAxJpBvNhEnUnLpf@nMbi@|Kld@xGpX|DlPhC|LbD|RzB~QjBtVn@hOTjJNfQAnoBAxc@?|n@?zIGbHE~BMbKa@vVUfEm@pDk@bCIjCZ|B^`ALRd@lDBzL?ve@E~L_@hYIrXi@t\\KrV_@bXBpXXz]b@beABrg@N|Q?`I[pIc@pDq@dDw@dCiA`C_BxBcBtAmAn@qBn@{Cp@aPtBwMlAyGv@iGd@gJ^qEVwD^qGf@oLz@q_@bCmGl@gN|BkFz@uEj@w@DoCJc@A_DQyNcBwH}@oUuCkCEgCNaDn@qCbAqDzBgGnHuEtIYz@mGdWeEfMkApCeH~NeErGOF}D~DqGxEyBtAKHo@kBiAsD_D{JwFiLwAgDiAeEuAsFyDwO}BsGuAqDi@mBkAmIs@kLWcDc@kCOk@NONQT}@CgAo@eQ?cCPcABBBBD?HCDME]ECLk@~n@m}@tHmKhDwEx@_AvAsAv@yAfFsH|JeOPb@LJZFo@f@YdFMxBnInBbE~@fAqArDwD@SSu@x@B|@_@\\{AFY`@gADSw@{C{@}DEO[{CYkCc@aAIFOBKEe@gAgAiFeAqGyFiYmBkNUcCEeF}@qQaAsN?kELaGOwSP_L\\mOZ}HJqLCcSDeERwE~@uHlBkH~AwDrBuC|CuCtFwEvAaAn@[vAKDBFBZ@\\K`@i@NaAE}@[_A[_@w@yAg@qB{F}TuQir@eG}VwB_LaBgKuAwLeAuLq@yLcBy`@{Boc@g@oHuCs[qCsU_C}O}Fy_@oHqh@cI_n@_E}\\qC}WuBaU_Ekg@gCaS{A}IyBsK{CyLsBgIwDwQwBqMsCqUqAePu@aNg@kQOsQI}NQeGa@}Hg@mGeBeNqCwNs@uCgBmGkEgQ_B_HUcA{A}GmBmKEWqAkIm@iGQyEFaJVuMLwGPyClA}IPkCr@iV@i@R@j@F^G\\W`B{C^[E}@RS@c@",
                false
            )
        )
        /*return retrofitRideService.fetchRideRecapFromDay(userId, dateTime.toString("dd/MM/yyyy"))
            .map  { rideNetworkMapper.mapToRideRecapOfDay(it)  }*/
    }
}