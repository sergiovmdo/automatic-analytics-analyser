package com.example.automatic_analytics_analyser.di.module

import com.example.automatic_analytics_analyser.view.fragments.analysis.AnalysisFragment
import com.example.automatic_analytics_analyser.view.fragments.calendar.CalendarFragment
import com.example.automatic_analytics_analyser.view.fragments.chat.ChatFragment
import com.example.automatic_analytics_analyser.view.fragments.medication.MedicationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeAnalysisFragment() : AnalysisFragment

    @ContributesAndroidInjector
    abstract fun contributeMedicationFragment() : MedicationFragment

    @ContributesAndroidInjector
    abstract fun contributeCalendarFragment() : CalendarFragment

    @ContributesAndroidInjector
    abstract fun contributeChatFragment() : ChatFragment
}