package com.p3g3.magellangpt

import android.app.Application
import com.p3g3.magellangpt.di.dataModule
import com.p3g3.magellangpt.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OpenApiApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@OpenApiApplication)
            modules(dataModule)
            modules(domainModule)
        }
    }
}