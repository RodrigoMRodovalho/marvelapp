package br.com.rrodovalho.marvelapp.main.features.characterlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.features.characterdetail.CharacterDetailActivity
import br.com.rrodovalho.marvelapp.main.features.characterlist.adapter.CharacterInfoRecyclerViewAdapter
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import kotlinx.android.synthetic.main.activity_character_list.*
import org.koin.android.ext.android.inject


class CharacterListActivity : AppCompatActivity() {

    private val vm : CharacterListViewModel by inject()
    lateinit var characterInfoRecyclerViewAdapter: CharacterInfoRecyclerViewAdapter
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        initScrollListener()
        initAdapter()

        vm.observeData.observe(this, { resource ->
                if (resource.status == Status.SUCCESS) {
                    isLoading = false
                    //Toast.makeText(this, "${resource.data?.size}", Toast.LENGTH_SHORT).show()
                    populateList(resource.data!!)
                } else {
                    Toast.makeText(this, "${resource.throwable?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        fetchData()
    }

    private fun fetchData(isFetchingMoreData: Boolean = false){
        val message = if (isFetchingMoreData){
            getString(R.string.fetch_more_characters_message)
        } else {
            getString(R.string.fetch_characters_message)
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        vm.getCharacterList()
    }

    private fun initAdapter() {
        characterInfoRecyclerViewAdapter = CharacterInfoRecyclerViewAdapter(this, mutableListOf()){
                _, character -> run {
                    showCharacterDetailScreen(character)
            }
        }
        recyclerview.adapter = characterInfoRecyclerViewAdapter
    }

    private fun populateList(characterList: List<ViewCharacter>) {
        characterInfoRecyclerViewAdapter.updateList(characterList.toMutableList())
    }

    private fun showCharacterDetailScreen(character: ViewCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtras(Bundle().also { it.putParcelable("character", character) })
        }
        startActivity(intent)
    }

    private fun initScrollListener() {
        recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null
                        && linearLayoutManager.findLastCompletelyVisibleItemPosition()
                        == characterInfoRecyclerViewAdapter.characterInfoList.size - 1) {
                        //bottom of list!
                        fetchData(isFetchingMoreData = true)
                        isLoading = true
                    }
                }
            }
        })
    }
}