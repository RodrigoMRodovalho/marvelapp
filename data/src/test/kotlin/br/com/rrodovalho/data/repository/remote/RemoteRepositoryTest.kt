package br.com.rrodovalho.data.repository.remote

import br.com.rrodovalho.data.repository.remote.api.ApiContract
import br.com.rrodovalho.data.repository.remote.model.CharacterApiResponse
import br.com.rrodovalho.data.repository.remote.model.Comics
import br.com.rrodovalho.data.repository.remote.model.ComicsApiResponse
import br.com.rrodovalho.domain.model.Comic
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RemoteRepositoryTest {

    lateinit var sut : RemoteRepository

    @Mock
    lateinit var apiContract: ApiContract

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = RemoteRepository(apiContract)
    }

    @Test
    fun remoteRepository_WhenFetchMarvelCharacterList_ShouldReturnMappedDataFromApi() {

        runBlockingTest {

            val apiResponse = Gson().fromJson(mockCharacterApiResponseJson,
                CharacterApiResponse::class.java)

            val limit = 10
            val offset = 30

            whenever(apiContract.fetchCharacterList(mapOf("limit" to "10", "offset" to "30")))
                .thenReturn(apiResponse)

            val characterList = sut.fetchMarvelCharacterList(limit, offset)

            val firstCharacter = characterList[0]

            assertEquals("1011334", firstCharacter.id)
            assertEquals("3-D Man", firstCharacter.name)
            assertEquals("", firstCharacter.description)
            assertEquals("https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
                firstCharacter.imageUrl)
            assertEquals("21366", firstCharacter.comics[0].id)
            assertEquals("Avengers: The Initiative (2007) #14", firstCharacter.comics[0].name)
            assertEquals("https://gateway.marvel.com/v1/public/comics/21366", firstCharacter.comics[0].resourceUrl)
            assertEquals("24571", firstCharacter.comics[1].id)
            assertEquals("Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)", firstCharacter.comics[1].name)
            assertEquals("https://gateway.marvel.com/v1/public/comics/24571", firstCharacter.comics[1].resourceUrl)


            val secondCharacter = characterList[1]

            assertEquals("1017100", secondCharacter.id)
            assertEquals("A-Bomb (HAS)", secondCharacter.name)
            assertEquals("Rick Jones has been Hulk's best bud since day one, but now he's more than", secondCharacter.description)
            assertEquals("https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg",
                secondCharacter.imageUrl)
            assertEquals("40632", secondCharacter.comics[0].id)
            assertEquals("Hulk (2008) #53", secondCharacter.comics[0].name)
            assertEquals("https://gateway.marvel.com/v1/public/comics/40632", secondCharacter.comics[0].resourceUrl)
        }
    }

    @Test
    fun remoteRepository_WhenFetchComicsDetail_ShouldReturnMappedDataFromApi() {

        runBlockingTest {

            val comic = Comic("21366",
                "Avengers: The Initiative (2007) #14",
                "https://gateway.marvel.com/v1/public/comics/21366")

            val apiResponse = Gson().fromJson(mockComicsApiResponseJson,
                ComicsApiResponse::class.java)

            whenever(apiContract.fetchComicsFromCharacter(comic.resourceUrl))
                .thenReturn(apiResponse)

            val comicDetail = sut.fetchComicsDetail(comic)

            assertEquals(comic, comicDetail.comic)
            assertEquals("The fates of The Initiative, the United States", comicDetail.description)
            assertEquals("https://i.annihil.us/u/prod/marvel/i/mg/c/60/58dbce634ea70.jpg", comicDetail.imageUrl)

        }

    }

    private val mockCharacterApiResponseJson = "{\n" +
            "    \"code\": 200,\n" +
            "    \"status\": \"Ok\",\n" +
            "    \"copyright\": \"© 2020 MARVEL\",\n" +
            "    \"attributionText\": \"Data provided by Marvel. © 2020 MARVEL\",\n" +
            "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2020 MARVEL</a>\",\n" +
            "    \"etag\": \"43140c22e35a5f0f2a436bec04a140066ead2a62\",\n" +
            "    \"data\": {\n" +
            "        \"offset\": 0,\n" +
            "        \"limit\": 10,\n" +
            "        \"total\": 1493,\n" +
            "        \"count\": 2,\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"id\": 1011334,\n" +
            "                \"name\": \"3-D Man\",\n" +
            "                \"description\": \"\",\n" +
            "                \"modified\": \"2014-04-29T14:18:17-0400\",\n" +
            "                \"thumbnail\": {\n" +
            "                    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784\",\n" +
            "                    \"extension\": \"jpg\"\n" +
            "                },\n" +
            "                \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1011334\",\n" +
            "                \"comics\": {\n" +
            "                    \"available\": 12,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011334/comics\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/21366\",\n" +
            "                            \"name\": \"Avengers: The Initiative (2007) #14\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/24571\",\n" +
            "                            \"name\": \"Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 2\n" +
            "                },\n" +
            "                \"series\": {\n" +
            "                    \"available\": 3,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011334/series\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/1945\",\n" +
            "                            \"name\": \"Avengers: The Initiative (2007 - 2010)\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/2005\",\n" +
            "                            \"name\": \"Deadpool (1997 - 2002)\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/2045\",\n" +
            "                            \"name\": \"Marvel Premiere (1972 - 1981)\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 3\n" +
            "                },\n" +
            "                \"stories\": {\n" +
            "                    \"available\": 21,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011334/stories\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/19947\",\n" +
            "                            \"name\": \"Cover #19947\",\n" +
            "                            \"type\": \"cover\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/19948\",\n" +
            "                            \"name\": \"The 3-D Man!\",\n" +
            "                            \"type\": \"interiorStory\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 2\n" +
            "                },\n" +
            "                \"events\": {\n" +
            "                    \"available\": 1,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1011334/events\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/events/269\",\n" +
            "                            \"name\": \"Secret Invasion\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 1\n" +
            "                },\n" +
            "                \"urls\": [\n" +
            "                    {\n" +
            "                        \"type\": \"detail\",\n" +
            "                        \"url\": \"http://marvel.com/characters/74/3-d_man?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"type\": \"wiki\",\n" +
            "                        \"url\": \"http://marvel.com/universe/3-D_Man_(Chandler)?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"type\": \"comiclink\",\n" +
            "                        \"url\": \"http://marvel.com/comics/characters/1011334/3-d_man?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 1017100,\n" +
            "                \"name\": \"A-Bomb (HAS)\",\n" +
            "                \"description\": \"Rick Jones has been Hulk's best bud since day one, but now he's more than\",\n" +
            "                \"modified\": \"2013-09-18T15:54:04-0400\",\n" +
            "                \"thumbnail\": {\n" +
            "                    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16\",\n" +
            "                    \"extension\": \"jpg\"\n" +
            "                },\n" +
            "                \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1017100\",\n" +
            "                \"comics\": {\n" +
            "                    \"available\": 3,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/comics\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/40632\",\n" +
            "                            \"name\": \"Hulk (2008) #53\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 3\n" +
            "                },\n" +
            "                \"series\": {\n" +
            "                    \"available\": 2,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/series\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/17765\",\n" +
            "                            \"name\": \"FREE COMIC BOOK DAY 2013 1 (2013)\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/3374\",\n" +
            "                            \"name\": \"Hulk (2008 - 2012)\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 2\n" +
            "                },\n" +
            "                \"stories\": {\n" +
            "                    \"available\": 7,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/stories\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/92078\",\n" +
            "                            \"name\": \"Hulk (2008) #55\",\n" +
            "                            \"type\": \"cover\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 1\n" +
            "                },\n" +
            "                \"events\": {\n" +
            "                    \"available\": 0,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/characters/1017100/events\",\n" +
            "                    \"items\": [],\n" +
            "                    \"returned\": 0\n" +
            "                },\n" +
            "                \"urls\": [\n" +
            "                    {\n" +
            "                        \"type\": \"detail\",\n" +
            "                        \"url\": \"http://marvel.com/characters/76/a-bomb?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"type\": \"comiclink\",\n" +
            "                        \"url\": \"http://marvel.com/comics/characters/1017100/a-bomb_has?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    }\n" +
            "                ]\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}"

    private val mockComicsApiResponseJson = "{\n" +
            "    \"code\": 200,\n" +
            "    \"status\": \"Ok\",\n" +
            "    \"copyright\": \"© 2020 MARVEL\",\n" +
            "    \"attributionText\": \"Data provided by Marvel. © 2020 MARVEL\",\n" +
            "    \"attributionHTML\": \"<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2020 MARVEL</a>\",\n" +
            "    \"etag\": \"0eb6aa510191703cab1c1f273957164f1b13766a\",\n" +
            "    \"data\": {\n" +
            "        \"offset\": 0,\n" +
            "        \"limit\": 10,\n" +
            "        \"total\": 1,\n" +
            "        \"count\": 1,\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"id\": 21366,\n" +
            "                \"digitalId\": 10715,\n" +
            "                \"title\": \"Avengers: The Initiative (2007) #14\",\n" +
            "                \"issueNumber\": 14,\n" +
            "                \"variantDescription\": \"\",\n" +
            "                \"description\": \"The fates of The Initiative, the United States\",\n" +
            "                \"modified\": \"2014-08-05T14:09:26-0400\",\n" +
            "                \"isbn\": \"\",\n" +
            "                \"upc\": \"5960606084-01411\",\n" +
            "                \"diamondCode\": \"APR082297\",\n" +
            "                \"ean\": \"\",\n" +
            "                \"issn\": \"\",\n" +
            "                \"format\": \"Comic\",\n" +
            "                \"pageCount\": 32,\n" +
            "                \"textObjects\": [\n" +
            "                    {\n" +
            "                        \"type\": \"issue_preview_text\",\n" +
            "                        \"language\": \"en-us\",\n" +
            "                        \"text\": \"The fates of The Initiative, the United States, and Planet Earth hang in the balance. Plus: Former Avenger, Delroy Garret, assumes the mantle and arsenal of Earth's greatest Skrull-Hunter, The 3-D Man.\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"type\": \"issue_solicit_text\",\n" +
            "                        \"language\": \"en-us\",\n" +
            "                        \"text\": \"SECRET INVASION TIE-IN\\r<br>\\\"We Have Met the Enemy and He Is Us!\\\"\\r<br>During the INFILTRATION, a Skrull at the heart of the Camp Hammond said these words: \\\"It won't be long until we have a Skrull in every state! \\\"Now that Skrull stands revealed and the fate of The Initiative, the United States, and Planet Earth hang in the balance. Plus:  Former Avenger, Delroy Garret, assumes the mantle and arsenal of Earth's greatest Skrull-Hunter, THE 3-D MAN.  He's here to chew bubblegum and kick some Skrull-@\$\$.  And he's all out of bubblegum.\\r<br>Rated T+ ...\$2.99 \\r<br>\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/21366\",\n" +
            "                \"urls\": [\n" +
            "                    {\n" +
            "                        \"type\": \"detail\",\n" +
            "                        \"url\": \"http://marvel.com/comics/issue/21366/avengers_the_initiative_2007_14?utm_campaign=apiRef&utm_source=2f8c72d3769c9199dbe5372409bc0da2\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"series\": {\n" +
            "                    \"resourceURI\": \"http://gateway.marvel.com/v1/public/series/1945\",\n" +
            "                    \"name\": \"Avengers: The Initiative (2007 - 2010)\"\n" +
            "                },\n" +
            "                \"variants\": [\n" +
            "                    {\n" +
            "                        \"resourceURI\": \"http://gateway.marvel.com/v1/public/comics/24571\",\n" +
            "                        \"name\": \"Avengers: The Initiative (2007) #14 (SPOTLIGHT VARIANT)\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"collections\": [],\n" +
            "                \"collectedIssues\": [],\n" +
            "                \"dates\": [\n" +
            "                    {\n" +
            "                        \"type\": \"onsaleDate\",\n" +
            "                        \"date\": \"2008-06-25T00:00:00-0400\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"prices\": [\n" +
            "                    {\n" +
            "                        \"type\": \"printPrice\",\n" +
            "                        \"price\": 2.99\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"type\": \"digitalPurchasePrice\",\n" +
            "                        \"price\": 1.99\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"thumbnail\": {\n" +
            "                    \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/60/58dbce634ea70\",\n" +
            "                    \"extension\": \"jpg\"\n" +
            "                },\n" +
            "                \"images\": [\n" +
            "                    {\n" +
            "                        \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/c/60/58dbce634ea70\",\n" +
            "                        \"extension\": \"jpg\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"path\": \"http://i.annihil.us/u/prod/marvel/i/mg/3/30/4bb7c84053318\",\n" +
            "                        \"extension\": \"jpg\"\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"creators\": {\n" +
            "                    \"available\": 6,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/21366/creators\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/creators/2133\",\n" +
            "                            \"name\": \"Tom Brevoort\",\n" +
            "                            \"role\": \"editor\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 1\n" +
            "                },\n" +
            "                \"characters\": {\n" +
            "                    \"available\": 8,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/21366/characters\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1011334\",\n" +
            "                            \"name\": \"3-D Man\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/characters/1010802\",\n" +
            "                            \"name\": \"Ant-Man (Eric O'Grady)\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 2\n" +
            "                },\n" +
            "                \"stories\": {\n" +
            "                    \"available\": 2,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/21366/stories\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/stories/47184\",\n" +
            "                            \"name\": \"AVENGERS: THE INITIATIVE (2007) #14\",\n" +
            "                            \"type\": \"cover\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 1\n" +
            "                },\n" +
            "                \"events\": {\n" +
            "                    \"available\": 1,\n" +
            "                    \"collectionURI\": \"http://gateway.marvel.com/v1/public/comics/21366/events\",\n" +
            "                    \"items\": [\n" +
            "                        {\n" +
            "                            \"resourceURI\": \"http://gateway.marvel.com/v1/public/events/269\",\n" +
            "                            \"name\": \"Secret Invasion\"\n" +
            "                        }\n" +
            "                    ],\n" +
            "                    \"returned\": 1\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}"
}