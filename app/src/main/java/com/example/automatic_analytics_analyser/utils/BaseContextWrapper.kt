package com.example.automatic_analytics_analyser.utils

import android.content.Context
import android.content.ContextWrapper
import com.yariksoffice.lingver.Lingver

class BaseContextWrapper(base: Context?) : ContextWrapper(base) {
    companion object {
        fun wrap(context: Context, locale: String): ContextWrapper {
            val con = context
            Lingver.getInstance().setLocale(context, locale)
            return ContextWrapper(con)
        }
    }
}