package com.sundaegukbap.banchango.core.data.di

import com.sundaegukbap.banchango.core.data.repository.DefaultIngredientRepository
import com.sundaegukbap.banchango.core.data.repository.DefaultRecipeRepository
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsRecipeRepository(recipeRepository: DefaultRecipeRepository): RecipeRepository

    @Singleton
    @Binds
    fun bindsIngredientRepository(ingredientRepository: DefaultIngredientRepository): IngredientRepository
}
