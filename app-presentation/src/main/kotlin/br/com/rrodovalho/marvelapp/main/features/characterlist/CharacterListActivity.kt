package br.com.rrodovalho.marvelapp.main.features.characterlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import br.com.rrodovalho.domain.model.Status
import br.com.rrodovalho.marvelapp.R
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject

class CharacterListActivity : AppCompatActivity() {

    private val vm : CharacterListViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm.observeData.observe(this,
            Observer { resource ->
                if (resource.status == Status.SUCCESS) {
                    Toast.makeText(this, "${resource.data?.size}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "${resource.throwable?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        )

        vm.getCharacterList()
    }
}