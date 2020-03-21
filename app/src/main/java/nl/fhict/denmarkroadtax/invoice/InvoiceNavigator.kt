package nl.fhict.denmarkroadtax.invoice

import android.app.Activity
import javax.inject.Inject

class InvoiceNavigator @Inject constructor(private val activity: Activity) :
    InvoiceContract.Navigator {

    override fun navigateToInvoiceDetail(invoiceId: String) {
        //TODO("not implemented")
    }
}