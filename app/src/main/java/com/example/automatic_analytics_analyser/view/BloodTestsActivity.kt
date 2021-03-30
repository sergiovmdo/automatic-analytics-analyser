package com.example.automatic_analytics_analyser.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityBloodTestsBinding
import com.mikepenz.materialdrawer.iconics.iconicsIcon
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.Nameable
import com.mikepenz.materialdrawer.model.interfaces.nameRes

class BloodTestsActivity : AbstractActivity() {
    private lateinit var binding : ActivityBloodTestsBinding

    private val viewModel: BloodTestsViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(BloodTestsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        binding = DataBindingUtil.setContentView(this, R.layout.activity_blood_tests)

        binding = ActivityBloodTestsBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding.slider.apply {
            itemAdapter.add(
                PrimaryDrawerItem().apply {
                    nameRes = R.string.welcome

                },
                SecondaryDrawerItem().apply {
                    nameRes = R.string.app_name

                }
            )
            onDrawerItemClickListener = { _, drawerItem, _ ->
                if (drawerItem is Nameable) {
                    Toast.makeText(this@BloodTestsActivity, (drawerItem as Nameable).name!!.getText(this@BloodTestsActivity), Toast.LENGTH_SHORT).show()
                }
                false
            }
            setSavedInstance(savedInstanceState)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }
}