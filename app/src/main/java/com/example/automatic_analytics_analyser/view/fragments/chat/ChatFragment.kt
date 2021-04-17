package com.example.automatic_analytics_analyser.view.fragments.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.afollestad.materialdialogs.datetime.dateTimePicker
import com.afollestad.materialdialogs.input.input
import com.example.automatic_analytics_analyser.R
import com.example.automatic_analytics_analyser.databinding.ChatItemBinding
import com.example.automatic_analytics_analyser.databinding.FragmentChatBinding
import com.example.automatic_analytics_analyser.model.Chat
import com.example.automatic_analytics_analyser.view.MainActivity
import com.example.automatic_analytics_analyser.view.fragments.AbstractFragment
import com.google.android.material.textfield.TextInputEditText
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.binding.BindingViewHolder
import com.mikepenz.fastadapter.listeners.ClickEventHook

class ChatFragment : AbstractFragment() {
    private val itemAdapter = ItemAdapter<BindingChatItem>()
    private lateinit var binding: FragmentChatBinding

    private val viewModel: ChatViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentChatBinding>(
            inflater,
            R.layout.fragment_chat,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Move to AbstractRecyclerFragment?
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val fastAdapter = FastAdapter.with(itemAdapter)
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }

        viewModel.chats.observe(viewLifecycleOwner, Observer {
            populateChats(it)
        })

        fastAdapter.addEventHook(object : ClickEventHook<BindingChatItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return viewHolder.asBinding<ChatItemBinding> {
                    it.chatElement
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<BindingChatItem>,
                item: BindingChatItem
            ) {
                val intent = Intent(requireActivity(), ChatItemActivity::class.java)
                intent.putExtra("chatId", item.chat.id)
                startActivity(intent)
            }
        })

        //Open dialog when clicking the FAB button
        binding.newChatButton.setOnClickListener {
            var messageInput: TextInputEditText? = null
            var dropdown: AutoCompleteTextView? = null
            val dialog = MaterialDialog(it.context).title(R.string.sendMessageTitle)
                .customView(R.layout.new_chat_dialog).positiveButton(R.string.send) {
                val messageContent = messageInput?.text?.toString()
                val speciality = dropdown?.text?.toString()
                viewModel.createMessage(messageContent, speciality)
            }.negativeButton(R.string.close)

            val customView = dialog.getCustomView()
            dropdown = customView.findViewById<AutoCompleteTextView>(R.id.specialitiesDropdown)
            dropdown.setAdapter(
                ArrayAdapter(
                    it.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    resources.getStringArray(R.array.languages)
                )
            )

            messageInput = customView.findViewById<TextInputEditText>(R.id.messageContent)

            dialog.show()
        }

        viewModel.createdChat.observe(viewLifecycleOwner, Observer {
            it?.let { chat ->
                val intent = Intent(requireActivity(), ChatItemActivity::class.java)
                intent.putExtra("chatId", chat.id)
                startActivity(intent)
            }
        })

        viewModel.refreshChats()
    }

    private fun populateChats(data: List<Chat>) {
        binding.refreshChats.isRefreshing = false
        val items = data.map {
            BindingChatItem(
                it
            )
        }
        itemAdapter.setNewList(items)
    }

    inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.asBinding(block: (T) -> View): View? {
        return if (this is BindingViewHolder<*> && this.binding is T) {
            block(this.binding as T)
        } else {
            null
        }
    }
}