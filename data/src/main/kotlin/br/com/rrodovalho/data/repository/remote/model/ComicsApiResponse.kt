package br.com.rrodovalho.data.repository.remote.model
import com.google.gson.annotations.SerializedName


data class ComicsApiResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val `data`: ComicsData,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)

data class ComicsData(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<ComicsResult>,
    @SerializedName("total")
    val total: Int
)

data class ComicsResult(
    @SerializedName("characters")
    val characters: Characters,
    @SerializedName("collectedIssues")
    val collectedIssues: List<Any>,
    @SerializedName("collections")
    val collections: List<Any>,
    @SerializedName("creators")
    val creators: Creators,
    @SerializedName("dates")
    val dates: List<Date>,
    @SerializedName("description")
    val description: String,
    @SerializedName("diamondCode")
    val diamondCode: String,
    @SerializedName("digitalId")
    val digitalId: Int,
    @SerializedName("ean")
    val ean: String,
    @SerializedName("events")
    val events: Events,
    @SerializedName("format")
    val format: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("issn")
    val issn: String,
    @SerializedName("issueNumber")
    val issueNumber: Int,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("pageCount")
    val pageCount: Int,
    @SerializedName("prices")
    val prices: List<Price>,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("series")
    val series: Series,
    @SerializedName("stories")
    val stories: Stories,
    @SerializedName("textObjects")
    val textObjects: List<TextObject>,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("title")
    val title: String,
    @SerializedName("upc")
    val upc: String,
    @SerializedName("urls")
    val urls: List<Url>,
    @SerializedName("variantDescription")
    val variantDescription: String,
    @SerializedName("variants")
    val variants: List<Variant>
)

data class Characters(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("returned")
    val returned: Int
)

data class Creators(
    @SerializedName("available")
    val available: Int,
    @SerializedName("collectionURI")
    val collectionURI: String,
    @SerializedName("items")
    val items: List<ItemX>,
    @SerializedName("returned")
    val returned: Int
)

data class Date(
    @SerializedName("date")
    val date: String,
    @SerializedName("type")
    val type: String
)

data class Image(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)

data class Price(
    @SerializedName("price")
    val price: Double,
    @SerializedName("type")
    val type: String
)

data class TextObject(
    @SerializedName("language")
    val language: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("type")
    val type: String
)

data class Variant(
    @SerializedName("name")
    val name: String,
    @SerializedName("resourceURI")
    val resourceURI: String
)