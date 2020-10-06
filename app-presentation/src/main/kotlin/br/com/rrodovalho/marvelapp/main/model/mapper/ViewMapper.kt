package br.com.rrodovalho.marvelapp.main.model.mapper

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comics
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.ViewComics

fun transformTo(character: Character): ViewCharacter {
    with (character) {
        return ViewCharacter( this.id, this.name, this.description, this.imageUrl, transformToViewComicsList(this.comics))
    }
}

fun transformToViewCharacterList(characterList: List<Character>): List<ViewCharacter> {
    val viewCharacterList = mutableListOf<ViewCharacter>()
    characterList.forEach {
        viewCharacterList.add(transformTo(it))
    }
    return viewCharacterList
}

fun transformTo(comics: Comics): ViewComics {
    with (comics) {
        return ViewComics(this.id, this.name, this.resourceUrl)
    }
}

fun transformTo(viewComics: ViewComics): Comics {
    with (viewComics) {
        return Comics(this.id, this.name, this.resourceUrl)
    }
}

fun transformToViewComicsList(comicsList: List<Comics>): List<ViewComics> {
    val viewComicsList = mutableListOf<ViewComics>()
    comicsList.forEach {
        viewComicsList.add(transformTo(it))
    }
    return viewComicsList
}

fun transformToComicsList(viewComicsList: List<ViewComics>): List<Comics> {
    val comicsList = mutableListOf<Comics>()
    viewComicsList.forEach {
        comicsList.add(transformTo(it))
    }
    return comicsList
}

fun transformTo(viewCharacter: ViewCharacter): Character {
    with (viewCharacter) {
        return Character( this.id, this.name, this.description, this.imageUrl, transformToComicsList(this.comics))
    }
}