@file:Suppress("unused")

package nl.fhict.denmarkroadtax.rides

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RidesModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun ridesFragment(): RidesFragment

    @Module
    interface Bindings {

        @Binds
        fun bindView(ridesFragment: RidesFragment): RidesContract.View

        @Binds
        fun bindPresenter(ridesPresenter: RidesPresenter): RidesContract.Presenter

        @Binds
        fun bindStringProvider(rideStringProvider: RideStringProvider): RidesContract.StringProvider
    }
}