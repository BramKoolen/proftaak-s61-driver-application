package nl.fhict.denarkroadtax.domain.invoice.model

data class Invoice(
    val id: Long,
    val month: Int,
    val costs: Double,
    val paymentStatus: InvoicePaymentStatus
)