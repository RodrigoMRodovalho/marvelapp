package br.com.rrodovalho.marvelapp.util.runner

import android.app.Application
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MarvelApplicationTestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(emptyList())
        }
    }

    internal fun injectModule(module: Module) {
        loadKoinModules(module)
    }
}