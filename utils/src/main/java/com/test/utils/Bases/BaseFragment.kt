package com.test.utils.Bases

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.utils.Common.CustomDialog
import com.test.utils.Common.CustomProgress
import org.koin.android.ext.android.inject


open class BaseFragment : Fragment() {
    lateinit var loadingDialog: CustomProgress
    var viewNeededToBeHidden: View? = null
    lateinit var customDialog: CustomDialog
    val sharedPrefrence: SharedPreferences by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = CustomProgress()
        customDialog = CustomDialog()
//        sharedPrefrence.getString(LANGUAGE_PREFRENCE, ARABIC)
//            ?.let { langugae -> requireActivity().setLocale(langugae) }


    }


//


}
