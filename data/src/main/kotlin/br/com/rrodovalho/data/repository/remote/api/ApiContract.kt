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
        const val API_KEY = "2f8c72d3769c9199dbe5372409bc0da2"
        const val TS = "7876398765489276"
        const val HASH = "c15039262d4a073f3238b99aa7b9cc0f"

    }

    @GET("characters")
    suspend fun fetchCharacterList(@QueryMap params: Map<String, String>): CharacterApiResponse

    @GET
    suspend fun fetchComicsFromCharacter(@Url comicsUrl: String): ComicsApiResponse

}