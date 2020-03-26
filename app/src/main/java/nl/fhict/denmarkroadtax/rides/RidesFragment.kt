package nl.fhict.denmarkroadtax.rides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
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
            hideClicked = {bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED}
        }
    }

    private var mMapView: MapView? = null
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_MAP_LOCATION, 12.0f))
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

        bottomSheetRidesViewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                map?.clear()
                val index = ridesBottomSheetAdapter.rideRecapOfDayViewModels.size - (position + 1)
                presenter.getRideRecapFromDay(ridesBottomSheetAdapter.rideRecapOfDayViewModels[index])
            }
        })
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
        map?.moveCamera(CameraUpdateFactory.newLatLng(DEFAULT_MAP_LOCATION))
        ridesBottomSheetAdapter.rideRecapOfDayViewModels =
            rideRecapOfDayViewModelList.toMutableList()
        ridesBottomSheetAdapter.notifyDataSetChanged()
        bottomSheetRidesViewPager.currentItem = rideRecapOfDayViewModelList.size
    }

    override fun updateRideRecapOfDayList(rideRecapOfDayViewModelList: List<RideRecapOfDayViewModel>) {
        rideRecapOfDayViewModelList.forEach { newItem ->
            ridesBottomSheetAdapter.rideRecapOfDayViewModels.forEachIndexed { index, oldItem ->
                if (newItem.date == oldItem.date) {
                    val itemIndex =
                        ridesBottomSheetAdapter.rideRecapOfDayViewModels.size - (index + 1)
                    ridesBottomSheetAdapter.rideRecapOfDayViewModels[itemIndex] = newItem
                    return@forEachIndexed
                }
            }
        }
        ridesBottomSheetAdapter.notifyDataSetChanged()
        val index = ridesBottomSheetAdapter.rideRecapOfDayViewModels[bottomSheetRidesViewPager.currentItem]
        index.rides?.let { createRoute(it) }
    }

    private fun createRoute(rides: List<RideViewModel>) {
        var lastColor = 1
        var color: Int
        val allEncodedRoutes = mutableListOf<LatLng>()
        rides.forEach {
            if (lastColor == 1) {
                color = context?.getColor(R.color.redLight) ?: 0
                lastColor = 0
            } else {
                color = context?.getColor(R.color.red) ?: 1
                lastColor = 1
            }
            val encodedRoute = MapDecodeDirectionsPointsToLatLong(it.route)
            allEncodedRoutes.addAll(encodedRoute)
            val polyLineOptions = PolylineOptions().apply {
                color(color)
                width(10f)
                addAll(encodedRoute)
            }
            map?.addPolyline(polyLineOptions)
        }

        val boundsBuilder = LatLngBounds.Builder()
        for (latLngPoint in allEncodedRoutes) boundsBuilder.include(latLngPoint)
        val routePadding = 100
        val latLngBounds = boundsBuilder.build()
        map?.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, routePadding))


    }

    override fun showPreviousPage() {
        val index = bottomSheetRidesViewPager.currentItem
        bottomSheetRidesViewPager.currentItem = (index - 1)
    }

    override fun showNextPage() {
        val index = bottomSheetRidesViewPager.currentItem
        bottomSheetRidesViewPager.currentItem = (index + 1)
    }

    companion object {

        private val DEFAULT_MAP_LOCATION = LatLng(55.675758361816406, 12.569023132324219)
    }
}