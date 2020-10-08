package br.com.rrodovalho.marvelapp.main.features.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.usecase.GetCharacterDetailUseCase
import br.com.rrodovalho.marvelapp.main.base.BaseViewModel
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.mapper.transformTo
import kotlinx.coroutines.launch

open class CharacterDetailViewModel(private val getCharacterDetailUseCase: GetCharacterDetailUseCase)
    : BaseViewModel() {

    internal val _observeData = MutableLiveData<Resource<CharacterDetail>>()
    val observeData : LiveData<Resource<CharacterDetail>> = _observeData

    open fun getCharacterDetail (character: ViewCharacter) {
        coroutineScope.launch {
            try {
                val result = getCharacterDetailUseCase
                    .addRequestValues(GetCharacterDetailUseCase.Values(transformTo(character)))
                    .run()
                _observeData.value = Resource.success(result)
            } catch (e: Exception) {
                _observeData.value = Resource.error(e)
            }
        }
    }

}