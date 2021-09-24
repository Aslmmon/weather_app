package com.test.utils.Common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.CountDownTimer
import android.view.*
import android.widget.*
import com.test.utils.Ext.isTimeWith_in_Interval
import com.test.utils.R
import kotlinx.android.synthetic.main.verification_dialog.view.*
import java.text.SimpleDateFormat
import java.util.*


class CustomDialog {
    var cTimer: CountDownTimer? = null
    var dialog: Dialog? = null

    fun showDialog(activity: Activity?, s: String) {
        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity?.baseContext).inflate(R.layout.error_dialog, null)
        dialog?.setContentView(view!!)
        view.findViewById<TextView>(R.id.tv_error).text = s

//        view.findViewById<ImageView>(R.id.tv_delete_).setOnClickListener {
//            dialog?.dismiss()
//        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
    }




    @SuppressLint("SimpleDateFormat")
    fun showDateTimePicker(activity: Activity?, function: (String) -> Unit) {

        val calndar = Calendar.getInstance()

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view =
            LayoutInflater.from(activity.baseContext).inflate(R.layout.date_time_picker, null)
        val viewDAte = view.findViewById<EditText>(R.id.ed_year)
        dialog?.setContentView(view!!)



        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }

        view.findViewById<EditText>(R.id.ed_time).setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calndar.set(Calendar.HOUR_OF_DAY, hour)
                calndar.set(Calendar.MINUTE, minute)
                val neededData = SimpleDateFormat("HH:mm", Locale.ENGLISH).format(calndar.time)
                view.findViewById<EditText>(R.id.ed_time).setText(neededData)
                //  activity.isTimeWith_in_Interval(neededData)
            }
            val time = TimePickerDialog(
                activity,
                R.style.DialogTheme,
                timeSetListener,
                calndar.get(Calendar.HOUR_OF_DAY),
                calndar.get(Calendar.MINUTE),
                false
            )
            time.show()
            time.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(activity.resources.getColor(R.color.teaBlue));
            time.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(activity.resources.getColor(R.color.teaBlue));

        }


        view.findViewById<EditText>(R.id.ed_year).setOnClickListener {

            // create an OnDateSetListener
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    calndar.set(Calendar.YEAR, year)
                    calndar.set(Calendar.MONTH, monthOfYear)
                    calndar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val myFormat = "dd/MM/yyyy" // mention the format you need
                    val sdf = SimpleDateFormat(myFormat, Locale.ENGLISH)


                    viewDAte.setText(sdf.format(calndar.time))
                }

            val datePicker = DatePickerDialog(
                activity, R.style.DialogTheme,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                calndar.get(Calendar.YEAR),
                calndar.get(Calendar.MONTH),
                calndar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.datePicker.minDate = System.currentTimeMillis() - 1000;

            datePicker.show()
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(activity.resources.getColor(R.color.teaBlue));
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(activity.resources.getColor(R.color.teaBlue));


        }

        view.findViewById<Button>(R.id.button_delete).setOnClickListener {

            val backEndFormat = "yyyy-MM-dd" // mention the format you need
            val sdf2 = SimpleDateFormat(backEndFormat, Locale.ENGLISH)
            val dateToBeSend = sdf2.format(calndar.time)
            val timeToBeSend = view.findViewById<EditText>(R.id.ed_time).text.toString()

            if (timeToBeSend.isEmpty() || viewDAte.text.toString().isEmpty()) {
                Toast.makeText(
                    activity,
                    activity.resources.getString(R.string.enter_missing),
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            activity.isTimeWith_in_Interval("$dateToBeSend $timeToBeSend")
            function("$dateToBeSend $timeToBeSend")
            dialog?.dismiss()


        }
        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)





        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun dismissAnyDialog() {
        dialog?.dismiss()
    }


}