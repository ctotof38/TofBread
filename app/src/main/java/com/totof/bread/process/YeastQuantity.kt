package com.totof.bread.process

import com.totof.bread.data.Input

class YeastQuantity(recipe: Input) {
    val total: Double = recipe.levainAGarder
    val flour: Double = total / (1 + recipe.pourcentageEauLevain / 100)
    val water: Double = total - flour
}
