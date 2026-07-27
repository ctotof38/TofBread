package com.totof.bread.data

import java.io.Serializable

data class Output(
    var nbPain: Double = 0.0,
    var patePourUnPain: Double = 0.0,
    var hydratation: Double = 0.0,
    var pourcentageLevain: Double = 0.0,
    var pourcentageSel: Double = 0.0,
    var pateTotale: Double = 0.0,
    var pateAGarder: Double = 0.0,
    var eauPourPateAGarder: Double = 0.0,
    var farineBleTotale: Double = 0.0,
    var eauBleTotale: Double = 0.0,
    var selTotal: Double = 0.0,
    var levainDePate: Double = 0.0,
    var farinePourLevain: Double = 0.0,
    var eauPourLevain: Double = 0.0,
    var pateTotaleSimple: Double = 0.0,
    var pateTotaleGraine: Double = 0.0,
    var pateSimple: Double = 0.0,
    var pateGraine: Double = 0.0,
    var graine: Double = 0.0
) : Serializable {
    companion object {
        private const val serialVersionUID = 20130403052513L
    }
}
