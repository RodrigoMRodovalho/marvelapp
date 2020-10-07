package br.com.rrodovalho.data.repository.remote.api

import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiContract {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    }

    @GET("characters")
    suspend fun fetchCharacterList(@QueryMap params: Map<String, String>): CharacterApiResponse

    @GET
    suspend fun fetchComicsFromCharacter(@Url comicsUrl: String): ComicsApiResponse

}