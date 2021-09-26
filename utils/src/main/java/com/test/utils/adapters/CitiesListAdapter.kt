package com.test.utils.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.entity.CitiesEntities
import com.test.utils.R

class CitiesListAdapter(private val interaction: OnItemClickOfProduct? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CitiesEntities>() {

        override fun areItemsTheSame(oldItem: CitiesEntities, newItem: CitiesEntities) = oldItem.cityEntityId == newItem.cityEntityId

        override fun areContentsTheSame(oldItem: CitiesEntities, newItem: CitiesEntities) = oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cities_item,
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

    fun submitList(list: List<CitiesEntities>) {
        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        val interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(data: CitiesEntities) = with(this.itemView) {
            findViewById<TextView>(R.id.tv_cityName).text = data.name
            setOnClickListener {
                interaction?.onItemClicked(adapterPosition, data)
            }


        }

    }


    interface OnItemClickOfProduct {
        fun onItemClicked(position: Int, item: CitiesEntities)


    }

}
data class Test(var id: Int, var name: String)

