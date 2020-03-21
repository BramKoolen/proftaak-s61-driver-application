package nl.fhict.denmarkroadtax.more

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ProfileModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun profileFragment(): MoreFragment

    @Module
    interface Bindings {

        @Binds
        fun bindView(moreFragment: MoreFragment): MoreContract.View

        @Binds
        fun bindPresenter(profilePresenter: ProfilePresenter): MoreContract.Presenter
    }
}