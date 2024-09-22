package com.sundaegukbap.banchango

class ContainerIngredients(
    containerIngredients: List<ContainerIngredient>
) {
    private val _value: MutableMap<Container, IngredientContainer> = containerIngredients
        .groupBy { it.container }
        .mapValues { (container, containerIngredients) ->
            IngredientContainer(
                container = container,
                kindIngredientContainers = containerIngredients.toKindIngredientContainers()
            )
        }.toMutableMap()

    val value: Map<Container, IngredientContainer> get() = _value.toMap()

    fun getKindIngredientContainerDetail(
        container: Container,
        kind: IngredientKind
    ): KindIngredientContainer {
        return _value[container]?.kindIngredientContainers?.find { it.kind == kind }!!
    }


    fun getIngredientContainers(): List<IngredientContainer> {
        return value.map { container ->
            IngredientContainer(
                container = container.key,
                kindIngredientContainers = container.value.kindIngredientContainers
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
