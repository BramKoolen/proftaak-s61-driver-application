package nl.fhict.denmarkroadtax.mainnavigation

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import nl.fhict.denmarkroadtax.invoice.InvoiceModule
import nl.fhict.denmarkroadtax.more.MoreModule
import nl.fhict.denmarkroadtax.rides.RidesModule

@Module
interface MainNavigationActivityModule {

    @ContributesAndroidInjector(
        modules = [
            Bindings::class,
            InvoiceModule::class,
            MoreModule::class,
            RidesModule::class
        ]
    )
    fun mainNavigationActivity(): MainNavigationActivity

    @Module
    interface Bindings {

        @Binds
        fun bindActivity(mainNavigationActivity: MainNavigationActivity): Activity
    }
}