package com.example.automatic_analytics_analyser.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityBloodTestsBinding
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.materialdrawer.iconics.iconicsIcon
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.*
import com.mikepenz.materialdrawer.util.addStickyFooterItem
import com.mikepenz.materialdrawer.widget.AccountHeaderView

class BloodTestsActivity : AbstractActivity() {
    private lateinit var binding: ActivityBloodTestsBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var headerView: AccountHeaderView

    private val viewModel: BloodTestsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BloodTestsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_blood_tests)

        val profile = ProfileDrawerItem().apply {
            nameText = "Mike Penz"; descriptionText = "mikepenz@gmail.com"; identifier =
            100; iconRes = R.drawable.ic_profile_image
        }


        // Create the AccountHeader
        headerView = AccountHeaderView(this).apply {
            attachToSliderView(binding.slider)
            addProfiles(
                profile
            )
            onAccountHeaderListener = { view, profile, current ->

                this@BloodTestsActivity.startActivity(
                    Intent(
                        this@BloodTestsActivity,
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
            itemAdapter.add(
                PrimaryDrawerItem().apply {
                    nameRes = R.string.blood_tests_screen
                    identifier = 1L

                },
                PrimaryDrawerItem().apply {
                    nameRes = R.string.medication_screen
                    identifier = 2L

                },
                PrimaryDrawerItem().apply {
                    nameRes = R.string.calendar_screen
                    identifier = 3L

                },
                PrimaryDrawerItem().apply {
                    nameRes = R.string.chat_screen
                    identifier = 4L

                }
            )
            onDrawerItemClickListener = { _, drawerItem, _ ->
                when(drawerItem.identifier) {
                    1L -> startActivity(Intent(this@BloodTestsActivity, BloodTestsActivity::class.java))
                }
                false
            }
            setSavedInstance(savedInstanceState)

        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.slider.addStickyFooterItem(PrimaryDrawerItem().apply {
            nameRes = R.string.logout
        })

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

}