package br.com.rrodovalho.marvelapp.main.features.characterlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        vm.observeData.observe(this, { resource ->
                if (resource.status == Status.SUCCESS) {
                    //Toast.makeText(this, "${resource.data?.size}", Toast.LENGTH_SHORT).show()
                    populateList(resource.data!!)
                } else {
                    Toast.makeText(this, "${resource.throwable?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        vm.getCharacterList()
    }

    private fun populateList (characterList: List<ViewCharacter>) {
        characterInfoRecyclerViewAdapter =
            CharacterInfoRecyclerViewAdapter(this, characterList.toMutableList()) {
                    _ , character -> run {
                        showCharacterDetailScreen(character)
                }
            }
        recyclerview.adapter = characterInfoRecyclerViewAdapter
    }

    private fun showCharacterDetailScreen(character: ViewCharacter) {
        val intent = Intent(this, CharacterDetailActivity::class.java).apply {
            putExtras(Bundle().also { it.putParcelable("character", character)})
        }
        startActivity(intent)
    }
}