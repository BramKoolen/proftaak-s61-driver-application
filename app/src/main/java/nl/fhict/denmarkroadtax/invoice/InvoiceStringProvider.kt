package nl.fhict.denmarkroadtax.invoice

import android.content.Context
import nl.fhict.denmarkroadtax.R
import nl.fhict.denmarkroadtax.generic.dagger.ApplicationContext
import javax.inject.Inject

class InvoiceStringProvider @Inject constructor(@ApplicationContext private val context: Context) :
    InvoiceContract.StringProvider {

    override fun invoiceMonth(month: Int): String {
        return context.resources.getStringArray(R.array.invoice_month_array)[month - 1]
    }

    override val invoiceCurrencieSymbol: String
        get() = context.getString(R.string.invoice_currencie_symbol)
    override val invoiceTitle: String
        get() = context.getString(R.string.invoice_title)
}