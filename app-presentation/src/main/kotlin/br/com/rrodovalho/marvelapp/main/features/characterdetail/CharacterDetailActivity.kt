package br.com.rrodovalho.marvelapp.main.features.characterdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.base.loadImage
import br.com.rrodovalho.marvelapp.main.features.characterdetail.adapter.ComicsDetailRecyclerViewAdapter
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import br.com.rrodovalho.marvelapp.main.model.ViewComic
import br.com.rrodovalho.marvelapp.main.model.mapper.transformTo
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.android.ext.android.inject


class CharacterDetailActivity : AppCompatActivity() {

    private val vm : CharacterDetailViewModel by inject()
    lateinit var comicsDetailRecyclerViewAdapter: ComicsDetailRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<ViewCharacter>("character")

        character?.let {

            initComicsList(character.comics)

            characterDetailNameTextView.text = it.name
            characterDetailDescriptiontextView.text = it.description
            characterDetailImageView.loadImage(it.imageUrl, R.drawable.marvel_logo)

            vm.observeData.observe(this, { resource ->
                    if (resource.status == Status.SUCCESS) {
                        val characterDetail = resource.data!!
                        comicsDetailRecyclerViewAdapter.updateList(characterDetail.comicDetail.toMutableList())
                        //Toast.makeText(this, "${resource.data?.character?.id}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "${resource.throwable?.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            )

            Toast.makeText(this, getString(R.string.fetch_comics_message), Toast.LENGTH_SHORT).show()
            vm.getCharacterDetail(it)
        }


    }

    private fun initComicsList(comics: List<ViewComic>) {
        comicsRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        val comicsDetailList = mutableListOf<ComicDetail?>()

        comics.forEach {
            comicsDetailList.add(ComicDetail(transformTo(it), "", ""))
        }

        comicsDetailRecyclerViewAdapter = ComicsDetailRecyclerViewAdapter(this, comicsDetailList)
        comicsRecyclerView.adapter = comicsDetailRecyclerViewAdapter
    }
}