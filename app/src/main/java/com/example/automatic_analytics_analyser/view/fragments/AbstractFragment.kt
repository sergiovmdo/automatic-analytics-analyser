package com.example.automatic_analytics_analyser.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.automatic_analytics_analyser.view.MainActivity
import com.mikepenz.fastadapter.binding.BindingViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractFragment : DaggerFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            context?.startActivity(Intent(context, MainActivity::class.java))
            requireActivity().finishAfterTransition()
        }
    }

    inline fun <reified T : ViewBinding> RecyclerView.ViewHolder.asBinding(block: (T) -> View): View? {
        return if (this is BindingViewHolder<*> && this.binding is T) {
            block(this.binding as T)
        } else {
            null
        }
    }

}