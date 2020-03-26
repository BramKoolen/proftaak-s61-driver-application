package nl.fhict.denmarkroadtax.rides.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_pager_item_bottomsheet_rides.view.*
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.generic.extension.convertToDate
import nl.fhict.denmarkroadtax.rides.RideRecapOfDayViewModel
import org.joda.time.DateTime

class RidesBottomSheetAdapter : RecyclerView.Adapter<RidesBottomSheetAdapter.ViewHolder>() {

    var rideRecapOfDayViewModels: MutableList<RideRecapOfDayViewModel> = mutableListOf()

    var onPreviousDayClicked: ((RideRecapOfDayViewModel) -> Unit)? = null
    var onNextDayClicked: ((RideRecapOfDayViewModel) -> Unit)? = null
    var hideClicked: ((RideRecapOfDayViewModel) -> Unit)? = null

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
                bottomSheetRidesContentDate.text = DateTime().convertToDate(viewModel.date).toString("dd MMMM yyyy")
                bottomSheetRidesContentDate.setOnClickListener {
                    hideClicked?.invoke(
                        viewModel
                    )
                }
                if (viewModel.rides != null) {
                    bottomSheetRidesContentLoadingIndicator.visibility = View.GONE
                    bottomSheetRidesContentRecyclerView.visibility = View.VISIBLE
                    bottomSheetRidesPeekContainerContent.visibility = View.VISIBLE

                    bottomSheetRidesPeekCostsTodayLabel.text = viewModel.costs
                    bottomSheetRidesPeekCostPerKmLabel.text = viewModel.average
                    bottomSheetRidesPeekDrivenTodayLabel.text = viewModel.drivenKilometers
                    bottomSheetRidesPeekRidesTodayLabel.text = viewModel.drivenRides

                    val ridesBottomSheetRideDetailsAdapter = RidesBottomSheetRideDetailsAdapter()
                    bottomSheetRidesContentRecyclerView.adapter = ridesBottomSheetRideDetailsAdapter
                    ridesBottomSheetRideDetailsAdapter.rideViewModels = viewModel.rides

                    bottomSheetRidesPeekLeftArrow.setOnClickListener {
                        onPreviousDayClicked?.invoke(
                            viewModel
                        )
                    }
                    bottomSheetRidesPeekRightArrow.setOnClickListener {
                        onNextDayClicked?.invoke(
                            viewModel
                        )
                    }
                }else{
                    bottomSheetRidesContentLoadingIndicator.visibility = View.VISIBLE
                    bottomSheetRidesContentRecyclerView.visibility = View.GONE
                    bottomSheetRidesPeekContainerContent.visibility = View.GONE
                }
            }
        }
    }
}