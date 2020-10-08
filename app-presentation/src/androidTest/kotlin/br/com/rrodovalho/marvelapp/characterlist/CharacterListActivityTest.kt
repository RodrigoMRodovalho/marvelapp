package br.com.rrodovalho.marvelapp.characterlist

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.BundleMatchers.hasKey
import androidx.test.espresso.intent.matcher.BundleMatchers.hasValue
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtras
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import br.com.rrodovalho.domain.model.CharacterDetail
import br.com.rrodovalho.domain.model.Resource
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.features.characterdetail.CharacterDetailActivity
import br.com.rrodovalho.marvelapp.main.features.characterlist.CharacterListActivity
import br.com.rrodovalho.marvelapp.main.features.di.CHARACTER_BUNDLE_NAVIGATION_KEY
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.mapper.transformTo
import br.com.rrodovalho.marvelapp.util.matcher.RecyclerViewTestUtils.withRecyclerView
import br.com.rrodovalho.marvelapp.util.mocks.MockCharacterDetailViewModel
import br.com.rrodovalho.marvelapp.util.mocks.MockCharacterListViewModel
import br.com.rrodovalho.marvelapp.util.mocks.mockedUseCaseModule
import br.com.rrodovalho.marvelapp.util.runner.MarvelApplicationTestApplication
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class CharacterListActivityTest: KoinTest {

    lateinit var scenario : ActivityScenario<CharacterListActivity>

    @Before
    fun before(){
        val application =
            InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MarvelApplicationTestApplication
        application.injectModule(mockedUseCaseModule)
    }

    @Test
    fun characterListActivity_WhenSelectingFirstRow_ShouldGoToCharacterDetailScreen(){

        Intents.init()

        val selectedViewCharacter = ViewCharacter(
            "1011334",
            "3-D Man",
            "",
            "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg",
            listOf()
        )

        val unselectedViewCharacter =  ViewCharacter(
            "1017100", "A-Bomb (HAS)",
            "Rick Jones has been Hulk's best bud since day one, but now he's more than",
            "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16.jpg", listOf()
        )

        MockCharacterListViewModel.resource = Resource.success(
            listOf(selectedViewCharacter, unselectedViewCharacter)
        )

        MockCharacterDetailViewModel.resource =
            Resource.success(CharacterDetail(transformTo(selectedViewCharacter), listOf()))

        scenario = ActivityScenario.launch(CharacterListActivity::class.java)

        onView(withId(R.id.tryAgainButton)).check(matches(not(isDisplayed())))
        onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))

        onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(0, R.id.characterImageView)
        ).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(0, R.id.characterNametextView)
        ).check(matches(withText(selectedViewCharacter.name)))

        val selectedCharacterViewInteraction = onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(0, R.id.characterDescriptionTextView)
        ).check(matches(withText("No description available")))

        onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(1, R.id.characterImageView)
        ).check(matches(isDisplayed()))

        onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(1, R.id.characterNametextView)
        ).check(matches(withText(unselectedViewCharacter.name)))

        onView(
            withRecyclerView(R.id.recyclerview)
                .atPositionOnView(1, R.id.characterDescriptionTextView)
        ).check(matches(withText(unselectedViewCharacter.description)))

        selectedCharacterViewInteraction.perform(click())

        intended(
            allOf(
                hasComponent(CharacterDetailActivity::class.java.name),
                hasExtras(allOf(hasValue(selectedViewCharacter), hasKey(CHARACTER_BUNDLE_NAVIGATION_KEY)))
            )
        )

        Intents.release()
    }

    @After
    fun after(){
        if(::scenario.isInitialized){
            scenario.close()
        }
    }
}