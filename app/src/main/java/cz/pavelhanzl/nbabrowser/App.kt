package cz.pavelhanzl.nbabrowser

import android.app.Application
import cz.pavelhanzl.nbabrowser.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {
    // Start Koin DI
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}