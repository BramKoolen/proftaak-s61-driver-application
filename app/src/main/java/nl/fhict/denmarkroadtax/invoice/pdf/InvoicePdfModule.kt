@file:Suppress("unused")

package nl.fhict.denmarkroadtax.invoice.pdf

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface InvoicePdfModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun invoicePdfActivity(): InvoicePdfActivity

    @Module
    interface Bindings {

        @Binds
        fun bindView(invoicePdfActivity: InvoicePdfActivity): InvoicePdfContract.View

        @Binds
        fun bindPresenter(invoicePdfPresenter: InvoicePdfPresenter): InvoicePdfContract.Presenter
    }
}