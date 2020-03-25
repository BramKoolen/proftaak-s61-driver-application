@file:Suppress("unused")

package nl.fhict.denmarkroadtax.login

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface LoginModule {

    @ContributesAndroidInjector(modules = [Bindings::class])
    fun loginActivity(): LoginActivity

    @Module
    interface Bindings {

        @Binds
        fun bindActivity(loginActivity: LoginActivity): Activity

        @Binds
        fun bindView(loginActivity: LoginActivity): LoginContract.View

        @Binds
        fun bindPresenter(loginPresenter: LoginPresenter): LoginContract.Presenter

        @Binds
        fun bindNavigator(loginNavigator: LoginNavigator): LoginContract.Navigator
    }
}