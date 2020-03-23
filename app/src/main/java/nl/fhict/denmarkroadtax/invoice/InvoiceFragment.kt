package nl.fhict.denmarkroadtax.invoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.downloader.PRDownloader
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_invoice.*
import kotlinx.android.synthetic.main.fragment_invoice.view.*
import kotlinx.android.synthetic.main.view_internet_error_state.view.*
import nl.fhict.denmarkroadtax.R
import javax.inject.Inject

class InvoiceFragment : DaggerFragment(), InvoiceContract.View {

    @Inject
    lateinit var presenter: InvoiceContract.Presenter

    private val invoiceAdapter by lazy {
        InvoiceAdapter().apply {
            onInvoiceClicked = presenter::onInvoiceClicked
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_invoice, container, false)
            view.invoiceRecyclerView.adapter = invoiceAdapter
        view.internetErrorStateTryAgainButton.setOnClickListener { presenter.onErrorStateButtonClicked() }
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting()
    }

    override fun onPause() {
        presenter.stopPresenting()
        super.onPause()
    }

    override fun showInvoices(invoiceList: List<InvoiceViewModel>) {
        invoiceLoadingIndicator.visibility = View.GONE
        invoiceListView.visibility = View.VISIBLE
        invoiceAdapter.invoiceViewModelList = invoiceList
    }

    override fun showLoadingIndicator() {
        invoiceLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        invoiceLoadingIndicator.visibility = View.GONE
    }

    override fun showErrorState() {
        invoiceListView.visibility = View.GONE
        internetErrorStateView.visibility = View.VISIBLE
    }

    override fun hideErrorState() {
        internetErrorStateView.visibility = View.GONE
    }
}