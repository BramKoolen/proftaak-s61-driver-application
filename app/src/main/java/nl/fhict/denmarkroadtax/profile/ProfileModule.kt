package nl.fhict.denmarkroadtax.profile

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ProfileModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun profileFragment(): ProfileFragment

    @Module
    interface Bindings {

        @Binds
        fun bindView(profileFragment: ProfileFragment): ProfileContract.View

        @Binds
        fun bindPresenter(profilePresenter: ProfilePresenter): ProfileContract.Presenter
    }
}