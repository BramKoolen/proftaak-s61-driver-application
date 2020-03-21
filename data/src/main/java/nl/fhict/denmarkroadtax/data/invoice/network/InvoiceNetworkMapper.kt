package nl.fhict.denmarkroadtax.data.invoice.network

import nl.fhict.denarkroadtax.domain.invoice.model.Invoice
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus
import nl.fhict.denmarkroadtax.data.invoice.network.response.InvoiceResponse
import timber.log.Timber
import javax.inject.Inject

class InvoiceNetworkMapper @Inject constructor() {

    fun mapToInvoiceList(invoiceResponseList: List<InvoiceResponse>): List<Invoice> {
        return invoiceResponseList.map { mapToInvoice(it) }
    }

    private fun mapToInvoice(invoiceResponse: InvoiceResponse): Invoice {
        with(invoiceResponse) {
            return Invoice(
                id,
                month,
                costs,
                mapStringToInvoicePaymentStatus(paymentStatus)
            )
        }
    }

    private fun mapStringToInvoicePaymentStatus(invoicePaymentStatusString: String): InvoicePaymentStatus {
        return try {
            InvoicePaymentStatus.valueOf(invoicePaymentStatusString)
        } catch (e: IllegalArgumentException) {
            Timber.e(e)
            InvoicePaymentStatus.NOTPAID
        }
    }
}