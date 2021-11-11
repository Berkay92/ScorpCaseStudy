package com.example.scorpcasestudy.view

import androidx.lifecycle.ViewModelProvider
import android.view.View
import com.example.scorpcasestudy.R
import com.example.scorpcasestudy.core.fragment.BaseFragment
import com.example.scorpcasestudy.viewmodel.UsersViewModel

class UsersFragment : BaseFragment() {

    private lateinit var viewModel: UsersViewModel

    override fun getRootLayoutId() = R.layout.users_fragment

    override fun initView(rootView: View) {
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }

}