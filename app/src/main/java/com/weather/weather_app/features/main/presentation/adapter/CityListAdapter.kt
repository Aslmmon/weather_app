package com.weather.weather_app.features.main.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded
import com.weather.weather_app.R

class CityListAdapter(private val interaction: OnItemClickOfProduct? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CitiesEntities>() {

        override fun areItemsTheSame(oldItem: CitiesEntities, newItem: CitiesEntities) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CitiesEntities, newItem: CitiesEntities) =
            oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.city_item,
                parent,
                false
            ), interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SubjectViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: CitiesNeeded) {
        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        val interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(data: CitiesEntities) = with(this.itemView) {
            findViewById<TextView>(R.id.tv_cityName).text = data.name
            findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
                interaction?.onDeleteItemClicked(adapterPosition, data)
            }

            setOnClickListener {
                interaction?.onItemClicked(adapterPosition, data)
            }


        }

    }


    interface OnItemClickOfProduct {
        fun onItemClicked(position: Int, item: CitiesEntities)
        fun onDeleteItemClicked(position: Int, item: CitiesEntities)


    }

}

