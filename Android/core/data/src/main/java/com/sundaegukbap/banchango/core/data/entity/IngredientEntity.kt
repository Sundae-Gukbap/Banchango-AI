package com.sundaegukbap.banchango.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "kind") val kind: String?,
    @ColumnInfo(name = "image") val image: String?
)
