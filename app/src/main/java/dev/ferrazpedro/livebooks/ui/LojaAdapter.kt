package dev.ferrazpedro.livebooks.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.ferrazpedro.livebooks.R
import dev.ferrazpedro.livebooks.api.model.RespostaLivro
import kotlinx.android.synthetic.main.item_card.view.*

class LojaAdapter (val homeFeed: MutableList<RespostaLivro>, private val ClickListener: MVP.btnClickListener): RecyclerView.Adapter<LojaAdapter.CustomViewHolder>(){

    inner class CustomViewHolder(view : View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val homeFeed = homeFeed[position]

        with(holder.itemView) {
            title.text = homeFeed.title
            writer.text = homeFeed.writer
            price.text = homeFeed.price.toString()
            val cover = thumbnailHd
            Picasso.get().load(homeFeed.thumbnailHd).into(cover)
            btnComprar.setOnClickListener{
                ClickListener.onBtnClick(position)
            }

        }
    }

    override fun getItemCount(): Int {

        return homeFeed.size
    }
}