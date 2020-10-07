package br.com.rrodovalho.data.repository.mapper

import br.com.rrodovalho.data.repository.local.db.entities.ComicsDetailEntity
import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import br.com.rrodovalho.data.repository.remote.model.Thumbnail
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Comic
import br.com.rrodovalho.domain.model.ComicDetail

const val HTTPS_REF = "https"
const val HTTP_REF = "http"

fun transformTo(characterApiResponse: CharacterApiResponse): List<Character> {

    val characterList = mutableListOf<Character>()
    characterApiResponse.data.results.forEach {

        val comicsList = mutableListOf<Comic>()

        it.comics.items.forEach { comics ->
            comicsList.add(
                Comic(comics.resourceURI.substringAfterLast("/"),
                    comics.name,
                    handleMissingHttpSecurityUrl(comics.resourceURI)
                )
            )
        }

        val character = Character("${it.id}", it.name,
            it.description,
            composeImageUrl(it.thumbnail),
            comicsList)

        characterList.add(character)
    }

    return characterList
}

fun transformTo(comicsApiResponse: ComicsApiResponse): ComicDetail {

    val comicsResult = comicsApiResponse.data.results[0]

    return ComicDetail(
        Comic(comicsResult.id.toString(), comicsResult.title,
            handleMissingHttpSecurityUrl(comicsResult.resourceURI)),
        comicsResult.description,
        composeImageUrl(comicsResult.thumbnail)
    )
}

fun transformTo(comicsDetailEntity: ComicsDetailEntity): ComicDetail {
    with (comicsDetailEntity) {
        return ComicDetail(
            Comic(this.id, this.name, this.resourceUri)
            , this.description, this.imageUrl
        )
    }
}

fun transformTo(comicDetail: ComicDetail): ComicsDetailEntity {
    with(comicDetail){
        return ComicsDetailEntity(
            this.comic.id,
            this.comic.name,
            this.comic.resourceUrl,
            this.description,
            this.imageUrl)
    }
}

private fun composeImageUrl(thumbnail: Thumbnail): String {
    return composeImageUrlWithFileExtension(
        handleMissingHttpSecurityUrl(thumbnail.path),
        thumbnail.extension
    )
}

private fun handleMissingHttpSecurityUrl(url: String): String {
    if (url.contains(HTTPS_REF).not()){
        return url.replace(HTTP_REF, HTTPS_REF)
    }
    return url
}

private fun composeImageUrlWithFileExtension(imageUrl: String, extensionFile: String): String {
    return imageUrl.plus(".")
        .plus(extensionFile)
}