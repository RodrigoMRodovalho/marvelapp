package br.com.rrodovalho.marvelapp.main.features.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import br.com.rrodovalho.marvelapp.main.base.BaseViewModel
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.mapper.transformToViewCharacterList
import kotlinx.coroutines.launch

open class CharacterListViewModel (private val getCharacterListUseCase: GetCharacterListUseCase)
    : BaseViewModel() {

    internal val _observeData = MutableLiveData<Resource<List<ViewCharacter>>>()
    val observeData : LiveData<Resource<List<ViewCharacter>>> = _observeData

    open fun getCharacterList () {
        coroutineScope.launch {
            try {
                val result = getCharacterListUseCase
                    .run()
                _observeData.value = Resource.success(transformToViewCharacterList(result))
            } catch (e: Exception) {
                _observeData.value = Resource.error(e)
            }
        }
    }

}