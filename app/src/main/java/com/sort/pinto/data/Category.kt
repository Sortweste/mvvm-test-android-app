package com.sort.pinto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val id: Long,
    val name: String,
    @ColumnInfo(name = "image_url") val imageURL: String
)