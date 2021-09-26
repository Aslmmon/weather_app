package com.floriaapp.core.db.dao

import androidx.room.*
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.CitiesNeeded

@Dao
interface MainCitiesListDAO {
    @Query("SELECT * FROM CityEntity")
    suspend fun getAll(): CitiesNeeded

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg citiesEntities: CitiesEntities)

    @Delete
    suspend fun delete(stationEntity: CitiesEntities)
}