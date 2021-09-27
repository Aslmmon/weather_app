package com.test.utils.Common

import android.app.Activity
import android.app.Dialog
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.floriaapp.core.entity.CitiesEntities
import com.test.utils.R
import com.test.utils.adapters.CitiesListAdapter


class CustomDialog {
    var cTimer: CountDownTimer? = null
    var dialog: Dialog? = null

    fun showDialog(activity: Activity?, s: String) {
//        dialog = activity?.let { Dialog(it) }!!
//        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        val view = LayoutInflater.from(activity?.baseContext).inflate(R.layout.error_dialog, null)
//        dialog?.setContentView(view!!)
//        view.findViewById<TextView>(R.id.tv_error).text = s
//
////        view.findViewById<ImageView>(R.id.tv_delete_).setOnClickListener {
////            dialog?.dismiss()
////        }
//
//        val window: Window? = dialog?.window
//        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        dialog?.setCancelable(true)
//        dialog?.show()
    }

    fun showCitiesDialog(
        activity: Activity,
        AdditionFunctionality: (((CitiesEntities)?) -> Unit)? = null,
        citiesList: List<CitiesEntities>,
        isFromSearch: Boolean = false,
        searchFunctionality: (((CitiesEntities)?) -> Unit)? = null
    ) {
        dialog = Dialog(activity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view =
            LayoutInflater.from(activity.baseContext).inflate(R.layout.dialog_cities_layout, null)
        val searchEditText = view.findViewById<EditText>(R.id.ed_search)
        dialog?.setContentView(view)

        val adapter = CitiesListAdapter(object : CitiesListAdapter.OnItemClickOfProduct {
            override fun onItemClicked(position: Int, item: CitiesEntities) {
                when (isFromSearch) {
                    true -> searchFunctionality?.invoke(item)
                    false -> AdditionFunctionality?.invoke(item)
                }
                dialog?.dismiss()
            }

        })
        if (isFromSearch) {
            searchEditText.visibility = View.VISIBLE
            filterCitiesByQuery(searchEditText, citiesList, adapter,activity)
        }

        view.findViewById<RecyclerView>(R.id.rv_cities).adapter = adapter
        adapter.submitList(citiesList)


        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    private fun filterCitiesByQuery(
        searchEditText: EditText?,
        citiis: List<CitiesEntities>,
        adapter: CitiesListAdapter,
        activity: Activity
    ) {

        searchEditText?.addTextChangedListener { textWritten ->
            val newList = citiis.filter { s -> s.name.contains (textWritten.toString()) }.toMutableList()
            adapter.submitList(newList)
            adapter.notifyDataSetChanged()
        }
    }


    fun dismissAnyDialog() {
        dialog?.dismiss()
    }


}