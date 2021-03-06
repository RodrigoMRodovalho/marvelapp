package br.com.rrodovalho.domain.usecase.di

import br.com.rrodovalho.domain.usecase.GetCharacterDetailUseCase
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {GetCharacterListUseCase(get()) }
    factory { GetCharacterDetailUseCase(get()) }
}
