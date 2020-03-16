package nl.fhict.denmarkroadtax.rides.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_pager_item_bottomsheet_rides.view.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.rides.RideRecapOfDayViewModel

class RidesBottomSheetAdapter : RecyclerView.Adapter<RidesBottomSheetAdapter.ViewHolder>() {

    var rideRecapOfDayViewModels: List<RideRecapOfDayViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onPreviousDayClicked: ((Int) -> Unit)? = null
    var onNextDayClicked: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_pager_item_bottomsheet_rides,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rideRecapOfDayViewModels[position])
    }

    override fun getItemCount(): Int {
        return rideRecapOfDayViewModels.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewModel: RideRecapOfDayViewModel) {
            itemView.run {
                bottomSheetRidesPeekCostsTodayLabel.text = viewModel.costs
                bottomSheetRidesPeekCostPerKmLabel.text = viewModel.average
                bottomSheetRidesPeekDrivenTodayLabel.text = viewModel.drivenKilometers
                bottomSheetRidesPeekRidesTodayLabel.text = viewModel.drivenRides

                val ridesBottomSheetRideDetailsAdapter = RidesBottomSheetRideDetailsAdapter()
                bottomSheetRidesContentRecyclerView.adapter = ridesBottomSheetRideDetailsAdapter
                ridesBottomSheetRideDetailsAdapter.rideViewModels = viewModel.rides

                bottomSheetRidesPeekLeftArrow.setOnClickListener { onPreviousDayClicked?.invoke(0) }
                bottomSheetRidesPeekRightArrow.setOnClickListener { onNextDayClicked?.invoke(0) }
            }
        }
    }
}