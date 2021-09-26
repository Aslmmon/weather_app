package com.floriaapp.core.db.dao

import androidx.room.*
import com.floriaapp.core.entity.CitiesEntities
import com.floriaapp.core.entity.DateWithData


@Dao
interface ForecastDAO {
    @Query("SELECT * FROM FORECASTENTITY")
    suspend fun getAll(): List<DateWithData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg dateWithData: DateWithData)

}