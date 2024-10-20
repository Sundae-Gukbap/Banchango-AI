package com.sundaegukbap.banchango.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sundaegukbap.banchango.Container
import com.sundaegukbap.banchango.ContainerIngredients
import com.sundaegukbap.banchango.Ingredient
import com.sundaegukbap.banchango.IngredientKind
import com.sundaegukbap.banchango.core.data.repository.api.IngredientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDateTime
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
                getAllIngredients(state.ingredientQuery)
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

    fun getKindIngredientContainerDetail(container: Container, kind: IngredientKind) = intent {
        reduce {
            state.copy(
                kindIngredientContainerDetail = containerIngredients.getKindIngredientContainerDetail(
                    container,
                    kind
                ),
                isDetailShowing = true
            )
        }
        containerIngredients.getKindIngredientContainerDetail(container, kind)
    }


    fun selectIngredient(ingredient: Ingredient) = intent {
        if (ingredient in state.selectedIngredients) {
            return@intent
        }
        reduce { state.copy(selectedIngredients = state.selectedIngredients + ingredient) }
    }

    fun unSelectIngredient(ingredient: Ingredient) = intent {
        reduce { state.copy(selectedIngredients = state.selectedIngredients - ingredient) }
    }

    fun addIngredientsToContainer(container: Container) = intent {
        viewModelScope.launch {
            reduce { state.copy(isLoading = true) }
            ingredientRepository.addIngredientToContainer(
                container.id,
                state.selectedIngredients.map { it.id },
                LocalDateTime.now().plusDays(7)
            ).onSuccess {
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
                postSideEffect("Failed to add ingredients to container")
            }
        }
    }

    fun getAllIngredients(query: String) = intent {
        reduce { state.copy(isLoading = true, ingredientQuery = query) }
        viewModelScope.launch(Dispatchers.IO) {
            if (query.isBlank()) {
                ingredientRepository.getAllIngredients()
            } else {
                ingredientRepository.getIngredientsByNameLike(query)
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
