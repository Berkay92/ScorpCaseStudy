package com.example.scorpcasestudy.core.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseBindingFragment<T : ViewDataBinding> : BaseFragment() {

    abstract fun applyBinding(binding: T)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : T = DataBindingUtil.inflate(inflater, getRootLayoutId(), container, false)
        return binding.apply {
            lifecycleOwner = this@BaseBindingFragment
            applyBinding(this)
            executePendingBindings()
        }.root
    }
}