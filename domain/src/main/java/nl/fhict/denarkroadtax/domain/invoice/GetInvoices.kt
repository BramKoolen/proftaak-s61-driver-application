package nl.fhict.denarkroadtax.domain.invoice

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.invoice.data.InvoiceRepository
import nl.fhict.denarkroadtax.domain.invoice.model.Invoice

import javax.inject.Inject

class GetInvoices @Inject constructor(private val invoiceRepository: InvoiceRepository) {

    operator fun invoke(): Single<List<Invoice>> {
        return invoiceRepository.fetchInvoices().map {
            it.sortedByDescending { it.month }
        }
    }
}