package com.sundaegukbap.banchango.core.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.sundaegukbap.banchango.core.data.BuildConfig
import com.sundaegukbap.banchango.core.data.api.IngredientApi
import com.sundaegukbap.banchango.core.data.api.RecipeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultRetrofitQualifier

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofitConverterFactory(): retrofit2.Converter.Factory {
        val json = Json {
            ignoreUnknownKeys = true
        }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    @DefaultRetrofitQualifier
    fun providesDefaultRetrofit(
        @BaseUrlQualifier baseUrl: String,
        converterFactory: retrofit2.Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    @BaseUrlQualifier
    fun providesBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun providesRecipeRecommendApi(
        @DefaultRetrofitQualifier retrofit: Retrofit
    ): RecipeApi = retrofit.create(RecipeApi::class.java)

    @Provides
    @Singleton
    fun providesIngredientApi(
        @DefaultRetrofitQualifier retrofit: Retrofit
    ): IngredientApi = retrofit.create(IngredientApi::class.java)
}
