package br.com.rrodovalho.marvelapp.main.features.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import br.com.rrodovalho.marvelapp.main.base.BaseViewModel
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.mapper.transformTo
import br.com.rrodovalho.marvelapp.main.model.mapper.transformToViewCharacterList
import kotlinx.coroutines.launch

class CharacterListViewModel (private val getCharacterListUseCase: GetCharacterListUseCase)
    : BaseViewModel() {

    private val _observeData = MutableLiveData<Resource<List<ViewCharacter>>>()
    val observeData : LiveData<Resource<List<ViewCharacter>>> = _observeData

    fun getCharacterList () {
        coroutineScope.launch {
            try {
                val result = getCharacterListUseCase
                    .run()
                _observeData.value = Resource.success(transformToViewCharacterList(result))
            } catch (e: Exception) {
                _observeData.value = Resource.Companion.error(e)
            }
        }
    }

}