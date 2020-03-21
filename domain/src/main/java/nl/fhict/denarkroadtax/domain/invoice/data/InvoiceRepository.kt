package nl.fhict.denarkroadtax.domain.invoice.data

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.invoice.model.Invoice

interface InvoiceRepository {

    fun fetchInvoices(): Single<List<Invoice>>
}