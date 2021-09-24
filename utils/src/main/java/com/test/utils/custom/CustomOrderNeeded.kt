package com.test.utils.custom

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.text.Html
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.domain.model.checkout.order.order_details.BankTransfer
import com.floriaapp.core.domain.model.checkout.order.order_details.OrderDetailsResponse
import com.test.utils.R
import com.test.utils.adapter.AttachmentAdapter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class CustomOrderNeeded(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    lateinit var adapterAttach :AttachmentAdapter

    init {
        inflate(context, R.layout.custom_order_needed, this)

    }
    @SuppressLint("SetTextI18n", "CutPasteId")
    fun  setData(data: OrderDetailsResponse) {
        //val formatEnglish: DateFormat = SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
       // val date: Date = formatEnglish.parse(data.data.createdAt.time)!!
        //Log.i("data",date.time.toString())
       // var date2 = DateTimeFormatter.ofPattern( "hh:mm a" , JOR )  // Pass a `Locale` to specify human language and cultural norms for localization.

        findViewById<TextView>(R.id.order_number).text = "#${data.data.id}  ${"\u200f"}${data.data.requiredAt.time.substringBefore(" ")} "
        findViewById<TextView>(R.id.order_number).append(data.data.requiredAt.date)
       // findViewById<TextView>(R.id.order_payment).text = "( ${data.data.paymentOption.name}) "
        findViewById<TextView>(R.id.order_payment).append(Html.fromHtml("<font color='#0199B1'>${data.data.paymentOption.status} </font "))
        data.data.paymentOptions.forEachIndexed { index, paymentOptions ->
            findViewById<TextView>(R.id.order_payment).append(Html.fromHtml("<font color='#682300'>${paymentOptions.name} </font>"))
        }
        findViewById<TextView>(R.id.order_location).text = "${data.data.address.country.name} - ${data.data.address.region.name} - ${data.data.address.district.name} - ${data.data.address.specialMarque}"
        findViewById<TextView>(R.id.order_shipment).text = data.data.shipping.name
        if (data.data.bankTransfer.isEmpty()){
            findViewById<RecyclerView>(R.id.rv).visibility =View.GONE

        }else{
            findViewById<RecyclerView>(R.id.rv).adapter = adapterAttach
            val list = mutableListOf<BankTransfer>()
            if (data.data.bankTransfer.size >1) {
                list.add(data.data.bankTransfer.get(data.data.bankTransfer.size - 1))
            }else list.addAll(data.data.bankTransfer)
            adapterAttach.submitList(list)
            findViewById<RecyclerView>(R.id.rv).visibility =View.VISIBLE

        }

    }
    fun setAdapter(adapter: AttachmentAdapter){
        adapterAttach = adapter
    }


}