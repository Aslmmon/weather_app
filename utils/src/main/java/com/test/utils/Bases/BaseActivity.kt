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
        functionNeeded: (((CitiesEntities)?) -> Unit)? = null,
        searchFunctionality: (((CitiesEntities)?) -> Unit)? = null,
        citiis: List<CitiesEntities>,
        isFromSearch: Boolean = false
    ) {
        customDialog.showCitiesDialog(
            this,
            functionNeeded,
            citiis,
            isFromSearch,
            searchFunctionality
        )
    }

    fun showProgress() {
        loadingDialog.show(this, resources.getString(R.string.Loading))
    }

    fun dismissProgressDialog() {
        loadingDialog.dismissDialog()
    }


}