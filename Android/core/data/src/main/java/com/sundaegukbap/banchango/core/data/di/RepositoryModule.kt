package com.sundaegukbap.banchango.core.data.di

import com.sundaegukbap.banchango.core.data.repository.DefaultRecipeRepository
import com.sundaegukbap.banchango.core.data.repository.FakeRecipeRepository
import com.sundaegukbap.banchango.core.data.repository.api.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class RepositoryModule {

    @Binds
    abstract fun bindsRecipeRepository(recipeRepository: FakeRecipeRepository): RecipeRepository
}
