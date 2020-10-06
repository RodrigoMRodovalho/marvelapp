package br.com.rrodovalho.marvelapp.main.features.characterdetail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.base.loadImage
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import kotlinx.android.synthetic.main.activity_character_detail.*
import org.koin.android.ext.android.inject

class CharacterDetailActivity : AppCompatActivity() {

    private val vm : CharacterDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        val character = intent.getParcelableExtra<ViewCharacter>("character")

        character?.let {

            vm.observeData.observe(this, { resource ->
                    if (resource.status == Status.SUCCESS) {
                        val characterDetail = resource.data!!
                        characterDetailNameTextView.text = characterDetail.character.name
                        characterDetailDescriptiontextView.text = characterDetail.character.description
                        characterDetailImageView.loadImage(characterDetail.character.imageUrl)
                        //Toast.makeText(this, "${resource.data?.character?.id}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "${resource.throwable?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            )

            vm.getCharacterDetail(it)
        }


    }
}