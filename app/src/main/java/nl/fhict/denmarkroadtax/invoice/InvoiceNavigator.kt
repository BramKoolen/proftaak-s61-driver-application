package nl.fhict.denmarkroadtax.invoice

import android.app.Activity
import nl.fhict.denmarkroadtax.invoice.pdf.InvoicePdfActivity
import javax.inject.Inject

class InvoiceNavigator @Inject constructor(private val activity: Activity) :
    InvoiceContract.Navigator {

    override fun navigateToInvoicePdf(invoiceViewModel: InvoiceViewModel) {
        activity.startActivity(InvoicePdfActivity.createIntent(activity,invoiceViewModel))
    }
}