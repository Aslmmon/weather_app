package com.test.utils.Bases

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.floriaapp.core.ui.LoginViewModel
import com.test.utils.ARABIC
import com.test.utils.Common.CustomDialog
import com.test.utils.Common.CustomProgress
import com.test.utils.Ext.setLocale
import com.test.utils.LANGUAGE_PREFRENCE
import com.test.utils.R
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
        sharedPrefrence.getString(LANGUAGE_PREFRENCE, ARABIC)?.let { langugae -> requireActivity().setLocale(langugae) }


    }

    fun showProgress(viewToBeHidden: View? = null) {
        loadingDialog.show(requireContext(), resources.getString(R.string.Loading))
        Log.i("progress", "called once")
        if (viewToBeHidden != null) viewNeededToBeHidden = viewToBeHidden
    }


    fun showSuccessResetPassword(){
        customDialog.showSuccessResetPassword(requireActivity())
    }
    fun showSuccessFavouriteMessage(message: String) {
        customDialog.showSuccessFavourites(requireActivity(),message)
    }
    fun showSuccessVerification(function: () -> Unit) {
        customDialog.showSuccessVerification(requireActivity(),function)

    }



    fun showSuccessChangePassword(){
        customDialog.showSuccessChangedPassword(requireActivity())
    }
    fun dismissProgressDialog() {
        loadingDialog.dismissDialog()
        if (viewNeededToBeHidden != null) viewNeededToBeHidden?.visibility = View.VISIBLE
    }

    fun showErrorDialog(message: String) {
        customDialog.showDialog(requireActivity(), message)

    }
    fun showAuthenticationDialog(function: () -> Unit,goToCartFunction : (() -> Unit)?=null) {
        customDialog.showAuthenticationDialog(requireActivity(),function,goToCartFunction)

    }


    fun showForgetPasswordDialog(loginViewModel: LoginViewModel, function: (String) -> Unit) {
        customDialog.showForgetPasswordDialog(requireActivity(), loginViewModel, function)
    }

    fun showBlockDialg(message: String) {
        customDialog.showBlockedDialog(requireActivity(), message)

    }

    fun showVerificationDialog(
        loginViewModel: LoginViewModel,
        typeOfApi: Int,
        phoneNumber: String? = null,
        view:LifecycleOwner?=null,
        function: (String) -> Unit
    ) {
        customDialog.showVerificationDialog(
            requireActivity(),
            loginViewModel,
            typeOfApi,
            phoneNumber,
            function,view
        )

    }
    fun dismissAnyDialog() {
        customDialog.dismissAnyDialog()

    }


    fun showSuccessDialog(function: () -> Unit) {
        customDialog.showSuccessADDdialog(requireActivity(), function)

    }

    fun showResetPassoword(function: (String, String) -> Unit){
        customDialog.showResetNewPassword(requireActivity(),function)
    }

    fun showNotesDialog( notesNeeded:String?,function: (String) ->Unit) {
        customDialog.showNotesDialog(requireActivity(), function,notesNeeded)

    }

    fun showDateTimePicker(function: (String) -> Unit) {
        customDialog.showDateTimePicker(requireActivity(), function)

    }

    fun clearAll() {
        customDialog.cancelTimer()
    }


}
