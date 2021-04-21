package com.example.automatic_analytics_analyser.model

data class Medication(
    val id: Long,
    val medicines: List<Medicine>,
    val disease: String
) {

    fun showMedicines() : String {
        var medicinesText = ""
        medicines.forEach {
            medicinesText += it.name + " " + it.dose + "\n"
        }
        return medicinesText
    }
}

