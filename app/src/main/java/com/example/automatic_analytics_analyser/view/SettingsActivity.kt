package com.example.automatic_analytics_analyser.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivitySettingsBinding
import com.example.automatic_analytics_analyser.view.user.LoginActivity


class SettingsActivity : AbstractActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val viewModel: SettingsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val spinner = binding.languagesDropdown

        spinner.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, _, _ ->
                viewModel.changeLanguage(spinner.text.toString())
            }

        spinner.setAdapter(
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                resources.getStringArray(R.array.languages)
            )
        )

        viewModel.user.observe(this, Observer {
            spinner.setText(viewModel.getUserLanguage(), false)
        })

        viewModel.changeLanguage.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this, getString(R.string.changeLanguageMessage), Toast.LENGTH_LONG)
                    .show()
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle(R.string.settings)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.logout -> {
                logout()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings_menu, menu)
        var menuItem = menu!!.findItem(R.id.logout)
        tintMenuIcon(menuItem, R.color.white)
        return true
    }

    private fun logout() {
        preferences.edit().putBoolean("logged", false).apply()
        preferences.edit().remove("token").apply()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun tintMenuIcon(item: MenuItem, @ColorRes color: Int) {
        var drawable = item.icon
        var wrappedDrawable = DrawableCompat.wrap(drawable)
        DrawableCompat.setTint(wrappedDrawable, this.resources.getColor(color))
    }

}