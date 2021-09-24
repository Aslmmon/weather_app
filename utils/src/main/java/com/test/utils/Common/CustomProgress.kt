package com.test.utils.Common

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.test.utils.Ext.loadImage
import com.test.utils.R

class CustomProgress() {

   private var dialog:Dialog? = null

    fun show(context: Context): Dialog {
        return show(context, null)
    }


    fun show(context: Context, title: CharSequence?): Dialog {
        val inflater = (context as Activity).layoutInflater
        val view = inflater.inflate(R.layout.progress_dialog, null)

        dialog = context.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(view)

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
        return dialog!!
    }

    fun dismissDialog() {
        if (dialog?.isShowing == true && dialog != null) dialog?.dismiss()
    }

}