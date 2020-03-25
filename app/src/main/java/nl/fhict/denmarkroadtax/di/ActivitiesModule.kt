package nl.fhict.denmarkroadtax.di

import dagger.Module
import nl.fhict.denmarkroadtax.invoice.pdf.InvoicePdfModule
import nl.fhict.denmarkroadtax.login.LoginModule
import nl.fhict.denmarkroadtax.mainnavigation.MainNavigationActivityModule
import nl.fhict.denmarkroadtax.splash.SplashModule

@Module(
    includes = [SplashModule::class,
        MainNavigationActivityModule::class,
        InvoicePdfModule::class,
        LoginModule::class]
)

class ActivitiesModule