package com.sundaegukbap.banchango.core.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sundaegukbap.banchango.core.data.dao.IngredientDao
import com.sundaegukbap.banchango.core.data.entity.IngredientEntity

@Database(entities = [IngredientEntity::class], version = 1)
abstract class BanchangoDatabase : RoomDatabase() {
    abstract fun ingredientDao(): IngredientDao
}