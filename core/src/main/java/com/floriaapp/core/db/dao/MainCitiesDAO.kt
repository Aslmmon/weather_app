package com.floriaapp.core.db.dao

import androidx.room.*
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded

@Dao
interface MainCitiesListDAO {
    @Query("SELECT * FROM CityEntity")
    suspend fun getAll(): CitiesNeeded

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg citiesEntities: CitiesEntities?)

    @Delete
    suspend fun delete(stationEntity: CitiesEntities?)

//
//    @Transaction
//    @Query("SELECT * FROM cityentity")
//    suspend fun getCitiesAndData(): CityAndData
}


//data class CityAndData(
//    @Embedded val citiesEntities: CitiesEntities,
//    @Relation(
//        parentColumn = "cityEntityId",
//        entityColumn = "id"
//    )
//    val ListData: DateWithData
//)