package com.totof.bread.data

import android.util.Log
import com.totof.bread.MainActivity
import java.io.Serializable

class Input : Serializable {
    var nbPainSimple = 0.0
    var nbDemiPainSimple = 0.0
    var nbPainSimpleMoule = 0.0
    var nbDemiPainSimpleMoule = 0.0
    var nbPainGraine = 0.0
    var nbDemiPainGraine = 0.0
    var nbPainGraineMoule = 0.0
    var nbDemiPainGraineMoule = 0.0

    var poidsPain = 0.0
    var pourcentageEau = 0.0
    var pourcentageLevain = 0.0
    var pourcentageEauLevain = 0.0
    var pourcentageSel = 0.0

    // currently cannot update
    val pourcentageGraine = 6.0

    var levainActuel = 0.0
    var levainAGarder = 0.0

    var currentFragmentPosition = MainActivity.DEFAULT_FRAGMENT
        set(value) {
            oldFragmentPosition = field
            field = value
        }
    var oldFragmentPosition = 0

    var isUpdated = false
        private set

    val seed: Double
        get() = poidsPain * pourcentageGraine / 100

    fun setPourcentageLevain(newStringValue: String, defaultValue: Double) {
        try {
            pourcentageLevain = update(newStringValue, defaultValue, pourcentageLevain)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setPourcentageEau(newStringValue: String, defaultValue: Double) {
        try {
            pourcentageEau = update(newStringValue, defaultValue, pourcentageEau)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbPainSimple(newStringValue: String, defaultValue: Double) {
        try {
            nbPainSimple = update(newStringValue, defaultValue, nbPainSimple)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbPainSimpleMoule(newStringValue: String, defaultValue: Double) {
        try {
            nbPainSimpleMoule = update(newStringValue, defaultValue, nbPainSimpleMoule)
            Log.d("Input", "nb pain simple moule : $nbPainSimpleMoule")
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbPainGraine(newStringValue: String, defaultValue: Double) {
        try {
            nbPainGraine = update(newStringValue, defaultValue, nbPainGraine)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbPainGraineMoule(newStringValue: String, defaultValue: Double) {
        try {
            nbPainGraineMoule = update(newStringValue, defaultValue, nbPainGraineMoule)
            Log.d("Input", "nb pain graine moule : $nbPainGraineMoule")
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setPoidsPain(newStringValue: String, defaultValue: Double) {
        try {
            poidsPain = update(newStringValue, defaultValue, poidsPain)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    val currentFlourOfYeast: Double
        get() = levainActuel / (1 + pourcentageEauLevain / 100)

    val currentWaterOfYeast: Double
        get() = levainActuel - currentFlourOfYeast

    fun setLevainActuel(newStringValue: String, defaultValue: Double) {
        try {
            levainActuel = update(newStringValue, defaultValue, levainActuel)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setLevainAGarder(newStringValue: String, defaultValue: Double) {
        try {
            levainAGarder = update(newStringValue, defaultValue, levainAGarder)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbDemiPainSimple(newStringValue: String, defaultValue: Double) {
        try {
            nbDemiPainSimple = update(newStringValue, defaultValue, nbDemiPainSimple)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbDemiPainSimpleMoule(newStringValue: String, defaultValue: Double) {
        try {
            nbDemiPainSimpleMoule = update(newStringValue, defaultValue, nbDemiPainSimpleMoule)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbDemiPainGraine(newStringValue: String, defaultValue: Double) {
        try {
            nbDemiPainGraine = update(newStringValue, defaultValue, nbDemiPainGraine)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setNbDemiPainGraineMoule(newStringValue: String, defaultValue: Double) {
        try {
            nbDemiPainGraineMoule = update(newStringValue, defaultValue, nbDemiPainGraineMoule)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setPourcentageEauLevain(newStringValue: String, defaultValue: Double) {
        try {
            pourcentageEauLevain = update(newStringValue, defaultValue, pourcentageEauLevain)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    fun setPourcentageSel(newStringValue: String, defaultValue: Double) {
        try {
            pourcentageSel = update(newStringValue, defaultValue, pourcentageSel)
        } catch (e: NumberFormatException) {
            // not updated
        }
    }

    val toutNbPainSimple: Double
        get() = nbPainSimple + nbDemiPainSimple / 2

    val toutNbPainSimpleMoule: Double
        get() = nbPainSimpleMoule + nbDemiPainSimpleMoule / 2

    val toutNbPainGraine: Double
        get() = nbPainGraine + nbDemiPainGraine / 2

    val toutNbPainGraineMoule: Double
        get() = nbPainGraineMoule + nbDemiPainGraineMoule / 2

    private fun update(newStringValue: String, defaultValue: Double, oldValue: Double): Double {
        try {
            val newValue = newStringValue.toDouble()
            if (oldValue != newValue) {
                isUpdated = true
                return newValue
            }
        } catch (e: Exception) {
            if (oldValue != defaultValue) {
                isUpdated = true
                return defaultValue
            }
        }
        throw NumberFormatException()
    }

    fun upToDate() {
        isUpdated = false
    }
}
