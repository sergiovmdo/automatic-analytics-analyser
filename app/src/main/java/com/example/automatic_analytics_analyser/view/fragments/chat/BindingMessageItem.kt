package com.example.automatic_analytics_analyser.view.fragments.chat

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ChatItemBinding
import com.example.automatic_analytics_analyser.databinding.MessageItemBinding
import com.example.automatic_analytics_analyser.model.Message
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class BindingMessageItem(val message: Message, val id : String) :
    AbstractBindingItem<MessageItemBinding>() {
    override val type: Int
        get() = R.id.fastadapter_item

    override fun bindView(binding: MessageItemBinding, payloads: List<Any>) {
        binding.message = message
        if (id == message.user.dni) {
            binding.nurseMessage.visibility = View.GONE
            binding.pacientMessage.visibility = View.VISIBLE
        }
        else {
            binding.pacientMessage.visibility = View.GONE
            binding.nurseMessage.visibility = View.VISIBLE
        }
    }

    override fun createBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): MessageItemBinding {
        return MessageItemBinding.inflate(inflater, parent, false)
    }
}
