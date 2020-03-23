package nl.fhict.denarkroadtax.domain.invoice.data

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.invoice.model.Invoice
import java.io.File

interface InvoiceRepository {

    fun fetchInvoices(): Single<List<Invoice>>
    fun fetchInvoicePdfFile(url: String): Single<File>
}