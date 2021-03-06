package com.example.RebmemMusicPlayer.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val songId: Long
) {
}