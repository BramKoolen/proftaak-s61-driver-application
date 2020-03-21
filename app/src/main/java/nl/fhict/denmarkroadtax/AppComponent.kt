package nl.fhict.denmarkroadtax

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nl.fhict.denmarkroadtax.data.invoice.InvoiceModule
import nl.fhict.denmarkroadtax.data.ride.RideModule
import nl.fhict.denmarkroadtax.mainnavigation.MainNavigationActivityModule
import nl.fhict.denmarkroadtax.splash.SplashModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        SplashModule::class,
        MainNavigationActivityModule::class,
        RideModule::class,
        InvoiceModule::class
    ]
)
interface AppComponent : AndroidInjector<DenmarkRoadTaxApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DenmarkRoadTaxApplication>() {

        abstract fun appModule(appModule: AppModule): Builder
    }
}