package com.example.automatic_analytics_analyser.view.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.CalendarItemBinding
import com.example.automatic_analytics_analyser.databinding.ChatItemBinding
import com.example.automatic_analytics_analyser.model.Chat
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BindingChatItem(val chat: Chat) : AbstractBindingItem<ChatItemBinding>() {
    override val type: Int
        get() = R.id.fastadapter_item

    override fun bindView(binding: ChatItemBinding, payloads: List<Any>) {
        binding.chat = chat
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): ChatItemBinding {
        return ChatItemBinding.inflate(inflater, parent, false)
    }
}