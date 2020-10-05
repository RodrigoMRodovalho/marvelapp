package br.com.rrodovalho.marvelapp.main.features.characterlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.domain.usecase.GetCharacterListUseCase
import br.com.rrodovalho.marvelapp.main.base.BaseViewModel
import kotlinx.coroutines.launch

class CharacterListViewModel (private val getCharacterListUseCase: GetCharacterListUseCase)
    : BaseViewModel() {

    private val _observeData = MutableLiveData<Resource<List<Character>>>()
    val observeData : LiveData<Resource<List<Character>>> = _observeData

    fun getCharacterList () {
        coroutineScope.launch {
            try {
                val result = getCharacterListUseCase
                    .run()
                _observeData.value = Resource.success(result)
            } catch (e: Exception) {
                _observeData.value = Resource.Companion.error(e)
            }
        }
    }

}