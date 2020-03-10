package nl.fhict.denmarkroadtax.invoice

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface InvoiceModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun invoiceFragment(): InvoiceFragment

    @Module
    interface Bindings {

        @Binds
        fun bindView(invoiceFragment: InvoiceFragment): InvoiceContract.View

        @Binds
        fun bindPresenter(invoicePresenter: InvoicePresenter): InvoiceContract.Presenter
    }
}