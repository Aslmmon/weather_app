package com.test.utils.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.test.utils.R

class CustomCounter(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {
    private var onCounterSelectedApi: CounterSelection? = null
    private var onCounterSelectedLocal: CounterSelectionLocal? = null

    init {
        inflate(context, R.layout.custom_counter, this)
        findViewById<TextView>(R.id.tv_add).setOnClickListener {
            onCounterSelectedApi?.onCounterSelectionApi(getCounter(), TYPE.PLUS.plustFunction())
            onCounterSelectedLocal?.onCounterSelectionLocal(getCounter(), TYPE.PLUS.plustFunction())

        }
        findViewById<TextView>(R.id.tv_minus).setOnClickListener {
            if (getCounter() >= 2) {
                onCounterSelectedApi?.onCounterSelectionApi(
                    getCounter(),
                    TYPE.MINUS.minusFunction()
                )
                onCounterSelectedLocal?.onCounterSelectionLocal(
                    getCounter(),
                    TYPE.MINUS.minusFunction()
                )
            }
        }


    }

    @SuppressLint("SetTextI18n")
    fun setPrice(text: Int) {
        findViewById<TextView>(R.id.tv_price).text = text.toString() + " " + resources.getString(R.string.egp)
    }

    fun setCounterItem(text: Int) {
        findViewById<TextView>(R.id.tv_counter).text = text.toString()
    }



    interface CounterSelection {
        fun onCounterSelectionApi(counter: Int, plus: String)
    }
    interface CounterSelectionLocal{
        fun onCounterSelectionLocal(counter: Int, plus: String)

    }


    @JvmName("getCounter1")
    fun getCounter(): Int {
        return findViewById<TextView>(R.id.tv_counter).text.toString().toInt()
    }


    fun setOnCounterSelectionApi(listener: CounterSelection?) {
        onCounterSelectedApi = listener
    }
    fun setOnCounterSelectionLocal(listener: CounterSelectionLocal) {
        onCounterSelectedLocal = listener
    }



}

sealed class TYPE {
    object PLUS : TYPE() {
        fun plustFunction(): String = "plus"
    }

    object MINUS : TYPE() {
        fun minusFunction(): String = "minus"
    }
}