package dev.ferrazpedro.livebooks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.ferrazpedro.livebooks.api.LivrosComprados
import kotlinx.android.synthetic.main.item_card.view.*

class MainAdapter (private val mainFeed: LivrosComprados.Companion) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_card, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mainFeed = mainFeed.livrosComprados[position]

        with(holder.itemView) {
            title.text = mainFeed.title
            writer.text = mainFeed.writer
            val cover = thumbnailHd
            Picasso.get().load(mainFeed.thumbnailHd).into(cover)
        }
    }

    override fun getItemCount(): Int {
        return mainFeed.livrosComprados.size
    }

}