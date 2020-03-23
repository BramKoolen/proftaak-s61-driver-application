package nl.fhict.denmarkroadtax.invoice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_invoice.view.*
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus
import nl.fhict.denmarkroadtax.R

class InvoiceAdapter : RecyclerView.Adapter<InvoiceAdapter.ViewHolder>() {

    var invoiceViewModelList: List<InvoiceViewModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onInvoiceClicked: ((InvoiceViewModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_invoice,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(invoiceViewModelList[position])
    }

    override fun getItemCount(): Int {
        return invoiceViewModelList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(viewModel: InvoiceViewModel) {
            itemView.run {
                listItemInvoiceTitle.text = viewModel.invoiceTitle
                listItemInvoiceSubTitle.text = viewModel.invoiceId
                listItemInvoiceCosts.text = viewModel.invoiceCosts
                listItemInvoicePaymentStatus.text = mapInvoicePaymentStatusToString(viewModel.invoicePaymentStatus)
                when(viewModel.invoicePaymentStatus){
                    InvoicePaymentStatus.PAID -> {
                        listItemInvoicePaymentStatus.background = context.getDrawable(R.drawable.bg_invoice_payment_status_paid)
                        listItemInvoicePaymentStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
                    }
                    InvoicePaymentStatus.NOTPAID -> {
                        listItemInvoicePaymentStatus.background = context.getDrawable(R.drawable.bg_invoice_payment_status_not_paid)
                        listItemInvoicePaymentStatus.setTextColor(ContextCompat.getColor(context, R.color.orange))
                    }
                    InvoicePaymentStatus.OVERDUE -> {
                        listItemInvoicePaymentStatus.background = context.getDrawable(R.drawable.bg_invoice_payment_status_overdue)
                        listItemInvoicePaymentStatus.setTextColor(ContextCompat.getColor(context, R.color.red))
                    }
                }
                setOnClickListener { onInvoiceClicked?.invoke(viewModel) }
            }
        }
    }

    private fun mapInvoicePaymentStatusToString(invoicePaymentStatus: InvoicePaymentStatus): String {
        return when (invoicePaymentStatus) {
            InvoicePaymentStatus.PAID -> "paid"
            InvoicePaymentStatus.NOTPAID -> "not paid"
            InvoicePaymentStatus.OVERDUE -> "overdue"
        }
    }
}