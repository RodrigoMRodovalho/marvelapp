package br.com.rrodovalho.marvelapp.main.features.characterdetail.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rrodovalho.domain.model.ComicsDetail
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.base.loadImage
import kotlinx.android.synthetic.main.comics_info_item.view.*


class ComicsDetailRecyclerViewAdapter(private val context: Context,
                                      var comicsDetailList: MutableList<ComicsDetail?>):
    RecyclerView.Adapter<ComicsDetailRecyclerViewAdapter.ComicsDetailRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsDetailRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comics_info_item, parent, false)
        return ComicsDetailRecyclerViewHolder(context, view)
    }

    fun updateList(list: MutableList<ComicsDetail?>) {
        comicsDetailList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = comicsDetailList.size

    override fun onBindViewHolder(holder: ComicsDetailRecyclerViewHolder, position: Int) {
        holder.bindView(comicsDetailList[position])
    }

    class ComicsDetailRecyclerViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindView(comicsDetail: ComicsDetail?){

            comicsDetail?.let {
                itemView.comicsTitleTextView.text = comicsDetail.comics.name
                if (comicsDetail.description.isBlank()){
                    itemView.comicsDescriptionTextView.visibility = View.GONE
                } else {
                    itemView.comicsDescriptionTextView.text = comicsDetail.description
                }

                if (comicsDetail.imageUrl.isNotBlank()){
                    itemView.comicsImageView.loadImage(comicsDetail.imageUrl)
                } else {
                    itemView.comicsImageView.background = context.getDrawable(R.drawable.marvel_logo)
                }
            }
        }
    }
}