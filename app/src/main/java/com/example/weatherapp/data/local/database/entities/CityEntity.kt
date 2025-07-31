package com.example.weatherapp.data.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class CityEntity(
    val cityName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
)