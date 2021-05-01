package com.example.automatic_analytics_analyser.view.fragments.chat

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ActivityChatElementBinding
import com.example.automatic_analytics_analyser.model.ChatItem
import com.example.automatic_analytics_analyser.view.AbstractActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter

class ChatItemActivity : AbstractActivity() {
    private val itemAdapter = ItemAdapter<BindingMessageItem>()
    private lateinit var binding: ActivityChatElementBinding

    private val viewModel: ChatItemViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ChatItemViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat_element)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        Toast.makeText(this, intent.extras?.get("chatId").toString(), Toast.LENGTH_LONG).show()

        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }

        viewModel.chat.observe(this, Observer {
            populateChat(it)
        })

        val id = intent.getLongExtra("chatId", 0)
        viewModel.getChat(id)
        viewModel.onChatOpen(id)

        binding.sendButton.setOnClickListener {
            if (!binding.messageContent.text.isNullOrEmpty()) {
                viewModel.createMessage()
                binding.messageContent.text!!.clear()
                hideKeyboard()
            }
        }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onDestroy() {
        viewModel.onChatClose()
        super.onDestroy()
    }

    private fun populateChat(data: ChatItem) {
        val items = data.messages.map { BindingMessageItem(it, data.user.dni) }
        itemAdapter.setNewList(items)
        binding.recycler.scrollToPosition(data.messages.size - 1)
    }
}