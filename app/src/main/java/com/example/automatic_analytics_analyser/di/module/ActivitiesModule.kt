package com.example.automatic_analytics_analyser.di.module

import com.example.automatic_analytics_analyser.view.IntroActivity
import com.example.automatic_analytics_analyser.view.user.LoginActivity
import com.example.automatic_analytics_analyser.view.MainActivity
import com.example.automatic_analytics_analyser.view.user.RegisterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity

    @ContributesAndroidInjector
    abstract fun contributeIntroActivity() : IntroActivity

    @ContributesAndroidInjector
    abstract fun contributeRegisterActivity() : RegisterActivity

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity() : LoginActivity
}