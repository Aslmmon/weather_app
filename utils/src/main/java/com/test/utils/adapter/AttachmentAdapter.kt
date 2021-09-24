package com.test.utils.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.domain.model.checkout.order.order_details.BankTransfer
import com.test.utils.R


class AttachmentAdapter(
    private val interaction: OnItemClickOfProduct? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BankTransfer>() {

        override fun areItemsTheSame(oldItem: BankTransfer, newItem: BankTransfer) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: BankTransfer, newItem: BankTransfer) =
            oldItem == newItem

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.attachment_adapter,
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

    fun submitList(list: List<BankTransfer>) {
        differ.submitList(list)
    }

    class SubjectViewHolder(
        itemView: View,
        var interaction: OnItemClickOfProduct?
    ) : RecyclerView.ViewHolder(itemView){

        @SuppressLint("SetTextI18n")
        fun bind(data: BankTransfer) = with(itemView) {
            findViewById<TextView>(R.id.doc).text = resources.getString(R.string.document) + " " +  data.status
            findViewById<ImageView>(R.id.iv_doc).setOnClickListener {
                if(data.status != resources.getString(R.string.accepted))interaction?.onAddNoteClicked(bindingAdapterPosition,item = data)
            }
            findViewById<TextView>(R.id.doc).setOnClickListener {
                interaction?.onImageClicked(bindingAdapterPosition,data)
            }


        }



    }


    interface OnItemClickOfProduct {
        fun onAddNoteClicked(position: Int, item: BankTransfer)
        fun onImageClicked(position: Int, item: BankTransfer)
    }
}