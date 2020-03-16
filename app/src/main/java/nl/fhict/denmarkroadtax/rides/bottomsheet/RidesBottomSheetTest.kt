package nl.fhict.denmarkroadtax.rides.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.view_pager_item_bottomsheet_rides.view.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.rides.RideRecapOfDayViewModel

class RidesBottomSheetTest: PagerAdapter() {

    var rideRecapOfDayViewModels: List<RideRecapOfDayViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun getCount(): Int {
        return rideRecapOfDayViewModels.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item_bottomsheet_rides, parent, false)
        view.run {
            bottomSheetRidesPeekCostsTodayLabel.text = rideRecapOfDayViewModels[position].costs
            bottomSheetRidesPeekCostPerKmLabel.text = rideRecapOfDayViewModels[position].average
            bottomSheetRidesPeekDrivenTodayLabel.text = rideRecapOfDayViewModels[position].drivenKilometers
            bottomSheetRidesPeekRidesTodayLabel.text = rideRecapOfDayViewModels[position].drivenRides

            val ridesBottomSheetRideDetailsAdapter = RidesBottomSheetRideDetailsAdapter()
            bottomSheetRidesContentRecyclerView.adapter = ridesBottomSheetRideDetailsAdapter
            ridesBottomSheetRideDetailsAdapter.rideViewModels = rideRecapOfDayViewModels[position].rides

            /*bottomSheetRidesPeekLeftArrow.setOnClickListener { onPreviousDayClicked?.invoke(0) }
            bottomSheetRidesPeekRightArrow.setOnClickListener { onNextDayClicked?.invoke(0) }*/
        }
        return view
    }
}