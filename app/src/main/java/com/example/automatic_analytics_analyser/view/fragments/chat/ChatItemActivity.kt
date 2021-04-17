package com.example.automatic_analytics_analyser.view.fragments.chat

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityChatElementBinding
import com.example.automatic_analytics_analyser.view.AbstractActivity

class ChatItemActivity : AbstractActivity() {
    private lateinit var binding : ActivityChatElementBinding

    private val viewModel: ChatItemViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ChatItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_element)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }
}