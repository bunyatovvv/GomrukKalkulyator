package com.example.gomrukkolkulatoru.presentation.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gomrukkolkulatoru.R
import com.example.gomrukkolkulatoru.common.util.format
import com.example.gomrukkolkulatoru.data.dto.local.FavoritesDTO

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    class FavoritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    private val differCallBack = object : DiffUtil.ItemCallback<FavoritesDTO>() {
        override fun areItemsTheSame(oldItem: FavoritesDTO, newItem: FavoritesDTO): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FavoritesDTO, newItem: FavoritesDTO): Boolean {
            return oldItem.id == newItem.id
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    var favorites: List<FavoritesDTO>
        get() = differ.currentList
        set(value) = differ.submitList(value.toList())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_favorite, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return favorites.size
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val favorite = favorites[position]
        val title = holder.itemView.findViewById<TextView>(R.id.titleText)
        val brand = holder.itemView.findViewById<TextView>(R.id.brandText)
        val model = holder.itemView.findViewById<TextView>(R.id.modelText)
        val issueDate = holder.itemView.findViewById<TextView>(R.id.yearText)
        val engine = holder.itemView.findViewById<TextView>(R.id.engineText)
        val price = holder.itemView.findViewById<TextView>(R.id.priceText)
        val engineType = holder.itemView.findViewById<TextView>(R.id.engineTypeText)
        val total = holder.itemView.findViewById<TextView>(R.id.totalPriceText)

        title.text = favorite.title
        brand.text = favorite.brand
        model.text = favorite.model
        issueDate.text = favorite.year
        engine.text = favorite.engine
        price.text = favorite.price
        engineType.text = favorite.engineType
        total.text = "${favorite.customsPrice.format(2)} AZN"
        holder.itemView.setOnLongClickListener {
            onItemClickListener?.let {
                it(favorite)
            }
            return@setOnLongClickListener true
        }
    }

    private var onItemClickListener: ((FavoritesDTO) -> Unit)? = null
    fun setOnItemClickListener(listener: (FavoritesDTO) -> Unit) {
        onItemClickListener = listener
    }
}