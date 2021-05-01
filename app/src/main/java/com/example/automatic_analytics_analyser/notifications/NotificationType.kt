package com.example.automatic_analytics_analyser.notifications

import com.example.automatic_analytics_analyser.R

enum class NotificationType(val id: Int) {
    ANALYSIS(R.id.analysisFragment), MEDICATION(R.id.medicationFragment), CALENDAR(R.id.calendarFragment), CHAT(R.id.chatFragment), REVISION(R.id.medicationFragment),UNKNOWN(R.id.mainActivity);

    companion object {
        fun fromString(string: String): NotificationType {
            values().forEach {
                if (it.name.equals(string)) {
                    return it
                }
            }
            return UNKNOWN
        }
    }
}