package com.sundaegukbap.banchango.core.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.sundaegukbap.banchango.core.data.entity.IngredientEntity

@Dao
interface IngredientDao {

    @Upsert
    fun insertAll(ingredients: List<IngredientEntity>)

    @Upsert
    fun upsert(ingredient: IngredientEntity)

    @Delete
    fun delete(ingredient: IngredientEntity)

    // Query to get all ingredients
    @Query("SELECT * FROM IngredientEntity")
    fun getAllIngredients(): List<IngredientEntity>

    // Query to get ingredient by id
    @Query("SELECT * FROM IngredientEntity WHERE id = :id")
    fun getIngredientById(id: Int): IngredientEntity

    // Query to get ingredients where name contains a specific string (LIKE query)
    @Query("SELECT * FROM IngredientEntity WHERE name LIKE '%' || :name || '%'")
    fun getIngredientsByNameLike(name: String): List<IngredientEntity>

    // Query to get ingredients by kind
    @Query("SELECT * FROM IngredientEntity WHERE kind = :kind")
    fun getIngredientsByKind(kind: String): List<IngredientEntity>
}
