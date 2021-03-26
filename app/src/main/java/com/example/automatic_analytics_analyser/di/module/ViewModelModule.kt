package com.example.automatic_analytics_analyser.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.automatic_analytics_analyser.di.util.ViewModelFactory
import com.example.automatic_analytics_analyser.di.util.ViewModelKey
import com.example.automatic_analytics_analyser.view.MainActivityViewModel
import com.example.automatic_analytics_analyser.view.SettingsViewModel
import com.example.automatic_analytics_analyser.view.user.LoginViewModel
import com.example.automatic_analytics_analyser.view.user.ResgisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResgisterViewModel::class)
    abstract fun registerViewModel(viewModel: ResgisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun mainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun settingsActivityViewModel(viewModel: SettingsViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}