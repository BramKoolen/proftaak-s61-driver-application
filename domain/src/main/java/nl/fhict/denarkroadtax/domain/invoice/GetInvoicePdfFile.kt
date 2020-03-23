package nl.fhict.denarkroadtax.domain.invoice

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.invoice.data.InvoiceRepository
import java.io.File
import javax.inject.Inject

class GetInvoicePdfFile @Inject constructor(private val invoiceRepository: InvoiceRepository) {

    operator fun invoke(url: String): Single<File> {
        return invoiceRepository.fetchInvoicePdfFile(url)
    }
}