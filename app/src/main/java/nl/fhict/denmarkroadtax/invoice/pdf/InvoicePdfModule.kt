@file:Suppress("unused")

package nl.fhict.denmarkroadtax.invoice.pdf

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface InvoicePdfModule {

    @ContributesAndroidInjector
    fun invoicePdfActivity(): InvoicePdfActivity
}