package com.test.utils.Common

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.CountDownTimer
import android.view.*
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.chaos.view.PinView
import com.floriaapp.core.domain.model.general.bankTransferSettings
import com.floriaapp.core.ui.CheckoutViewModel
import com.floriaapp.core.ui.LoginViewModel
import com.test.utils.BANK
import com.test.utils.Ext.getObject
import com.test.utils.Ext.isTimeWith_in_Interval
import com.test.utils.Ext.showToast
import com.test.utils.R
import com.test.utils.VERIFY_CHANGE_PASSWORD
import com.test.utils.VERIFY_NEW_USER
import com.test.utils.adapter.Languages
import com.test.utils.adapter.LanguagesAdapter
import com.test.utils.custom.CustomTelephone
import com.test.utils.custom.CustomTitleEditBox
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

    fun showAuthenticationDialog(
        activity: Activity?,
        function: () -> Unit,
        goToCartFunction: (() -> Unit?)? = null
    ) {
        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view =
            LayoutInflater.from(activity?.baseContext).inflate(R.layout.authentication_dialog, null)
        dialog?.setContentView(view!!)
        view.findViewById<TextView>(R.id.tv_error).text =
            activity.resources.getString(R.string.signIn_first)

        view.findViewById<Button>(R.id.btn_add_to_cart).setOnClickListener {
            dialog?.dismiss()
            if (goToCartFunction != null) {
                goToCartFunction()
            }
        }

        view.findViewById<Button>(R.id.btn_buy).setOnClickListener {
            dialog?.dismiss()
            function()

        }

        val window: Window? = dialog?.window
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showVerificationDialog(
        activity: Activity?,
        loginViewModel: LoginViewModel,
        typeOfApi: Int? = null,
        phoneNumber: String? = null,
        function: ((String) -> Unit?)? = null,
        view: LifecycleOwner? = null
    ) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view =
            LayoutInflater.from(activity.baseContext).inflate(R.layout.verification_dialog, null)
        dialog?.setContentView(view!!)
        startTimer(
            view.findViewById(R.id.tv_timer),
            activity,
            view.findViewById(R.id.tv_resend_verification)
        )
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)




        view.findViewById<Button>(R.id.btn_confirm).setOnClickListener {
            val code = view.findViewById<PinView>(R.id.pin_code).pin_code.text.toString()
            when (typeOfApi) {
                VERIFY_NEW_USER -> loginViewModel.verifyPhone(code)
                VERIFY_CHANGE_PASSWORD -> loginViewModel.confirmResetPassword(
                    phone = phoneNumber!!,
                    code = code
                )
            }
            dialog?.dismiss()
            function?.invoke(code)
        }


        view.findViewById<TextView>(R.id.tv_resend_verification).setOnClickListener {
            view.findViewById<PinView>(R.id.pin_code).pin_code.setText("")
            when (typeOfApi) {
                VERIFY_NEW_USER -> loginViewModel.resendVerification()
                VERIFY_CHANGE_PASSWORD -> loginViewModel.forgetPassword(phone = phoneNumber!!)
            }
            // loginViewModel.resendVerification()
            dialog?.dismiss()
        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(false)
        dialog?.show()
        dialog?.setOnDismissListener {
            cancelTimer()
        }


    }

    fun startTimer(timerText: TextView, context: Activity, textToSendVerification: TextView) {
        textToSendVerification.visibility = View.INVISIBLE
        cTimer = object : CountDownTimer(60000, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                timerText.text =
                    context.resources.getString(R.string.timer_title) + " ${millisUntilFinished / 1000} " + context.resources.getString(
                        R.string.seconds_title
                    )
            }

            override fun onFinish() {
                textToSendVerification.visibility = View.VISIBLE
            }
        }
        cTimer?.start()
    }

    fun cancelTimer() {
        if (cTimer != null)
            cTimer?.cancel()

        if (dialog?.isShowing == true) {
            dialog?.dismiss()
        }
    }

    fun showDeleteDialog(activity: Activity?, function: () -> Unit) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.delete_dialog, null)
        dialog?.setContentView(view!!)
        view.findViewById<Button>(R.id.button_delete).setOnClickListener {
            function()
            dialog?.dismiss()
        }
        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<ImageView>(R.id.tv_delete_).setOnClickListener {
            dialog?.dismiss()
        }
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)
        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun showSuccessADDdialog(activity: Activity?, function: () -> Unit) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.add_cart_dialog, null)
        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<ConstraintLayout>(R.id.btn_goToCart_all).setOnClickListener {
            dialog?.dismiss()
            function()
        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showSuccessResetPassword(activity: Activity?) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.success_forget, null)
        dialog?.setContentView(view!!)

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun showSuccessChangedPassword(activity: Activity?) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext)
            .inflate(R.layout.success_change_password, null)
        dialog?.setContentView(view!!)

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun showSuccessVerification(activity: Activity?, function: () -> Unit) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext)
            .inflate(R.layout.success_verification, null)
        dialog?.setContentView(view!!)

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)
        dialog?.setOnDismissListener {
            function()
        }

        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showSuccessSent(activity: Activity?) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.success_dialog, null)
        dialog?.setContentView(view!!)

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 10)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showSuccessFavourites(activity: Activity?, successMessage: String) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view =
            LayoutInflater.from(activity.baseContext).inflate(R.layout.success_favourites, null)
        dialog?.setContentView(view!!)
        view.findViewById<TextView>(R.id.tv_success_favourites).text = successMessage

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun showForgetPasswordDialog(
        activity: Activity?,
        loginViewModel: LoginViewModel,
        functionNeeded: (String) -> Unit
    ) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.forget_password, null)
        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<Button>(R.id.btn_send).setOnClickListener {
            val number = view.findViewById<CustomTelephone>(R.id.tv_custom_telephone).getText()
            if (number.trim().isEmpty()) {
                activity.showToast(activity.resources.getString(R.string.enter_missing))
                return@setOnClickListener
            } else {
                dialog?.dismiss()
                functionNeeded(number)
                loginViewModel.forgetPassword(number)
            }
        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }

    fun showNotesDialog(activity: Activity?, function: (String) -> Unit, notesNeeded: String?) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)

        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.notes_dialog, null)
        val text = view.findViewById<EditText>(R.id.ed_notes)

        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        if (notesNeeded == null) text.setText("")
        else text.setText(notesNeeded)
        view.findViewById<Button>(R.id.btn_add).setOnClickListener {
            if (text.toString().trim().isEmpty()) {
                activity.showToast(activity.resources.getString(R.string.enter_notes))
                return@setOnClickListener
            } else function(view.findViewById<EditText>(R.id.ed_notes).text.toString())
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


    fun showChangePassword(activity: Activity?, loginViewModel: LoginViewModel) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.change_password, null)
        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val oldPass = view.findViewById<CustomTitleEditBox>(R.id.custom_password_old).getText()
            val newPass = view.findViewById<CustomTitleEditBox>(R.id.custom_password_new).getText()
            val confirmPass =
                view.findViewById<CustomTitleEditBox>(R.id.custom_password_confirm).getText()

            if (oldPass.trim().isEmpty() || newPass.trim().isEmpty() || confirmPass.trim()
                    .isEmpty()
            ) {
                activity.showToast(activity.resources.getString(R.string.enter_missing))
                return@setOnClickListener
            }

            if (newPass.trim() != confirmPass.trim()
            ) {
                activity.showToast(activity.resources.getString(R.string.confirm_message))
                return@setOnClickListener
            }


            if (newPass.trim().length < 8 || confirmPass.trim().length < 8
            ) {
                activity.showToast(activity.resources.getString(R.string.enter_8_character))
                return@setOnClickListener
            } else {
                loginViewModel.changePassowrd(oldPass, newPass, confirmPass)
                dialog?.dismiss()
            }


        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 10)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showLangages(activity: Activity?, adapter: LanguagesAdapter) {

        dialog = activity?.let { Dialog(it) }!!
        val list = mutableListOf<Languages>(
            Languages(1, R.drawable.ic_united_kingdom, "English"),
            Languages(2, R.drawable.ic_saudi_arabia, "Arabic")

        )
        adapter.submitList(list)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.change_language, null)
        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<RecyclerView>(R.id.rv_langugage).adapter = adapter

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 10)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showResetNewPassword(
        activity: Activity?,
        function: (String, String) -> Unit
    ) {

        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.reset_password, null)
        dialog?.setContentView(view!!)
        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
            dialog?.dismiss()
        }
        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            val newPass = view.findViewById<CustomTitleEditBox>(R.id.custom_password_new).getText()
            val confirmPass =
                view.findViewById<CustomTitleEditBox>(R.id.custom_password_confirm).getText()

            if (newPass.trim().isEmpty() || confirmPass.trim().isEmpty()) {
                activity.showToast(activity.resources.getString(R.string.enter_missing))
                return@setOnClickListener
            } else {
                function(newPass, confirmPass)
                dialog?.dismiss()
            }


        }

        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 10)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    @SuppressLint("SetTextI18n")
    fun showAttachmentReceipt(
        activity: Activity?,
        ImageFunction: () -> Unit,
        launcher: ActivityResultLauncher<Intent>,
        passer: (ImageView) -> Unit
    ) {

        dialog = activity.let { Dialog(it!!) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity?.baseContext).inflate(R.layout.attachment, null)
        dialog?.setContentView(view!!)


        val bankAccount = activity?.getObject(BANK, bankTransferSettings::class.java)

        view.findViewById<TextView>(R.id.tv_bank_number).text =
            activity?.resources?.getString(R.string.account_number) + " " + bankAccount?.accountNumber
        view.findViewById<TextView>(R.id.tv_bank).text = bankAccount?.bank

        view.findViewById<ImageView>(R.id.iv).setOnClickListener {
//            val photoPickerIntent = Intent(Intent.ACTION_PICK)
//            photoPickerIntent.type = "image/*"
//            launcher.launch(photoPickerIntent)
//            passer(view.findViewById<ImageView>(R.id.iv))
        }


        view.findViewById<Button>(R.id.btn_save).setOnClickListener {
            //    ImageFunction()
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            launcher.launch(photoPickerIntent)
            dialog?.dismiss()


        }


        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 10)
        dialog?.window?.setBackgroundDrawable(inset)

        dialog?.setCancelable(true)
        dialog?.show()
    }


    class IntTransformer : () -> String {
        override operator fun invoke(): String = "TODO()"
    }

    fun showBlockedDialog(activity: Activity?, s: String) {
        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.block_dialog, null)
        dialog?.setContentView(view!!)
        view.findViewById<TextView>(R.id.tv_error).text = s
