package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ReadStatuses"
)

data class ReadStatusesDbEntity (
    @PrimaryKey @ColumnInfo(name = "Id") val id:Int,
    @ColumnInfo(name = "Title") val title: String?
)