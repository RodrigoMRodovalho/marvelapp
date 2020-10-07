package br.com.rrodovalho.marvelapp.main.features.characterlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.base.loadImage
import br.com.rrodovalho.marvelapp.main.model.ViewCharacter
import kotlinx.android.synthetic.main.character_info_item.view.*

class CharacterInfoRecyclerViewAdapter(private val context: Context,
                                       var characterInfoList: MutableList<ViewCharacter>,
                                       private val listener: (Int, ViewCharacter) -> Unit):
    RecyclerView.Adapter<CharacterInfoRecyclerViewAdapter.CharacterInfoRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterInfoRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_info_item, parent, false)
        return CharacterInfoRecyclerViewHolder(context, view)
    }

    fun updateList(list: MutableList<ViewCharacter>) {
        val lastListSize = characterInfoList.size
        val newItemCount = list.size
        characterInfoList.addAll(list)
        notifyItemRangeInserted(lastListSize, newItemCount)
    }

    override fun getItemCount(): Int = characterInfoList.size

    override fun onBindViewHolder(holder: CharacterInfoRecyclerViewHolder, position: Int) {
        holder.bindView(characterInfoList[position], position, listener)
    }

    class CharacterInfoRecyclerViewHolder(private val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(character: ViewCharacter, position: Int, listener: (Int, ViewCharacter) -> Unit){
            itemView.characterNametextView.text = character.name
            if (character.description.isBlank()){
                itemView.characterDescriptionTextView.text =
                    context.getString(R.string.no_character_description_available)
            } else {
                itemView.characterDescriptionTextView.text = character.description
            }
            itemView.characterImageView.loadImage(character.imageUrl, R.drawable.marvel_logo)

            itemView.setOnClickListener {
                listener(position, character)
            }
        }

    }
}