//        view.findViewById<ImageView>(R.id.tv_close).setOnClickListener {
//            dialog?.dismiss()
//        }
        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog?.setCancelable(true)
        dialog?.show()
    }


    fun showRateDialog(activity: Activity?, checkoutViewModel: CheckoutViewModel, orderID: Int) {
        dialog = activity?.let { Dialog(it) }!!
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val view = LayoutInflater.from(activity.baseContext).inflate(R.layout.rate_dialog, null)
        dialog?.setContentView(view!!)


        view.findViewById<Button>(R.id.btn_send_rate).setOnClickListener {
            val commentOrder = view.findViewById<EditText>(R.id.ed_comment_order).text.toString()
            val commentShipping =
                view.findViewById<EditText>(R.id.ed_comment_delivery).text.toString()
            val rateOrder = view.findViewById<RatingBar>(R.id.rate_order)
            val rateShipping = view.findViewById<RatingBar>(R.id.rate_delivery)
            if (rateOrder.rating == 0f || rateShipping.rating == 0f) {
                activity.showToast(activity.resources.getString(R.string.rating))

                return@setOnClickListener


            } else {
                dialog?.dismiss()
                checkoutViewModel.rateOrder(
                    orderID,
                    rateOrder.rating.toInt(),
                    commentOrder,
                    rateShipping.rating.toInt(),
                    commentShipping
                )
            }
        }
        val window: Window? = dialog?.window
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val back = ColorDrawable(Color.TRANSPARENT)
        val inset = InsetDrawable(back, 40)
        dialog?.window?.setBackgroundDrawable(inset)

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
            datePicker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(activity.resources.getColor(R.color.teaBlue));
            datePicker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(activity.resources.getColor(R.color.teaBlue));


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