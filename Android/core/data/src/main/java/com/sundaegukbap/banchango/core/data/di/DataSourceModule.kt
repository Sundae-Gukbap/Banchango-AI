package com.sundaegukbap.banchango.core.data.di

import android.content.Context
import androidx.room.Room
import com.sundaegukbap.banchango.core.data.db.BanchangoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataSourceModule {

    @Provides
    fun providesIngredientDao(dataSource: BanchangoDatabase) = dataSource.ingredientDao()

    @Singleton
    @Provides
    fun provideDataSource(@ApplicationContext context: Context): BanchangoDatabase =
        Room.databaseBuilder(
            context,
            BanchangoDatabase::class.java, "banchango.db"
        ).build()
}