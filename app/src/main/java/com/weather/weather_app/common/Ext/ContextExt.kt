package com.weather.weather_app.common.Ext

import android.app.*
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.core.app.NotificationCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.utils.R
import com.test.utils.SPLASH_CLASS_NAME
import com.weather.weather_app.di.getSharedPrefrences
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import java.io.*
import java.util.*
import java.util.concurrent.Flow


fun Context.createSpinner(
    list: ArrayList<String>,
    fromTags: Boolean = false
): ArrayAdapter<String?> {
    val spinnerAdapter: ArrayAdapter<String?> =
        object : ArrayAdapter<String?>(
            this,
            android.R.layout.simple_spinner_item,
            list as List<String?>
        ) {
            override fun getCount(): Int {
                val newList = mutableListOf<String>()
                list.forEachIndexed { index, s ->
                    if (index != 0) newList.add(s)
                }
                return if (fromTags) newList.size else list.size // Truncate the list
            }
        }

//    val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
//            R.layout.simple_spinner_item, list)

    spinnerAdapter.setDropDownViewResource(com.test.utils.R.layout.simple_spinner_dropdown_item)
    return spinnerAdapter
}

fun Context.getJsonFromAssets(context: Context, fileName: String?): String? {
    val jsonString: String
    jsonString = try {
        val `is`: InputStream = this.assets.open(fileName!!)
        val size: Int = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
    return jsonString
}

fun <T> Context.saveList(key: String?, list: List<T>?): Boolean {
    val gson = Gson()
    val json: String = gson.toJson(list)
    return getSharedPrefrences(androidApplication = this).edit().putString(key, json).commit()
}

fun Context.getList(key: String): String? {
    return getSharedPrefrences(this).getString(key, null)
}


fun Context.openUrl(url: String) {
    val i = Intent(Intent.ACTION_VIEW)
    i.data = Uri.parse(url)
    this.startActivity(i)
}

fun Context.getCompleteAddressString(LATITUDE: Double, LONGITUDE: Double): String? {
    var strAdd = ""
    val geocoder = Geocoder(this, Locale.getDefault())
    try {
        val addresses: List<Address>? = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1)
        if (addresses != null) {
            val returnedAddress: Address = addresses[0]
            val strReturnedAddress = StringBuilder("")
            for (i in 0..returnedAddress.maxAddressLineIndex) {
                strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n")
            }
            strAdd = strReturnedAddress.toString()
        } else {
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return strAdd
}

fun Context.getCountryName( latitude: Double, longitude: Double): String? {
    val geocoder = Geocoder(this, Locale.getDefault())
    var addresses: List<Address>? = null
    try {
        addresses = geocoder.getFromLocation(latitude, longitude, 1)
        var result: Address
        return if (addresses != null && addresses.isNotEmpty()) {
            addresses[0].countryName
        } else null
    } catch (ignored: IOException) {
        //do something
        return null
    }
}
//
//fun EditText.textChanges(): Flow<CharSequence?> {
//    return callbackFlow<CharSequence?> {
//        val listener = object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) = Unit
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                trySend(s)
//            }
//        }
//        addTextChangedListener(listener)
//        awaitClose { removeTextChangedListener(listener) }
//    }.onStart { emit(text) }
//}
//
//@SuppressLint("RestrictedApi")
//fun BottomNavigationView.disableShiftMode() {
//    val menuView = getChildAt(0) as BottomNavigationMenuView
//    try {
//        val shiftingMode = menuView::class.java.getDeclaredField("mShiftingMode")
//        shiftingMode.isAccessible = true
//        shiftingMode.setBoolean(menuView, false)
//        shiftingMode.isAccessible = false
//        for (i in 0 until menuView.childCount) {
//            val item = menuView.getChildAt(i) as BottomNavigationItemView
//            item.setShifting(false)
//            // set once again checked value, so view will be updated
//            item.setChecked(item.itemData.isChecked)
//        }
//    } catch (e: NoSuchFieldException) {
//        Log.e("error bottomnav", "Unable to get shift mode field", e)
//    } catch (e: IllegalStateException) {
//        Log.e("error bottomnav", "Unable to change value of shift mode", e)
//    }
//}

//fun ImageView.loadImage(src: String? = null, srcInt: Int? = null, noPlaceHolder: Boolean? = false) {
//    if (noPlaceHolder == true) Glide.with(this.context).load(src ?: srcInt)
//        .error(com.test.utils.R.drawable.ic_profile_user)
//        .into(this)
//    else Glide.with(this.context).load(src ?: srcInt)
//        .placeholder(com.test.utils.R.drawable.ic_loader).error(com.test.utils.R.drawable.ic_loader)
//        .into(this)
//}

fun Context.bitmapToFile(bitmap: Bitmap): File {
    // Get the context wrapper
    val wrapper = ContextWrapper(this)
    val getImage: File? = filesDir
    val file = File(getImage?.path, "${UUID.randomUUID()}.jpg")
    try {
        // Compress the bitmap and save in jpg format
        val stream: OutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, stream)
        stream.flush()
        stream.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return file
}

fun Activity.showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

/**
 * Manages the various graphs needed for a [BottomNavigationView].
 *
 * This sample is a workaround until the Navigation Component supports multiple back stacks.
 */
//fun BottomNavigationView.setupWithNavController(
//    navGraphIds: List<Int>,
//    fragmentManager: FragmentManager,
//    containerId: Int,
//    intent: Intent
//): LiveData<NavController> {
//
//    // Map of tags
//    val graphIdToTagMap = SparseArray<String>()
//    // Result. Mutable live data with the selected controlled
//    val selectedNavController = MutableLiveData<NavController>()
//
//    var firstFragmentGraphId = 0
//
//    // First create a NavHostFragment for each NavGraph ID
//    navGraphIds.forEachIndexed { index, navGraphId ->
//        val fragmentTag = getFragmentTag(index)
//
//        // Find or create the Navigation host fragment
//        val navHostFragment = obtainNavHostFragment(
//            fragmentManager,
//            fragmentTag,
//            navGraphId,
//            containerId
//        )
//
//        // Obtain its id
//        val graphId = navHostFragment.navController.graph.id
//
//        if (index == 0) {
//            firstFragmentGraphId = graphId
//        }
//
//        // Save to the map
//        graphIdToTagMap[graphId] = fragmentTag
//
//        // Attach or detach nav host fragment depending on whether it's the selected item.
//        if (this.selectedItemId == graphId) {
//            // Update livedata with the selected graph
//            selectedNavController.value = navHostFragment.navController
//            attachNavHostFragment(fragmentManager, navHostFragment, index == 0)
//        } else {
//            detachNavHostFragment(fragmentManager, navHostFragment)
//        }
//    }
//
//    // Now connect selecting an item with swapping Fragments
//    var selectedItemTag = graphIdToTagMap[this.selectedItemId]
//    val firstFragmentTag = graphIdToTagMap[firstFragmentGraphId]
//    var isOnFirstFragment = selectedItemTag == firstFragmentTag
//
//    // When a navigation item is selected
//    setOnNavigationItemSelectedListener { item ->
//        // Don't do anything if the state is state has already been saved.
//        if (fragmentManager.isStateSaved) {
//            false
//        } else {
//            val newlySelectedItemTag = graphIdToTagMap[item.itemId]
//            if (selectedItemTag != newlySelectedItemTag) {
//                // Pop everything above the first fragment (the "fixed start destination")
//                fragmentManager.popBackStack(
//                    firstFragmentTag,
//                    FragmentManager.POP_BACK_STACK_INCLUSIVE
//                )
//                val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
//                        as NavHostFragment
//
//                // Exclude the first fragment tag because it's always in the back stack.
//                if (firstFragmentTag != newlySelectedItemTag) {
//                    // Commit a transaction that cleans the back stack and adds the first fragment
//                    // to it, creating the fixed started destination.
//                    fragmentManager.beginTransaction()
//                        .attach(selectedFragment)
//                        .setPrimaryNavigationFragment(selectedFragment)
//                        .apply {
//                            // Detach all other Fragments
//                            graphIdToTagMap.forEach { _, fragmentTagIter ->
//                                if (fragmentTagIter != newlySelectedItemTag) {
//                                    detach(fragmentManager.findFragmentByTag(firstFragmentTag)!!)
//                                }
//                            }
//                        }
//                        .addToBackStack(firstFragmentTag)
//                        .setReorderingAllowed(true)
//                        .commit()
//                }
//                selectedItemTag = newlySelectedItemTag
//                isOnFirstFragment = selectedItemTag == firstFragmentTag
//                selectedNavController.value = selectedFragment.navController
//                true
//            } else {
//                false
//            }
//        }
//    }
//
//    // Optional: on item reselected, pop back stack to the destination of the graph
//    setupItemReselected(graphIdToTagMap, fragmentManager)
//
//    // Handle deep link
//    setupDeepLinks(navGraphIds, fragmentManager, containerId, intent)
//
//    // Finally, ensure that we update our BottomNavigationView when the back stack changes
//    fragmentManager.addOnBackStackChangedListener {
//        if (!isOnFirstFragment && !fragmentManager.isOnBackStack(firstFragmentTag)) {
//            this.selectedItemId = firstFragmentGraphId
//        }
//
//        // Reset the graph if the currentDestination is not valid (happens when the back
//        // stack is popped after using the back button).
//        selectedNavController.value?.let { controller ->
//            if (controller.currentDestination == null) {
//                controller.navigate(controller.graph.id)
//            }
//        }
//    }
//    return selectedNavController
//}
//
//
//private fun BottomNavigationView.setupDeepLinks(
//    navGraphIds: List<Int>,
//    fragmentManager: FragmentManager,
//    containerId: Int,
//    intent: Intent
//) {
//    navGraphIds.forEachIndexed { index, navGraphId ->
//        val fragmentTag = getFragmentTag(index)
//
//        // Find or create the Navigation host fragment
//        val navHostFragment = obtainNavHostFragment(
//            fragmentManager,
//            fragmentTag,
//            navGraphId,
//            containerId
//        )
//        // Handle Intent
//        if (navHostFragment.navController.handleDeepLink(intent)
//            && selectedItemId != navHostFragment.navController.graph.id
//        ) {
//            this.selectedItemId = navHostFragment.navController.graph.id
//        }
//    }
//}

//private fun BottomNavigationView.setupItemReselected(
//    graphIdToTagMap: SparseArray<String>,
//    fragmentManager: FragmentManager
//) {
//    setOnNavigationItemReselectedListener { item ->
//        val newlySelectedItemTag = graphIdToTagMap[item.itemId]
//        val selectedFragment = fragmentManager.findFragmentByTag(newlySelectedItemTag)
//                as NavHostFragment
//        val navController = selectedFragment.navController
//        // Pop the back stack to the start destination of the current navController graph
//        navController.popBackStack(
//            navController.graph.startDestination, false
//        )
//    }
//}
//
//private fun detachNavHostFragment(
//    fragmentManager: FragmentManager,
//    navHostFragment: NavHostFragment
//) {
//    fragmentManager.beginTransaction()
//        .detach(navHostFragment)
//        .commitNow()
//}
//
//private fun attachNavHostFragment(
//    fragmentManager: FragmentManager,
//    navHostFragment: NavHostFragment,
//    isPrimaryNavFragment: Boolean
//) {
//    fragmentManager.beginTransaction()
//        .attach(navHostFragment)
//        .apply {
//            if (isPrimaryNavFragment) {
//                setPrimaryNavigationFragment(navHostFragment)
//            }
//        }
//        .commitNow()
//
//}
//
//private fun obtainNavHostFragment(
//    fragmentManager: FragmentManager,
//    fragmentTag: String,
//    navGraphId: Int,
//    containerId: Int
//): NavHostFragment {
//    // If the Nav Host fragment exists, return it
//    val existingFragment = fragmentManager.findFragmentByTag(fragmentTag) as NavHostFragment?
//    existingFragment?.let { return it }
//
//    // Otherwise, create it and return it.
//    val navHostFragment = NavHostFragment.create(navGraphId)
//    fragmentManager.beginTransaction()
//        .add(containerId, navHostFragment, fragmentTag)
//        .commitNow()
//    return navHostFragment
//}
//
//private fun FragmentManager.isOnBackStack(backStackName: String): Boolean {
//    val backStackCount = backStackEntryCount
//    for (index in 0 until backStackCount) {
//        if (getBackStackEntryAt(index).name == backStackName) {
//            return true
//        }
//    }
//    return false
//}
//
//private fun getFragmentTag(index: Int) = "bottomNavigation#$index"

fun Context?.setLocale(langugeNeeded: String) {
    val locale = Locale(langugeNeeded)
    Locale.setDefault(locale)
    val config = Configuration()
    config.locale = locale
    this?.resources?.updateConfiguration(
        config,
        this.resources.displayMetrics
    )
}

fun Context.showLargeImage(urlOfImage: String) {
    val builder = Dialog(this)
    builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
    builder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    builder.setCanceledOnTouchOutside(true)
    val imageView = ImageView(this)
    // Picasso.get().load(urlOfImage).into(imageView)
    builder.addContentView(
        imageView, RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    )
    builder.show()
}

fun Context.showAlertDialog(title: String, message: String, launchFunction: () -> Unit) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(resources.getString(com.test.utils.R.string.yes)) { dialog, which ->
        launchFunction()
    }
    builder.setNeutralButton(resources.getString(com.test.utils.R.string.no)) { dialog, which ->
        dialog.dismiss()
    }
    val dialog: AlertDialog = builder.create()
    dialog.show()
}

//fun <T> Context.saveObject(key: String?, user: T) {
//    val gson = Gson()
//    val json: String = gson.toJson(user)
//    getSharedPrefrences(androidApplication = this).edit().putString(key, json).apply()
//}
//
//fun <T> Context.getObject(key: String?, type: Class<T>): T? {
//    val gson = Gson()
//    val json: String? = getSharedPrefrences(androidApplication = this).getString(key, "")
//    return gson.fromJson(json, type)
//}

//fun <T> Context.saveList(key: String?, list: List<T>?) {
//    val gson = Gson()
//    val json: String = gson.toJson(list)
//    getSharedPrefrences(androidApplication = this).edit().putString(key, json).apply()
//}
//
//fun Context.getList(key: String): String? {
//    return getSharedPrefrences(this).getString(key, null)
//}

/**
 * To Json
 */
inline fun <reified T : Any> T?.json() = this?.let { Gson().toJson(this, T::class.java) }

/**
 * from  Json
 */
inline fun <reified T : Any> String?.fromJson(): T? = this?.let {
    val type = object : TypeToken<T>() {}.type
    Gson().fromJson(this, type)
}

fun NotificationManager.sendNotification(
    messageBody: String,
    messageTitle: String,
    applicationContext: Context
) {
    val contentIntent = Intent(applicationContext, SPLASH_CLASS_NAME::class.java)
    contentIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
    contentIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    val pendingIntent = PendingIntent.getActivity(
        applicationContext, 5, contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.notification_id)
    ).setContentTitle((messageTitle))
        .setContentText(messageBody)
        .setContentIntent(pendingIntent)
        .setLargeIcon(
            BitmapFactory.decodeResource(
                applicationContext.resources,
                com.test.utils.R.drawable.ic_logo
            )
        )
        .setSmallIcon(com.test.utils.R.drawable.ic_logo)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    notify(5, builder.build())
}