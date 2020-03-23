package nl.fhict.denmarkroadtax

import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class DenmarkRoadTaxApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initFileDownloader()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    private fun initFileDownloader(){
        val config = PRDownloaderConfig.newBuilder()
            .setDatabaseEnabled(true)
            .build()
        PRDownloader.initialize(applicationContext, config)
    }
}