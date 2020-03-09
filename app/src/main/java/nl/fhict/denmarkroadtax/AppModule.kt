package nl.fhict.denmarkroadtax

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import nl.fhict.denmarkroadtax.data.generic.dagger.DataContext

@Module(includes = [AppModule.Bindings::class])
class AppModule {

    @Provides
    @DataContext
    fun provideDataContext(application: DenmarkRoadTaxApplication): Context = application

    @Module
    interface Bindings {

        @Binds
        fun bindApplication(denmarkRoadTaxApplication: DenmarkRoadTaxApplication): Application
    }
}