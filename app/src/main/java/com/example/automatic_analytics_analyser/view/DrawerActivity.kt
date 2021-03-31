package com.example.automatic_analytics_analyser.view

import android.content.Intent
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.databinding.DataBindingUtil
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityBloodTestsBinding
import com.example.automatic_analytics_analyser.view.user.LoginActivity
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.descriptionText
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.mikepenz.materialdrawer.model.interfaces.nameText
import com.mikepenz.materialdrawer.widget.AccountHeaderView
import java.util.*

open class DrawerActivity : AbstractActivity() {
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var headerView: AccountHeaderView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val profile = ProfileDrawerItem().apply {
            nameText = "Mike Penz"; descriptionText = "mikepenz@gmail.com"; identifier =
            100; iconRes = R.drawable.ic_profile_image
        }

        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolbar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )

    }

    fun getDrawerItems(): Array<PrimaryDrawerItem> {
        val array: Array<PrimaryDrawerItem> = arrayOf(PrimaryDrawerItem().apply {
            nameRes = R.string.main_screen
            identifier = 1L
            iconRes = R.drawable.ic_logo_background

        },
            PrimaryDrawerItem().apply {
                nameRes = R.string.blood_tests_screen
                identifier = 2L
                iconRes = R.drawable.ic_blood_sample

            },
            PrimaryDrawerItem().apply {
                nameRes = R.string.medication_screen
                identifier = 3L
                iconRes = R.drawable.ic_medicine

            },
            PrimaryDrawerItem().apply {
                nameRes = R.string.calendar_screen
                identifier = 4L
                iconRes = R.drawable.ic_calendar

            },
            PrimaryDrawerItem().apply {
                nameRes = R.string.chat_screen
                identifier = 5L
                iconRes = R.drawable.ic_asistencia_medica

            })

        return array
    }

    fun executeDrawerAction(identifier: Long) {
        when (identifier) {
            1L -> startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
            2L -> startActivity(
                Intent(
                    this,
                    BloodTestsActivity::class.java
                )
            )
            6L -> logout()
        }
    }

    private fun logout() {
        preferences.edit().putBoolean("logged", false).apply()
        preferences.edit().remove("token").apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }
}