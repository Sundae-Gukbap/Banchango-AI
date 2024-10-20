package com.sundaegukbap.banchango.core.data.repository

import android.content.Context
import android.util.Log
import com.opencsv.CSVReader
import com.sundaegukbap.banchango.ContainerIngredient
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.core.data.api.IngredientApi
import com.sundaegukbap.banchango.core.data.api.model.AddIngredientContainerRequest
import com.sundaegukbap.banchango.core.data.dao.IngredientDao
import com.sundaegukbap.banchango.core.data.entity.IngredientEntity
import com.sundaegukbap.banchango.core.data.mapper.toData
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.toLongOrDefault
import java.io.InputStreamReader
import javax.inject.Inject

internal class DefaultIngredientRepository @Inject constructor(
    private val ingredientApi: IngredientApi,
    private val ingredientDao: IngredientDao,
    @ApplicationContext context: Context,
) : IngredientRepository {

    init {
        try {
            val inputStream = context.resources.assets.open("ingredients.csv")
            val csvReader = CSVReader(InputStreamReader(inputStream, "utf-8"))
            CoroutineScope(Dispatchers.IO).launch {
                ingredientDao.insertAll(
                    csvReader.readAll().map { content ->
                        IngredientEntity(
                            id = content[0].toLongOrDefault(1L),
                            name = content[3],
                            kind = content[2],
                            image = content[1]
                        )
                    }
                )
            }
        } catch (e: Exception) {
            Log.e("DefaultIngredientRepository", "Failed to read ingredients.csv", e)
        }
    }

    override suspend fun getIngredientContainers(): Result<List<ContainerIngredient>> {
        return ingredientApi.getIngredients(1).mapCatching { dto ->
            dto.containerIngredientDtos.map { it.toData() }
        }
    }

    override suspend fun addIngredientContainer(containerName: String): Result<Unit> {
        return ingredientApi.addIngredientContainer(1, AddIngredientContainerRequest(containerName))
    }

    override suspend fun getAllIngredients(): Result<List<Ingredient>> {
        return kotlin.runCatching {
            ingredientDao.getAllIngredients().map { it.toData() }
        }
    }

    override suspend fun getIngredientsByNameLike(name: String): Result<List<Ingredient>> {
        return kotlin.runCatching {
            ingredientDao.getIngredientsByNameLike(name).map { it.toData() }
        }
    }
}

