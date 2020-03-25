package nl.fhict.denmarkroadtax.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import nl.fhict.denmarkroadtax.DenmarkRoadTaxApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DataModule::class,
        ActivitiesModule::class
    ]
)
interface AppComponent : AndroidInjector<DenmarkRoadTaxApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DenmarkRoadTaxApplication>() {

        abstract fun appModule(appModule: AppModule): Builder
    }
}