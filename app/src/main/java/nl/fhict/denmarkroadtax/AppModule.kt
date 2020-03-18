@file:Suppress("unused")

package nl.fhict.denmarkroadtax

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import nl.fhict.denmarkroadtax.data.generic.dagger.DataContext
import nl.fhict.denmarkroadtax.data.generic.room.RoomDRTDatabase
import javax.inject.Singleton

@Module(includes = [AppModule.Bindings::class])
class AppModule {

    @Provides
    @DataContext
    fun provideDataContext(application: DenmarkRoadTaxApplication): Context = application

    @Singleton
    @Provides
    fun provideDatabase(@DataContext context: Context): RoomDRTDatabase {
        return RoomDRTDatabase.getInstance(context)
    }

    @Module
    interface Bindings {

        @Binds
        fun bindApplication(denmarkRoadTaxApplication: DenmarkRoadTaxApplication): Application
    }
}