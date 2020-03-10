package nl.fhict.denmarkroadtax.invoice

interface InvoiceContract {

    interface View {

    }

    interface Presenter {

        fun startPresenting()
        fun stopPresenting()
    }
}