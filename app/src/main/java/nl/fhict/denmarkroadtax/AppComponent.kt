package nl.fhict.denmarkroadtax

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nl.fhict.denmarkroadtax.data.ride.RideModule
import nl.fhict.denmarkroadtax.mainnavigation.MainNavigationActivityModule
import nl.fhict.denmarkroadtax.splash.SplashActivityModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        SplashActivityModule::class,
        MainNavigationActivityModule::class,
        RideModule::class
    ]
)
interface AppComponent : AndroidInjector<DenmarkRoadTaxApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DenmarkRoadTaxApplication>() {

        abstract fun appModule(appModule: AppModule): Builder
    }
}