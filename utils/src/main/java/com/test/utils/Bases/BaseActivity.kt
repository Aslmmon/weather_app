package com.test.utils.Bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.floriaapp.core.entity.CitiesEntities
import com.test.utils.Common.CustomDialog
import com.test.utils.Common.CustomProgress
import com.test.utils.R

open class BaseActivity : AppCompatActivity() {

    lateinit var loadingDialog: CustomProgress
    lateinit var customDialog: CustomDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = CustomProgress()
        customDialog = CustomDialog()

    }

    fun showCitiesDialog(
        functionNeeded: (CitiesEntities) -> Unit,
        citiis: List<CitiesEntities>
    ) {
        customDialog.showCitiesDialog(this, functionNeeded, citiis)
    }

    fun showProgress() {
        loadingDialog.show(this, resources.getString(R.string.Loading))
    }

    fun dismissProgressDialog() {
        loadingDialog.dismissDialog()
    }


}