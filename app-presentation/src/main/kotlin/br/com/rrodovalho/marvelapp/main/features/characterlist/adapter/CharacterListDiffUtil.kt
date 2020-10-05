package br.com.rrodovalho.marvelapp.main.features.characterlist.adapter

import androidx.recyclerview.widget.DiffUtil
import br.com.rrodovalho.domain.model.Character

class CharacterListDiffUtil(private val oldCharacterList: List<Character>,
                            private val newCharacterList: List<Character>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCharacterList[oldItemPosition].id == newCharacterList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldCharacterList.size

    override fun getNewListSize(): Int = newCharacterList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldCharacterList[oldItemPosition].description == newCharacterList[newItemPosition].description
                && oldCharacterList[oldItemPosition].id == newCharacterList[newItemPosition].id
                && oldCharacterList[oldItemPosition].imageUrl == newCharacterList[newItemPosition].imageUrl
                && oldCharacterList[oldItemPosition].name == newCharacterList[newItemPosition].name)
    }
}