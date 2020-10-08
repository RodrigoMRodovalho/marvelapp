package br.com.rrodovalho.marvelapp.main.features.di

import br.com.rrodovalho.marvelapp.main.features.characterdetail.CharacterDetailViewModel
import br.com.rrodovalho.marvelapp.main.features.characterlist.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

const val CHARACTER_BUNDLE_NAVIGATION_KEY = "character"

val featuresModule = module {
    viewModel { CharacterListViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
}