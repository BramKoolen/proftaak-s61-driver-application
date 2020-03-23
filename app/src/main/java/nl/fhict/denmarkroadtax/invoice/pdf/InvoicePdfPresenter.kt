package nl.fhict.denmarkroadtax.invoice.pdf

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.invoice.GetInvoicePdfFile
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class InvoicePdfPresenter @Inject constructor(
    private val view: InvoicePdfContract.View,
    private val getInvoicePdfFile: GetInvoicePdfFile) :
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
        disposable?.dispose()
        disposable = getInvoicePdfFile(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoadingIndicator() }
            .subscribe(::onInvoicesFetched, ::onInvoicesFetchedFailed)
    }

    private fun onInvoicesFetched(pdfFile: File) {
        view.showInvoicePdf(pdfFile)
    }

    private fun onInvoicesFetchedFailed(throwable: Throwable) {
        //view.showErrorState()
        Timber.e(throwable)
    }
}