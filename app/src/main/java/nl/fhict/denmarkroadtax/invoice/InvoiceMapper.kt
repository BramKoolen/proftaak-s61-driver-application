package nl.fhict.denmarkroadtax.invoice

import nl.fhict.denarkroadtax.domain.invoice.model.Invoice
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus
import javax.inject.Inject

class InvoiceMapper @Inject constructor(private val stringProvider: InvoiceContract.StringProvider) {

    fun mapToInvoiceViewModelList(invoices: List<Invoice>): List<InvoiceViewModel> {
        return invoices.map { mapToInvoiceViewModel(it) }
    }

    private fun mapToInvoiceViewModel(invoice: Invoice): InvoiceViewModel {
        return InvoiceViewModel(
            invoice.id.toString(),
            "${stringProvider.invoiceTitle} ${stringProvider.invoiceMonth(invoice.month)}",
            "${stringProvider.invoiceCurrencieSymbol} ${invoice.costs}",
            invoice.paymentStatus,
            invoice.url
        )
    }
}