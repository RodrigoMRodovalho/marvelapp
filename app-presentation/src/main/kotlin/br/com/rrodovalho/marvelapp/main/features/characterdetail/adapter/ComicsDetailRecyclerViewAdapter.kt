package br.com.rrodovalho.marvelapp.main.features.characterdetail.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rrodovalho.domain.model.ComicDetail
import br.com.rrodovalho.marvelapp.R
import br.com.rrodovalho.marvelapp.main.base.loadImage
import kotlinx.android.synthetic.main.comics_info_item.view.*


class ComicsDetailRecyclerViewAdapter(private val context: Context,
                                      var comicDetailList: MutableList<ComicDetail?>):
    RecyclerView.Adapter<ComicsDetailRecyclerViewAdapter.ComicsDetailRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsDetailRecyclerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comics_info_item, parent, false)
        return ComicsDetailRecyclerViewHolder(context, view)
    }

    fun updateList(list: MutableList<ComicDetail?>) {
        comicDetailList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = comicDetailList.size

    override fun onBindViewHolder(holder: ComicsDetailRecyclerViewHolder, position: Int) {
        holder.bindView(comicDetailList[position])
    }

    class ComicsDetailRecyclerViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bindView(comicDetail: ComicDetail?){

            comicDetail?.let {
                itemView.comicsTitleTextView.text = comicDetail.comic.name
                if (comicDetail.description.isNullOrBlank().not()){
                    itemView.comicsDescriptionTextView.visibility = View.VISIBLE
                    itemView.comicsDescriptionTextView.text = comicDetail.description
                }

                if (comicDetail.imageUrl.isNotBlank()){
                    itemView.comicsImageView.setImageResource(android.R.color.transparent)
                    itemView.comicsImageView.loadImage(comicDetail.imageUrl, R.drawable.marvel_logo)
                } else {
                    itemView.comicsImageView.background = context.getDrawable(R.drawable.marvel_logo)
                }
            }
        }
    }
}