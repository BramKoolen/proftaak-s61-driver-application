package nl.fhict.denmarkroadtax.rides

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.bottomsheet_rides.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.rides.bottomsheet.RidesBottomSheetAdapter
import timber.log.Timber
import javax.inject.Inject

class RidesFragment : DaggerFragment(), OnMapReadyCallback, RidesContract.View {

    @Inject
    lateinit var presenter: RidesContract.Presenter

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private val ridesBottomSheetAdapter by lazy {
        RidesBottomSheetAdapter().apply {
            onPreviousDayClicked = presenter::onPreviousDayClicked
            onNextDayClicked = presenter::onNextDayClicked
        }
    }

    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mapFragment = SupportMapFragment()
        childFragmentManager.findFragmentById(R.id.rides_map) as SupportMapFragment?
        mapFragment.getMapAsync(this)
        return inflater.inflate(R.layout.fragment_rides, container, false)
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
    }

    override fun onPause() {
        presenter.stopPresenting()
        super.onPause()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
    }

    override fun showRides(rides: List<RideRecapOfDayViewModel>) {
        ridesBottomSheetAdapter.rideRecapOfDayViewModels = rides
        ridesBottomSheetAdapter.notifyDataSetChanged()

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