package com.sundaegukbap.banchango.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.ContainerIngredients
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ingredientRepository: IngredientRepository
) : ViewModel(), ContainerHost<HomeState, String> {

    private var containerIngredients: ContainerIngredients = ContainerIngredients(emptyList())
    override val container = container<HomeState, String>(HomeState())

    init {
        intent {
            viewModelScope.launch {
                reduce { state.copy(isLoading = true) }
                ingredientRepository.getIngredientContainers()
                    .onSuccess {
                        containerIngredients = ContainerIngredients(it)
                        reduce {
                            state.copy(
                                ingredientContainers = containerIngredients.getIngredientContainers(),
                                isLoading = false
                            )
                        }
                    }.onFailure {
                        postSideEffect("Failed to get ingredient containers")
                        reduce { state.copy(isLoading = false) }
                    }
            }
        }
    }

    fun addContainer(containerName: String) {
        intent {
            viewModelScope.launch {
                reduce { state.copy(isLoading = true) }
                ingredientRepository.addIngredientContainer(containerName)
                    .onSuccess {
                        ingredientRepository.getIngredientContainers()
                            .onSuccess {
                                containerIngredients = ContainerIngredients(it)
                                reduce {
                                    state.copy(
                                        ingredientContainers = containerIngredients.getIngredientContainers(),
                                        isLoading = false
                                    )
                                }
                            }.onFailure {
                                reduce { state.copy(isLoading = false) }
                                postSideEffect("Failed to get ingredient containers")
                            }
                    }.onFailure {
                        reduce { state.copy(isLoading = false) }
                        postSideEffect("Failed to add ingredient container")
                    }
            }
        }
    }

    fun getKindIngredientContainerDetail(container: Container, kind: IngredientKind) {
        intent {
            reduce {
                state.copy(
                    kindIngredientContainerDetail = containerIngredients.getKindIngredientContainerDetail(
                        container,
                        kind
                    ),
                    isDetailShowing = true
                )
            }
        }
        containerIngredients.getKindIngredientContainerDetail(container, kind)
    }

    fun getAllIngredients(name: String) = intent {
        reduce { state.copy(isLoading = true) }
        viewModelScope.launch {
            if (name.isBlank()) {
                ingredientRepository.getAllIngredients()
            } else {
                ingredientRepository.getIngredientsByNameLike(name)
            }.onSuccess {
                reduce { state.copy(ingredients = it, isLoading = false) }
            }.onFailure {
                reduce { state.copy(isLoading = false) }
                postSideEffect("Failed to get all ingredients")
            }
        }
    }

    fun closeDetail() {
        intent {
            reduce {
                state.copy(kindIngredientContainerDetail = null, isDetailShowing = false)
            }
        }
    }
}
