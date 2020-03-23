package nl.fhict.denmarkroadtax.invoice.pdf

import nl.fhict.denmarkroadtax.invoice.InvoiceViewModel
import java.io.File

interface InvoicePdfContract {

    interface View {

        val invoiceViewModel: InvoiceViewModel?

        fun showInvoicePdf(pdfFile: File)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showErrorState()
        fun hideErrorState()
    }

    interface Presenter {

        fun startPresenting()
        fun onErrorStateButtonClicked()
        fun stopPresenting()
    }
}