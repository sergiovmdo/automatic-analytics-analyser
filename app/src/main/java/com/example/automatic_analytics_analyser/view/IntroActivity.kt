package com.example.automatic_analytics_analyser.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import android.preference.PreferenceManager
import com.example.automatic_analytics_analyser.view.user.LoginActivity

class IntroActivity : DaggerAppCompatActivity() {

    private lateinit var preferences: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        if (!preferences.getBoolean("logged", false)) {
            startActivityForResult(Intent(this, LoginActivity::class.java), 40)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 40) {
            if (resultCode == Activity.RESULT_OK) {
                preferences.edit().putBoolean("logged", true).apply()
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }
    }

}