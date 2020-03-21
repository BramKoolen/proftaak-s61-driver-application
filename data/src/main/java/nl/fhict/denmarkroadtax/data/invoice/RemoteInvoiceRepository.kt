package nl.fhict.denmarkroadtax.data.invoice

import io.reactivex.Single
import nl.fhict.denarkroadtax.domain.invoice.data.InvoiceRepository
import nl.fhict.denarkroadtax.domain.invoice.model.Invoice
import nl.fhict.denarkroadtax.domain.invoice.model.InvoicePaymentStatus
import nl.fhict.denmarkroadtax.data.invoice.network.InvoiceNetworkMapper
import nl.fhict.denmarkroadtax.data.invoice.network.RetrofitInvoiceService
import javax.inject.Inject

class RemoteInvoiceRepository @Inject constructor(
    private val invoiceNetworkMapper: InvoiceNetworkMapper,
    private val retrofitInvoiceService: RetrofitInvoiceService
) : InvoiceRepository {

    override fun fetchInvoices(): Single<List<Invoice>> {
        return Single.just(
            listOf(
                Invoice(
                    123455,
                    1,
                    56.12,
                    InvoicePaymentStatus.PAID
                ),
                Invoice(
                    12334567,
                    2,
                    78.56,
                    InvoicePaymentStatus.OVERDUE
                ),
                Invoice(
                    321,
                    3,
                    32.76,
                    InvoicePaymentStatus.NOTPAID
                )
            )
        )
        /*return retrofitInvoiceService.fetchInvoices()
            .map  { invoiceNetworkMapper.mapToInvoiceList(it) }*/
    }
}