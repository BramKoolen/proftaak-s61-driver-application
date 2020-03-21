package nl.fhict.denmarkroadtax.invoice

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import nl.fhict.denarkroadtax.domain.invoice.GetInvoices
import timber.log.Timber
import javax.inject.Inject

class InvoicePresenter @Inject constructor(
    private val view: InvoiceContract.View,
    private val navigator: InvoiceContract.Navigator,
    private val getInvoices: GetInvoices,
    private val invoiceMapper: InvoiceMapper
) : InvoiceContract.Presenter {

    private var disposable: Disposable? = null

    override fun startPresenting() {
       getInvoices()
    }

    private fun getInvoices() {
        disposable?.dispose()
        disposable = getInvoices.invoke()
            .map { invoiceMapper.mapToInvoiceViewModelList(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { view.showLoadingIndicator() }
            .subscribe(::onInvoicesFetched, ::onInvoicesFetchedFailed)
    }

    private fun onInvoicesFetched(invoiceViewModelList: List<InvoiceViewModel>){
        view.showInvoices(invoiceViewModelList)
    }

    private fun onInvoicesFetchedFailed(throwable: Throwable){
        view.showErrorState()
        Timber.e(throwable)

    }

    override fun onInvoiceClicked(invoice: InvoiceViewModel) {
        navigator.navigateToInvoiceDetail(invoice.invoiceId)
    }

    override fun onErrorStateButtonClicked() {
        view.hideErrorState()
        getInvoices()
    }

    override fun stopPresenting() {
       disposable?.dispose()
    }
}