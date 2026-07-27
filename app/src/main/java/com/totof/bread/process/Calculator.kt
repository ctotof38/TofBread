package com.totof.bread.process

import com.totof.bread.data.Input
import com.totof.bread.data.Output
import java.text.DecimalFormat

object Calculator {

    /**
     * return a double value in String form, and delete decimal value if it is 0
     * @param value the value to convert
     * @return the string representation
     */
    @JvmStatic
    fun truncateDoubleToString(value: Double): String {
        return DecimalFormat("######").format(value)
    }

    @JvmStatic
    @Throws(NumberFormatException::class)
    fun calculate(input: Input?): Output {
        val result = Output()

        if (input == null) {
            throw NumberFormatException("pas de donnees !")
        }
        if (input.poidsPain <= 0) {
            throw NumberFormatException("poids 0 !")
        }
        if (input.pourcentageEau < 45) {
            throw NumberFormatException("hydratation trop faible !")
        }

        val nbPain = input.toutNbPainSimple + input.toutNbPainSimpleMoule + input.toutNbPainGraine + input.toutNbPainGraineMoule
        if (nbPain <= 0) {
            throw NumberFormatException("quantité de pain 0 !")
        }

        val oneNormalBread = NormalQuantity(input)
        val oneSeedBread = SeedQuantity(input)
        val yeastToKeep = YeastQuantity(input)

        result.nbPain = nbPain
        result.hydratation = input.pourcentageEau

        result.patePourUnPain = oneNormalBread.getTotalPasta(1.0)

        val nbTotalNormalBread = input.toutNbPainSimple + input.toutNbPainSimpleMoule
        val nbTotalSeedBread = input.toutNbPainGraine + input.toutNbPainGraineMoule
        val nbNormalBread = input.toutNbPainSimple
        val nbSeedBread = input.toutNbPainGraine

        // don't forget yeast to keep
        val totalPasta = oneNormalBread.getTotalPasta(nbTotalNormalBread) + oneSeedBread.getTotalPasta(nbTotalSeedBread) + yeastToKeep.total
        result.pateTotale = totalPasta

        val totalYeast = oneNormalBread.getTotalYeast(nbTotalNormalBread) + oneSeedBread.getTotalYeast(nbTotalSeedBread)
        result.levainDePate = totalYeast

        // add flour and water of levain a garder
        val totalFlour = oneNormalBread.getBreadFlour(nbTotalNormalBread) + oneSeedBread.getBreadFlour(nbTotalSeedBread) + yeastToKeep.flour
        result.farineBleTotale = totalFlour

        val totalWater = oneNormalBread.getBreadWater(nbTotalNormalBread) + oneSeedBread.getBreadWater(nbTotalSeedBread) + yeastToKeep.water
        result.eauBleTotale = totalWater

        val totalSalt = oneNormalBread.getSalt(nbTotalNormalBread) + oneSeedBread.getSalt(nbTotalSeedBread)
        result.selTotal = totalSalt

        val totalSeed = oneSeedBread.getSeed(nbTotalSeedBread)
        result.graine = totalSeed

        val floorOfYeast = oneNormalBread.getYeastFlour(nbTotalNormalBread) + oneSeedBread.getYeastFlour(nbTotalSeedBread) - input.currentFlourOfYeast
        result.farinePourLevain = floorOfYeast

        val waterOfYeast = oneNormalBread.getYeastWater(nbTotalNormalBread) + oneSeedBread.getYeastWater(nbTotalSeedBread) - input.currentWaterOfYeast
        result.eauPourLevain = waterOfYeast

        result.pateAGarder = input.levainAGarder

        result.pateTotaleSimple = oneNormalBread.getTotalPasta(nbTotalNormalBread)

        result.pateTotaleGraine = oneSeedBread.getTotalPasta(nbTotalSeedBread) + oneSeedBread.getSeed(nbTotalSeedBread)

        result.pateSimple = oneNormalBread.getTotalPasta(nbNormalBread)

        result.pateGraine = oneSeedBread.getTotalPasta(nbSeedBread) + oneSeedBread.getSeed(nbSeedBread)

        if (result.farinePourLevain < 0) {
            result.farinePourLevain = 0.0
        }
        if (result.eauPourLevain < 0) {
            result.eauPourLevain = 0.0
        }

        return result
    }
}
