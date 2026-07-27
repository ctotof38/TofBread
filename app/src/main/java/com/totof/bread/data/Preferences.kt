package com.totof.bread.data

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Preferences(activity: Activity) {
    private val preferences: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    val input = Input()

    init {
        preferences.getString(NB_PAIN_SIMPLE, "1")?.let { input.setNbPainSimple(it, 1.0) }
        preferences.getString(NB_PAIN_SIMPLE_MOULE, "0")?.let { input.setNbPainSimpleMoule(it, 0.0) }
        preferences.getString(NB_PAIN_GRAINE, "0")?.let { input.setNbPainGraine(it, 0.0) }
        preferences.getString(NB_PAIN_GRAINE_MOULE, "0")?.let { input.setNbPainGraineMoule(it, 0.0) }
        preferences.getString(NB_DEMI_PAIN_SIMPLE, "0")?.let { input.setNbDemiPainSimple(it, 1.0) }
        preferences.getString(NB_DEMI_PAIN_SIMPLE_MOULE, "0")?.let { input.setNbDemiPainSimpleMoule(it, 0.0) }
        preferences.getString(NB_DEMI_PAIN_GRAINE, "0")?.let { input.setNbDemiPainGraine(it, 0.0) }
        preferences.getString(NB_DEMI_PAIN_GRAINE_MOULE, "0")?.let { input.setNbDemiPainGraineMoule(it, 0.0) }
        preferences.getString(POURCENTAGE_LEVAIN, "15")?.let { input.setPourcentageLevain(it, 15.0) }
        preferences.getString(POURCENTAGE_EAU_FINALE, "62")?.let { input.setPourcentageEau(it, 67.0) }
        preferences.getString(POIDS_PAIN, "1000")?.let { input.setPoidsPain(it, 1000.0) }
        preferences.getString(LEVAIN_ACTUEL, "150")?.let { input.setLevainActuel(it, 150.0) }
        preferences.getString(LEVAIN_GARDE, "150")?.let { input.setLevainAGarder(it, 150.0) }
        preferences.getString(POURCENTAGE_EAU_LEVAIN, "90")?.let { input.setPourcentageEauLevain(it, 90.0) }
        preferences.getString(POURCENTAGE_SEL, "1.8")?.let { input.setPourcentageSel(it, 1.8) }

        input.upToDate()
    }

    fun update() {
        if (input.isUpdated) {
            Log.d("preferences", "update pain simple moule : ${input.nbDemiPainSimpleMoule} / pain graine moule : ${input.nbPainGraineMoule}")
            preferences.edit().apply {
                putString(NB_PAIN_SIMPLE, input.nbPainSimple.toString())
                putString(NB_PAIN_SIMPLE_MOULE, input.nbPainSimpleMoule.toString())
                putString(NB_PAIN_GRAINE, input.nbPainGraine.toString())
                putString(NB_PAIN_GRAINE_MOULE, input.nbPainGraineMoule.toString())
                putString(NB_DEMI_PAIN_SIMPLE, input.nbDemiPainSimple.toString())
                putString(NB_DEMI_PAIN_SIMPLE_MOULE, input.nbDemiPainSimpleMoule.toString())
                putString(NB_DEMI_PAIN_GRAINE, input.nbDemiPainGraine.toString())
                putString(NB_DEMI_PAIN_GRAINE_MOULE, input.nbDemiPainGraineMoule.toString())
                putString(POIDS_PAIN, input.poidsPain.toString())
                putString(POURCENTAGE_LEVAIN, input.pourcentageLevain.toString())
                putString(POURCENTAGE_EAU_FINALE, input.pourcentageEau.toString())
                putString(LEVAIN_ACTUEL, input.levainActuel.toString())
                putString(LEVAIN_GARDE, input.levainAGarder.toString())
                putString(POURCENTAGE_EAU_LEVAIN, input.pourcentageEauLevain.toString())
                putString(POURCENTAGE_SEL, input.pourcentageSel.toString())
            }.apply()

            input.upToDate()
        }
    }

    companion object {
        private const val NB_PAIN_SIMPLE = "nbPainSimple"
        private const val NB_PAIN_SIMPLE_MOULE = "nbPainSimpleMoule"
        private const val NB_PAIN_GRAINE = "nbPainGraine"
        private const val NB_PAIN_GRAINE_MOULE = "nbPainGrainMoule"
        private const val NB_DEMI_PAIN_SIMPLE = "nbDemiPainSimple"
        private const val NB_DEMI_PAIN_SIMPLE_MOULE = "nbDemiPainSimpleMoule"
        private const val NB_DEMI_PAIN_GRAINE = "nbDemiPainGraine"
        private const val NB_DEMI_PAIN_GRAINE_MOULE = "nbDemiPainGrainMoule"
        private const val LEVAIN_ACTUEL = "levainActuel"
        private const val POURCENTAGE_LEVAIN = "pourcentageLevain"
        private const val POURCENTAGE_EAU_FINALE = "pourcentageEauFinale"
        private const val POIDS_PAIN = "poidsPain"
        private const val LEVAIN_GARDE = "levainGarde"
        private const val POURCENTAGE_EAU_LEVAIN = "pourcentageEauLevain"
        private const val POURCENTAGE_SEL = "sel"
    }
}
