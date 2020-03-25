package nl.fhict.denmarkroadtax.invoice.pdf

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class InvoicePdfPresenter @Inject constructor(
    private val view: InvoicePdfContract.View) :
    InvoicePdfContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
        view.invoiceViewModel?.url?.let { loadPdfFile(it) }
    }

    override fun onErrorStateButtonClicked() {
        view.hideErrorState()
        view.invoiceViewModel?.url?.let { loadPdfFile(it) }
    }

    override fun stopPresenting() {
        disposable?.dispose()
    }

    private fun loadPdfFile(url: String) {

    }
}