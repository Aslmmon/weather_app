package com.test.utils.Bases

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.floriaapp.core.ui.CheckoutViewModel
import com.floriaapp.core.ui.LoginViewModel
import com.test.utils.*
import com.test.utils.Common.CustomDialog
import com.test.utils.Common.CustomProgress
import com.test.utils.Ext.setLocale
import com.test.utils.adapter.LanguagesAdapter
import org.koin.android.ext.android.inject

open class BaseActivity : AppCompatActivity() {

    lateinit var loadingDialog: CustomProgress
    lateinit var customDialog: CustomDialog

    val sharedPrefrenceEditor: SharedPreferences.Editor by inject()
    val sharedPrefrence: SharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog = CustomProgress()
        customDialog = CustomDialog()
        //setLocale(ARABIC)
        getSharedPrefrenceInstance().getString(LANGUAGE_PREFRENCE, ARABIC)
            ?.let { langugae -> setLocale(langugae) }


    }

    fun showProgress() {
        loadingDialog.show(this, resources.getString(R.string.Loading))
    }

    fun isWalletExceedsTotalPrice(): Boolean {
        val walletBalance = sharedPrefrence.getInt(WALLET_BALANCE, 0)
        val totalMoeny = sharedPrefrence.getInt(TOTAL_ORDER_MONEY, 0)
        return walletBalance >= totalMoeny
    }

    fun getTotalPrice():Int {
        val totalMoeny = sharedPrefrence.getInt(TOTAL_ORDER_MONEY, 0)
        return  totalMoeny
    }

    fun getWalletBalance():Int {
        val walletBalance = sharedPrefrence.getInt(WALLET_BALANCE, 0)
        return  walletBalance
    }
    fun getDifferenceofBalances():Int {
        val walletBalance = sharedPrefrence.getInt(WALLET_BALANCE, 0)
        val totalMoeny = sharedPrefrence.getInt(TOTAL_ORDER_MONEY, 0)
        return  totalMoeny -walletBalance
    }

    fun getSharedPrefrenceEdit() = sharedPrefrenceEditor
    fun getSharedPrefrenceInstance() = sharedPrefrence

    fun dismissProgressDialog() {
        loadingDialog.dismissDialog()
    }

    fun userVerifiedEnter(): Boolean {
        val isLoggedIn = sharedPrefrence.getString(TOKEN_USER, null)
        val isVerified = sharedPrefrence.getBoolean(USER_ISVERIFIED, false)
        return isLoggedIn != null && isVerified
    }

    fun showErrorDialog(message: String) {
        customDialog.showDialog(this, message)

    }

    fun showAuthenticationDialog(function: () -> Unit) {
        customDialog.showAuthenticationDialog(this, function, null)

    }

    fun showNotAuthorizedUser(function: () -> Unit): Boolean {
        if (!userVerifiedEnter()) {
            showAuthenticationDialog(function)
            return true
        }
        return false
    }

    fun navigateToLogin(): Unit {
        NavigationUtils.goToDestinationWithClearTasks(this, Class.forName(LOGIN_CLASS_NAME))
    }


    fun showBlockDialog(message: String) {
        customDialog.showBlockedDialog(this, message)

    }

    fun showSuccessSentMessage() {
        customDialog.showSuccessSent(this)

    }

    fun showSuccessFavouriteMessage(message: String) {
        customDialog.showSuccessFavourites(this, message)

    }

    fun showSuccessVerification(function: () -> Unit) {
        customDialog.showSuccessVerification(this, function)

    }

    fun showRateDialog(
        checkoutViewModel: CheckoutViewModel,
        orderID: Int
    ) {
        customDialog.showRateDialog(this, checkoutViewModel, orderID)
    }


    fun showChangePassowrd(loginViewModel: LoginViewModel) {
        customDialog.showChangePassword(this, loginViewModel)

    }

    fun showSuccessChangePassword() {
        customDialog.showSuccessChangedPassword(this)
    }

    fun showLanguages(adapter: LanguagesAdapter) {
        customDialog.showLangages(this, adapter)

    }


    fun showAttachment(
        launcher: ActivityResultLauncher<Intent>,
        ImageFunction: () -> Unit,
        passer: (ImageView) -> Unit
    ) {
        customDialog.showAttachmentReceipt(this, ImageFunction, launcher, passer = passer)

    }


    fun showVerificationDialog(
        loginViewModel: LoginViewModel,
        typeOfApi: Int,
        phoneNumber: String? = null,
        function: (String) -> Unit
    ) {
        customDialog.showVerificationDialog(
            this,
            loginViewModel,
            typeOfApi,
            phoneNumber,
            function
        )

    }

    fun showDeleteDialog(function: () -> Unit) {
        customDialog.showDeleteDialog(this, function)

    }

    fun showSuccessDialog(function: () -> Unit) {
        customDialog.showSuccessADDdialog(this, function)

    }

    fun dismissAnyDialog() {
        customDialog.dismissAnyDialog()

    }

    fun clearAll() {
        customDialog
        customDialog.cancelTimer()
    }


}