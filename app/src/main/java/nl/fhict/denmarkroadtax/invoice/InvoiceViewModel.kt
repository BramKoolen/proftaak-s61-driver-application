package nl.fhict.denmarkroadtax.invoice

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus

@Parcelize
data class InvoiceViewModel(
    val invoiceId: String,
    val invoiceTitle: String,
    val invoiceCosts: String,
    val invoicePaymentStatus: InvoicePaymentStatus,
    val url: String
): Parcelable