package cz.pavelhanzl.nbabrowser

import android.app.Application
import cz.pavelhanzl.nbabrowser.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * Main Application class for the NBA Browser app.
 *
 * This class extends [Application] and is used to set up the Koin dependency injection framework
 * at the start of the application. It initializes Koin with the necessary modules and the application context.
 */
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