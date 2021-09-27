package com.test.utils


const val API_OPENWATHER_KEY = "01ed0bf33f0d52e1bad309739903a79b"
const val CITIES_DATA = "cities"
const val MAXIMUM_CITIES= 4
const val CITY_JSON_FILENAME="city.json"
const val CitiesCounter = "city counter"
const val CITY_DATA = "city data"
const val DEFAULT_COUNTRY = "London"
const val LOCATION_PERMISSON = 10



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


