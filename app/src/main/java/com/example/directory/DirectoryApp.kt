package com.example.directory

import android.app.Application
import com.example.directory.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DirectoryApp : Application() {
    private val modules = listOf(
        apiModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule,
        useCasesModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(modules)
        }
    }
}