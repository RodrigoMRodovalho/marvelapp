package br.com.rrodovalho.marvelapp.main.model.mapper

import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.ViewComic

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

fun transformTo(comic: Comic): ViewComic {
    with (comic) {
        return ViewComic(this.id, this.name, this.resourceUrl)
    }
}

fun transformTo(viewComic: ViewComic): Comic {
    with (viewComic) {
        return Comic(this.id, this.name, this.resourceUrl)
    }
}

fun transformToViewComicsList(comicList: List<Comic>): List<ViewComic> {
    val viewComicsList = mutableListOf<ViewComic>()
    comicList.forEach {
        viewComicsList.add(transformTo(it))
    }
    return viewComicsList
}

fun transformToComicsList(viewComicList: List<ViewComic>): List<Comic> {
    val comicsList = mutableListOf<Comic>()
    viewComicList.forEach {
        comicsList.add(transformTo(it))
    }
    return comicsList
}

fun transformTo(viewCharacter: ViewCharacter): Character {
    with (viewCharacter) {
        return Character( this.id, this.name, this.description, this.imageUrl, transformToComicsList(this.comics))
    }
}