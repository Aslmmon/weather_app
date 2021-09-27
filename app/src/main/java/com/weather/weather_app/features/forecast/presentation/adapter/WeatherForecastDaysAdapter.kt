package com.weather.weather_app.features.forecast.presentation.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.entity.ListData
import com.weather.weather_app.R
import com.weather.weather_app.common.Ext.getDayNameFromDate
import com.weather.weather_app.common.Ext.getTimeAmPmFromDate
import com.weather.weather_app.common.Ext.loadImage

class WeatherForecastDaysAdapter(private val interaction: OnItemClickOfProduct? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListData>() {

        override fun areItemsTheSame(oldItem: ListData, newItem: ListData) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ListData, newItem: ListData) =
            oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.forecast_item_days,
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

    fun submitList(list: List<ListData>) {

        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        val interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView) {

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SetTextI18n")
        fun bind(data: ListData) = with(this.itemView) {
            Log.i("date", data.dtTxt.toString())
            findViewById<ImageView>(R.id.iv_icon).loadImage("https://openweathermap.org/img/w/${data.weather[0].icon}.png")
            findViewById<TextView>(R.id.tv_date).text =
                itemView.context.getDayNameFromDate(data.dtTxt)
            findViewById<TextView>(R.id.tv_temp_time).text = "at ${itemView.context.getTimeAmPmFromDate(data.dtTxt.substringAfter(" "))}"

            findViewById<TextView>(R.id.tv_temp).text = "${data.main.temp} \u2103 "
            findViewById<TextView>(R.id.tv_type_weather).text = data.weather[0].main
            findViewById<TextView>(R.id.tv_feels_like).text =
                "${resources.getString(R.string.feels_like_title)} ${data.main.feelsLike} \u2103 "


        }

    }


    interface OnItemClickOfProduct {
        fun onItemClicked(position: Int, item: String)


    }

}

