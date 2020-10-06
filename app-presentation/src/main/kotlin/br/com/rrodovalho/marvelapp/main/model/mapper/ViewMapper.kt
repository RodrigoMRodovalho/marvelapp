package br.com.rrodovalho.marvelapp.main.model.mapper

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter

fun transformTo(character: Character): ViewCharacter {
    with (character) {
        return ViewCharacter( this.id, this.name, this.description, this.imageUrl)
    }
}

fun transformTo(characterList: List<Character>): List<ViewCharacter> {
    val viewCharacterList = mutableListOf<ViewCharacter>()
    characterList.forEach {
        viewCharacterList.add(transformTo(it))
    }
    return viewCharacterList
}

fun transformTo(viewCharacter: ViewCharacter): Character {
    with (viewCharacter) {
        return Character( this.id, this.name, this.description, this.imageUrl)
    }
}