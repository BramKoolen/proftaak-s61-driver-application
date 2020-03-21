package nl.fhict.denmarkroadtax.invoice

import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus

data class InvoiceViewModel(
    val invoiceId: String,
    val invoiceTitle: String,
    val invoiceCosts: String,
    val invoicePaymentStatus: InvoicePaymentStatus
)