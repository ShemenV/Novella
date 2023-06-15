package com.example.novella.Data.Room.DbEntities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.novella.domain.Entities.Genre


@Entity(
    tableName = "Genres",
)

data class GenresDbEntity(
    @PrimaryKey @ColumnInfo(name = "Id") val id: Int,
    @ColumnInfo(name = "Title") val title: String
) {

    fun toGenre(): Genre =
        Genre(id = id,
            title = title)
}