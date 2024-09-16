package com.sundaegukbap.banchango.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundaegukbap.banchango.ContainerIngredients
import com.sundaegukbap.banchango.IngredientContainer
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : ViewModel() {

    private var containerIngredients: ContainerIngredients = ContainerIngredients(emptyList())

    private val _ingredientContainers: MutableStateFlow<List<IngredientContainer>> =
        MutableStateFlow(emptyList())
    val ingredientContainers: StateFlow<List<IngredientContainer>> =
        _ingredientContainers.asStateFlow()

    init {
        viewModelScope.launch {
            ingredientRepository.getIngredientContainers()
                .onSuccess {
                    containerIngredients = ContainerIngredients(it)
                    _ingredientContainers.value = containerIngredients.getIngredientContainers()
                    Log.d(
                        "asdf",
                        "containerIngredients: ${containerIngredients.getIngredientContainers()}"
                    )
                }.onFailure {
                    Log.e("asdf", "Failed to get ingredient containers", it)
                }
        }
    }
}

