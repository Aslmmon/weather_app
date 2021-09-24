package com.test.utils.Common

import android.app.Activity
import android.app.Dialog
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import com.test.utils.R


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


    fun dismissAnyDialog() {
        dialog?.dismiss()
    }


}