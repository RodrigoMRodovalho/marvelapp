package br.com.rrodovalho.marvelapp.characterdetail

import android.content.Intent
import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import br.com.rrodovalho.domain.model.*
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.features.characterdetail.CharacterDetailActivity
import br.com.rrodovalho.marvelapp.main.features.di.CHARACTER_BUNDLE_NAVIGATION_KEY
import br.com.rrodovalho.marvelapp.main.model.mapper.transformTo
import br.com.rrodovalho.marvelapp.util.matcher.RecyclerViewTestUtils.withRecyclerView
import br.com.rrodovalho.marvelapp.util.mocks.MockCharacterDetailViewModel
import br.com.rrodovalho.marvelapp.util.mocks.mockedUseCaseModule
import br.com.rrodovalho.marvelapp.util.runner.MarvelApplicationTestApplication
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class CharacterDetailActivityTest: KoinTest {

    lateinit var scenario : ActivityScenario<CharacterDetailActivity>

    @Before
    fun before(){
        val application =
            InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MarvelApplicationTestApplication
        application.injectModule(mockedUseCaseModule)
    }

    @Test
    fun characterDetailActivity_WhenShowCharacterDetail_ShouldPresentCharacterAndComicsInfo(){

        val selectedCharacter = Character(
            "1011334",
            "3-D Man",
            "",
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
            listOf(
                Comic("comic1", "name1", "resource1"),
                Comic("comic2", "name2", "resource2"),
            )
        )

        val comicsDetailList = listOf(
            ComicDetail(selectedCharacter.comics[0], "description1", "imageUrl"),
            ComicDetail(selectedCharacter.comics[1], "description2", "imageUrl2"),
        )

        MockCharacterDetailViewModel.resource =
            Resource.success(CharacterDetail(selectedCharacter, comicsDetailList))

        val context = InstrumentationRegistry.getInstrumentation()
            .targetContext

        val intent = Intent(context, CharacterDetailActivity::class.java).apply {
            putExtras(Bundle().also { it.putParcelable(CHARACTER_BUNDLE_NAVIGATION_KEY, transformTo(selectedCharacter)) })
        }
        scenario = ActivityScenario.launch(intent)

        Thread.sleep(3000)

        onView(withId(R.id.characterDetailImageView))
            .check(matches(isDisplayed()))
        onView(withId(R.id.characterDetailNameTextView))
            .check(matches(withText(selectedCharacter.name)))
        onView(withId(R.id.characterDetailDescriptionTextView))
            .check(matches(withText(selectedCharacter.description)))

        val comicsListText = context.getString(R.string.comics_reference, comicsDetailList.size)

        onView(withId(R.id.comicsListReferenceTextView))
            .check(matches(withText(comicsListText)))


        onView(
            withRecyclerView(R.id.comicsRecyclerView)
                .atPositionOnView(0, R.id.comicsImageView)
        ).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.comicsRecyclerView)
                .atPositionOnView(0, R.id.comicsTitleTextView)
        ).check(matches(withText(comicsDetailList[0].comic.name)))

        onView(
            withRecyclerView(R.id.comicsRecyclerView)
                .atPositionOnView(0, R.id.comicsDescriptionTextView)
        ).check(matches(withText(comicsDetailList[0].description)))

        onView(
            withRecyclerView(R.id.comicsRecyclerView)
                .atPositionOnView(1, R.id.comicsTitleTextView)
        ).check(matches(withText(comicsDetailList[1].comic.name)))

        onView(
            withRecyclerView(R.id.comicsRecyclerView)
                .atPositionOnView(1, R.id.comicsDescriptionTextView)
        ).check(matches(withText(comicsDetailList[1].description)))
    }

    @After
    fun after(){
        if(::scenario.isInitialized){
            scenario.close()
        }
    }
}