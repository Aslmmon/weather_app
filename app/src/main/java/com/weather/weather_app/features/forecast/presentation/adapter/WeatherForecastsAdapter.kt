package com.weather.weather_app.features.forecast.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.entity.DateWithData
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.getDayNameFromDate


class WeatherForecastsAdapter(private val interaction: OnItemClickOfProduct? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DateWithData>() {

        override fun areItemsTheSame(oldItem: DateWithData, newItem: DateWithData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: DateWithData, newItem: DateWithData) =
            oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_item,
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

    fun submitList(list: MutableList<DateWithData>) {

        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        val interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n", "CutPasteId")
        fun bind(data: DateWithData) = with(this.itemView) {
            findViewById<TextView>(R.id.tv_date_name).text = itemView.context.getDayNameFromDate(data.date)
            val adapter = WeatherForecastDaysAdapter()

            findViewById<RecyclerView>(R.id.rv_forecasts).adapter = adapter
            findViewById<RecyclerView>(R.id.rv_forecasts).layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            adapter.submitList(data.listNeeded)

        }

    }


    interface OnItemClickOfProduct {
        fun onItemClicked(position: Int, item: String)


    }

}

