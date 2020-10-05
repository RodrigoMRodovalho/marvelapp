package br.com.rrodovalho.marvelapp.application

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import br.com.rrodovalho.data.di.dataModule
import br.com.rrodovalho.domain.usecase.di.useCaseModule
import br.com.rrodovalho.marvelapp.main.features.di.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin(){
        startKoin {
            androidContext(this@MarvelApplication)
            modules(listOf(featuresModule, useCaseModule, dataModule))
        }
    }

}