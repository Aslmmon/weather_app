package com.test.utils.Bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.utils.Common.CustomDialog
import com.test.utils.Common.CustomProgress
import com.test.utils.R

open class BaseActivity : AppCompatActivity() {

    lateinit var loadingDialog: CustomProgress
    lateinit var customDialog: CustomDialog

//    val sharedPrefrenceEditor: SharedPreferences.Editor by inject()
//    val sharedPrefrence: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = CustomProgress()
        customDialog = CustomDialog()
        //setLocale(ARABIC)
//        getSharedPrefrenceInstance().getString(LANGUAGE_PREFRENCE, ENGLISH)
//            ?.let { langugae -> setLocale(langugae) }


    }

    fun showProgress() {
        loadingDialog.show(this, resources.getString(R.string.Loading))
    }
//
//    fun getSharedPrefrenceEdit() = sharedPrefrenceEditor
//    fun getSharedPrefrenceInstance() = sharedPrefrence

    fun dismissProgressDialog() {
        loadingDialog.dismissDialog()
    }








}