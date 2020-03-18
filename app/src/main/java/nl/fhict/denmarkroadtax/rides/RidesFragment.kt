package nl.fhict.denmarkroadtax.rides

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomsheet_rides.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.generic.map.MapDecodeDirectionsPointsToLatLong
import nl.fhict.denmarkroadtax.rides.bottomsheet.RidesBottomSheetAdapter
import timber.log.Timber
import javax.inject.Inject


class RidesFragment : DaggerFragment(), RidesContract.View {

    @Inject
    lateinit var presenter: RidesContract.Presenter

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val ridesBottomSheetAdapter by lazy {
        RidesBottomSheetAdapter().apply {
            onPreviousDayClicked = presenter::onPreviousDayClicked
            onNextDayClicked = presenter::onNextDayClicked
        }
    }

    private var mMapView: MapView? = null
    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_rides, null, false)
        mMapView = view.findViewById<View>(R.id.rides_map) as MapView
        mMapView?.onCreate(savedInstanceState)
        mMapView?.onResume()
        try {
            MapsInitializer.initialize(activity?.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mMapView?.getMapAsync { mMap ->
            map = mMap
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(bottomSheetRides)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Timber.i("hallo")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                Timber.i("hello")
                /*when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> if (bottomSheetBehavior.state !== BottomSheetBehavior.STATE_HALF_EXPANDED) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
                    else ->{}
                }*/
            }
        })

        bottomSheetRidesViewPager.apply {
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = ridesBottomSheetAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting()
        mMapView?.onResume()
    }

    override fun onPause() {
        presenter.stopPresenting()
        mMapView?.onPause()
        super.onPause()
    }

    override fun showRideRecapOfDayList(rideRecapOfDayViewModelList: List<RideRecapOfDayViewModel>) {
        val route = MapDecodeDirectionsPointsToLatLong(rideRecapOfDayViewModelList[0].route)
        val polyLineOptions = PolylineOptions().apply {
            color(Color.BLUE)
            width(10f)
            addAll(route)
        }
        map?.addPolyline(polyLineOptions)
        zoomRoute(route)
        ridesBottomSheetAdapter.rideRecapOfDayViewModels = rideRecapOfDayViewModelList
        ridesBottomSheetAdapter.notifyDataSetChanged()

    }

    private fun zoomRoute(lstLatLngRoute: List<LatLng?>?) {
        if (map == null || lstLatLngRoute == null || lstLatLngRoute.isEmpty()) return
        val boundsBuilder = LatLngBounds.Builder()
        for (latLngPoint in lstLatLngRoute) boundsBuilder.include(latLngPoint)
        val routePadding = 100
        val latLngBounds = boundsBuilder.build()
        map?.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding))
    }

    override fun showLoadingIndicator() {
        //TODO("not implemented")
    }

    override fun showPreviousPage() {
        val index = bottomSheetRidesViewPager.currentItem
        if (index != 0) {
            bottomSheetRidesViewPager.currentItem = (index - 1)
        }else {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun showNextPage() {
        val index = bottomSheetRidesViewPager.currentItem
        bottomSheetRidesViewPager.currentItem = (index + 1)
    }
}