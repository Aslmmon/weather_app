package com.test.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.utils.R

class LanguagesAdapter(
    private val interaction: OnItemClickOfProduct? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Languages>() {

        override fun areItemsTheSame(oldItem: Languages, newItem: Languages) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Languages, newItem: Languages) =
            oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.language_item,
                parent,
                false
            ),interaction)
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

    fun submitList(list: List<Languages>) {
        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        var interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bind(data: Languages) = with(itemView) {
            findViewById<TextView>(R.id.tv_langugage).text =   data.title
          //  findViewById<TextView>(R.id.tv_langugage).setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,data.flag,0)
            setOnClickListener {
                interaction?.onAddNoteClicked(bindingAdapterPosition,data)
            }


        }



    }


    interface OnItemClickOfProduct {
        fun onAddNoteClicked(position: Int, item: Languages)
    }
}

data class Languages(var id:Int,var flag:Int,var title:String)
