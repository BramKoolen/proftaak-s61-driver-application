package nl.fhict.denmarkroadtax.invoice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import nl.fhict.denmarkroadtax.R
import javax.inject.Inject

class InvoiceFragment : DaggerFragment(), InvoiceContract.View {

    @Inject
    lateinit var presenter: InvoiceContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_invoice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.startPresenting()
    }

    override fun onPause() {
        presenter.stopPresenting()
        super.onPause()
    }
}