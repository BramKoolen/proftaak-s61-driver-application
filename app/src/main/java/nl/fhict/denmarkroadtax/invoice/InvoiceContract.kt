package nl.fhict.denmarkroadtax.invoice

interface InvoiceContract {

    interface View {

        fun showInvoices(invoiceList: List<InvoiceViewModel>)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showErrorState()
        fun hideErrorState()
    }

    interface Presenter {

        fun startPresenting()
        fun onInvoiceClicked(invoice: InvoiceViewModel)
        fun onErrorStateButtonClicked()
        fun stopPresenting()
    }

    interface Navigator {

        fun navigateToInvoicePdf(invoiceViewModel: InvoiceViewModel)
    }

    interface StringProvider {

        fun invoiceMonth(month: Int): String
        val invoiceCurrencieSymbol: String
        val invoiceTitle: String
    }
}