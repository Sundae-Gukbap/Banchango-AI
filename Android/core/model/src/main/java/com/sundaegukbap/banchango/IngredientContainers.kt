package com.sundaegukbap.banchango

class ContainerIngredients(
    containerIngredients: List<ContainerIngredient>
) {
    private val _value: MutableList<ContainerIngredient> = containerIngredients.toMutableList()
    val value: List<ContainerIngredient> get() = _value.toList()

    fun getIngredientContainers(): List<IngredientContainer> {
        return _value.groupBy { it.container }
            .map { (container, containerIngredients) ->
                IngredientContainer(
                    container = container,
                    kindIngredientContainers = containerIngredients.toKindIngredientContainers()
                )
            }
    }

    private fun List<ContainerIngredient>.toKindIngredientContainers(): List<KindIngredientContainer> {
        return groupBy { it.ingredient.kind }
            .map { (kind, ingredients) ->
                KindIngredientContainer(
                    kind = kind,
                    ingredients = ingredients
                )
            }
    }
}
