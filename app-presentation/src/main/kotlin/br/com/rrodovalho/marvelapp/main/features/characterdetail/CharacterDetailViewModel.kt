package br.com.rrodovalho.marvelapp.main.features.characterdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.usecase.GetCharacterDetailUseCase
import br.com.rrodovalho.marvelapp.main.base.BaseViewModel
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getCharacterDetailUseCase: GetCharacterDetailUseCase)
    : BaseViewModel() {

    private val _observeData = MutableLiveData<Resource<CharacterDetail>>()
    val observeData : LiveData<Resource<CharacterDetail>> = _observeData

    fun getCharacterDetail (character: Character) {
        coroutineScope.launch {
            try {
                val result = getCharacterDetailUseCase
                    .addRequestValues(GetCharacterDetailUseCase.Values(character))
                    .run()
                _observeData.value = Resource.success(result)
            } catch (e: Exception) {
                _observeData.value = Resource.Companion.error(e)
            }
        }
    }

}