package com.example.automatic_analytics_analyser.notifications

enum class NotificationType(val id: Long) {
    ANALYSIS(2L), MEDICATION(3L), CALENDAR(4L), UNKNOWN(1L);

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