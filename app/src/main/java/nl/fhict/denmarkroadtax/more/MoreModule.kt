package nl.fhict.denmarkroadtax.more

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MoreModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun moreFragment(): MoreFragment

    @Module
    interface Bindings {

        @Binds
        fun bindView(moreFragment: MoreFragment): MoreContract.View

        @Binds
        fun bindPresenter(morePresenter: MorePresenter): MoreContract.Presenter
    }
}