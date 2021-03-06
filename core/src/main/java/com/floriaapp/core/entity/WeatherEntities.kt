package com.floriaapp.core.entity


import android.os.Parcelable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.android.parcel.Parcelize
import java.lang.reflect.Type
import java.util.*

typealias WeatherNeededData = List<ListData>


data class WeatherEntities(
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("cod")
    val cod: String,
    @SerializedName("list")
    val list: List<ListData>,
    @SerializedName("message")
    val message: Int
)


@Parcelize
data class DateWithData(
    var id: Int? = null,
    val date: String,
    val listNeeded: List<ListData>
) : Parcelable


@Parcelize
data class ListData(
    @SerializedName("dt_txt")
    val dtTxt: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("weather")
    val weather: List<Weather>,
) : Parcelable

data class Wind(
    @SerializedName("deg")
    val deg: Double,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
)

data class Rain(
    @SerializedName("3h")
    val h: Double
)

@Parcelize
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("grnd_level")
    val grndLevel: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("sea_level")
    val seaLevel: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_kf")
    val tempKf: Double,
    @SerializedName("temp_max")
    val tempMax: Double,
    @SerializedName("temp_min")
    val tempMin: Double
) : Parcelable

@Parcelize
data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
):Parcelable

data class Clouds(
    @SerializedName("all")
    val all: Int
)

data class Sys(
    @SerializedName("pod")
    val pod: String
)


object ListsDataConverter {

    @TypeConverter
    fun stringToList(data: String?): List<ListData>? {
        if (data == null) return null
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<ListData?>?>() {}.type
        return gson.fromJson<List<ListData>>(data, listType)
    }

    @TypeConverter
    fun listToString(myObjects: List<ListData>?): String? {
        if (myObjects == null) return null
        val gson = Gson()
        return gson.toJson(myObjects)
    }

    @TypeConverter
    fun dataToList(data: String?): List<DateWithData>? {
        if (data == null) return null
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<DateWithData?>?>() {}.type
        return gson.fromJson<List<DateWithData>>(data, listType)
    }

    @TypeConverter
    fun listTodata(myObjects: List<DateWithData>?): String? {
        if (myObjects == null) return null
        val gson = Gson()
        return gson.toJson(myObjects)
    }




}

class MainObjectConverter {

    @TypeConverter
    fun appToString(app: Main): String = Gson().toJson(app)

    @TypeConverter
    fun stringToApp(string: String): Main = Gson().fromJson(string, Main::class.java)

}

