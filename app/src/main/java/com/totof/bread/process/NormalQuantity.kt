package com.totof.bread.process

import com.totof.bread.data.Input

class NormalQuantity(recipe: Input) {
    private val totalPasta: Double
    private val totalYeast: Double
    private val yeastFlour: Double
    private val yeastWater: Double
    private val breadFlour: Double
    private val breadWater: Double
    private val salt: Double

    init {
        // cannot currently update, consider pasta has 24% heavier weight than bread
        totalPasta = recipe.poidsPain * 1.24
        val totalFlour = totalPasta / (1 + recipe.pourcentageEau / 100)
        val totalWater = totalPasta - totalFlour

        totalYeast = totalPasta * recipe.pourcentageLevain / 100
        yeastFlour = totalYeast / (1 + recipe.pourcentageEauLevain / 100)
        yeastWater = totalYeast - yeastFlour

        breadFlour = totalFlour - yeastFlour
        breadWater = totalWater - yeastWater

        salt = totalFlour * recipe.pourcentageSel / 100
    }

    fun getTotalPasta(quantity: Double) = totalPasta * quantity
    fun getTotalYeast(quantity: Double) = totalYeast * quantity
    fun getYeastFlour(quantity: Double) = yeastFlour * quantity
    fun getYeastWater(quantity: Double) = yeastWater * quantity
    fun getBreadFlour(quantity: Double) = breadFlour * quantity
    fun getBreadWater(quantity: Double) = breadWater * quantity
    fun getSalt(quantity: Double) = salt * quantity
}
