package dev.softikk.acksy

import android.app.Application
import dev.softikk.acksy.di.appModule
import dev.softikk.acksy.di.dataModule
import dev.softikk.acksy.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule, domainModule, dataModule)
        }
    }
}