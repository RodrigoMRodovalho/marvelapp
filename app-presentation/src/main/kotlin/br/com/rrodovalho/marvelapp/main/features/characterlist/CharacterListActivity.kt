package br.com.rrodovalho.marvelapp.main.features.characterlist

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.features.characterlist.adapter.CharacterInfoRecyclerViewAdapter
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

    private fun populateList (characterList: List<Character>) {
        characterInfoRecyclerViewAdapter =
            CharacterInfoRecyclerViewAdapter(this, characterList.toMutableList()) {
                    position, character -> run {

                }
            }
        recyclerview.adapter = characterInfoRecyclerViewAdapter
    }
}