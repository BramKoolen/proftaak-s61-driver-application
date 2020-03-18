package nl.fhict.denmarkroadtax.rides.bottomsheet

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_bottomsheet_rides.view.*
import nl.fhict.denarkroadtax.domain.ride.model.RideAddressType
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.rides.RideViewModel

class RidesBottomSheetRideDetailsAdapter : RecyclerView.Adapter<RidesBottomSheetRideDetailsAdapter.ViewHolder>() {

    var rideViewModels: List<RideViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_bottomsheet_rides,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rideViewModels[position])
    }

    override fun getItemCount(): Int {
        return rideViewModels.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewModel: RideViewModel) {
            itemView.run {
                listItemBottomSheetRidesAddressTitle.text = viewModel.startTitle
                listItemBottomSheetRidesAddressSubTitle.text = viewModel.startAddress
                when (viewModel.rideAddressType) {
                    RideAddressType.START -> {
                        listItemBottomSheetRidesTimelineRec.setImageDrawable(resources.getDrawable(R.drawable.ic_timeline_header_rectangle,context.theme))
                        listItemBottomSheetRidesAddressTime.text = viewModel.startTime
                        listItemBottomSheetRidesRideKmLabel.text = viewModel.drivenKilometers
                        listItemBottomSheetRidesRideTimeLabel.text = viewModel.drivenTime
                    }
                    RideAddressType.STOPOVER -> {
                        listItemBottomSheetRidesAddressTime.text = "${viewModel.startTime} - ${viewModel.endTime}"
                        listItemBottomSheetRidesRideKmLabel.text = viewModel.drivenKilometers
                        listItemBottomSheetRidesRideTimeLabel.text = viewModel.drivenTime
                    }
                    RideAddressType.END -> {
                        listItemBottomSheetRidesAddressTime.text = viewModel.startTime
                        listItemBottomSheetRidesTimelineRec.setImageDrawable(resources.getDrawable(R.drawable.ic_timeline_footer_rectangle,context.theme))
                        listItemBottomSheetRidesRideKmLabel.visibility = GONE
                        listItemBottomSheetRidesRideTimeLabel.visibility = GONE
                    }
                }
            }
        }
    }
}