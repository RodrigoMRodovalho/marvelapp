package br.com.rrodovalho.marvelapp.main.features.characterlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.rrodovalho.domain.model.Character
import br.com.rrodovalho.marvelapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.characterinfo_item.view.*

class CharacterInfoRecyclerViewAdapter(private val context: Context,
                                         private val characterInfoList: MutableList<Character>,
                                         private val listener: (Int, Character) -> Unit):
    RecyclerView.Adapter<CharacterInfoRecyclerViewAdapter.CharacterInfoRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterInfoRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characterinfo_item, parent, false)
        return CharacterInfoRecyclerViewHolder(context, view)
    }

    //fun getList(): MutableList<Character> = characterInfoList

    override fun getItemCount(): Int = characterInfoList.size

    override fun onBindViewHolder(holder: CharacterInfoRecyclerViewHolder, position: Int) {
        holder.bindView(characterInfoList[position], position, listener)
    }

    class CharacterInfoRecyclerViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(character: Character, position: Int, listener: (Int, Character) -> Unit){
            itemView.characterNametextView.text = character.name
            itemView.characterDescriptiontextView.text = character.description
            itemView.characterImageView.loadImage(character.imageUrl)

            itemView.setOnClickListener {
                listener(position, character)
            }
        }

    }
}

fun ImageView.loadImage(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        //.asBitmap()
        .error(R.drawable.ic_launcher_background)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}