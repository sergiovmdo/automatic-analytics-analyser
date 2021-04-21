package com.example.automatic_analytics_analyser.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityDrawerBinding
import com.example.automatic_analytics_analyser.view.AbstractActivity
import com.example.automatic_analytics_analyser.view.SettingsActivity
import com.example.automatic_analytics_analyser.view.user.LoginActivity
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.descriptionText
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.model.interfaces.nameText
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.util.addStickyFooterItem
import com.mikepenz.materialdrawer.util.setupWithNavController
import com.mikepenz.materialdrawer.widget.AccountHeaderView

open class DrawerActivity : AbstractActivity() {
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var headerView: AccountHeaderView
    private lateinit var binding: ActivityDrawerBinding

    private val viewModel: DrawerActivityViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(DrawerActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_drawer)

        val profile = ProfileDrawerItem().apply {
            nameText = viewModel.userProfile.value?.name ?: ""; descriptionText =
            viewModel.userProfile.value?.mail ?: ""; identifier = 100;
            iconRes = R.drawable.ic_profile_image
        }

        viewModel.userProfile.observe(this, Observer {
            it?.let {user ->
                profile.apply {
                    nameText = user.name
                    descriptionText = user.mail
                }

                headerView.updateProfile(profile)
            }
        })

        // Create the AccountHeader
        headerView = AccountHeaderView(this).apply {
            attachToSliderView(binding.slider)
            addProfiles(
                profile
            )
            onAccountHeaderListener = { view, profile, current ->

                this@DrawerActivity.startActivity(
                    Intent(
                        this@DrawerActivity,
                        SettingsActivity::class.java
                    )
                )
                //true if you have consumed the event and it should close the drawer
                true
            }
            withSavedInstance(savedInstanceState)
        }

        headerView.selectionListEnabledForSingleProfile = false
        headerView.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SettingsActivity::class.java
                )
            )
        }

        binding.slider.apply {
            addItems(
                // Añadimos los elementos de tipo NavigationDrawerItem. El primer id es el
                // del grafo a donde quiera navegar este elemento
                NavigationDrawerItem(
                    R.id.mainActivity,
                    PrimaryDrawerItem().apply {
                        nameRes = R.string.main_screen
                        identifier = 1L
                        iconRes = R.drawable.ic_logo

                    },
                    null,
                    null
                ),
                NavigationDrawerItem(
                    R.id.analysisFragment,
                    // El elemento en si

                    PrimaryDrawerItem().apply {
                        nameRes = R.string.blood_tests_screen
                        identifier = 2L
                        iconRes = R.drawable.ic_blood_sample
                    },
                    null,
                    null
                ),
                NavigationDrawerItem(
                    R.id.medicationFragment,
                    PrimaryDrawerItem().apply {
                        nameRes = R.string.medication_screen
                        identifier = 3L
                        iconRes = R.drawable.ic_medicine
                    },
                    null,
                    null
                ),
                NavigationDrawerItem(
                    R.id.calendarFragment,
                    PrimaryDrawerItem().apply {
                        nameRes = R.string.calendar_screen
                        identifier = 4L
                        iconRes = R.drawable.ic_calendar
                    },
                    null,
                    null
                ),
                NavigationDrawerItem(
                    R.id.chatFragment,
                    PrimaryDrawerItem().apply {
                        nameRes = R.string.chat_screen
                        identifier = 5L
                        iconRes = R.drawable.ic_asistencia_medica
                    },
                    null,
                    null
                )
            )
        }

        binding.lifecycleOwner = this

        // Añadimos la toolbar y habilitamos el boton izquierdo
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Obtenemos el controlador desde el xml
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Para meter el icono para abrir
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )

        binding.slider.addStickyFooterItem(
            NavigationDrawerItem(
                R.id.loginActivity,
                PrimaryDrawerItem().apply {
                    nameRes = R.string.logout
                    identifier = 6L
                })
        )


        // Importante para que la navegacion sea automatica
        binding.slider.setupWithNavController(navController)

        navController.navigate(intent.getIntExtra("fragmentId", 0))
    }

    override fun onResume() {
        super.onResume()
        actionBarDrawerToggle.syncState()
    }

    private fun logout() {
        preferences.edit().putBoolean("logged", false).apply()
        preferences.edit().remove("token").apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}