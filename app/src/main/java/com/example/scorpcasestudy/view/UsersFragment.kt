package com.example.scorpcasestudy.view

import androidx.lifecycle.ViewModelProvider
import android.view.View
import androidx.lifecycle.Observer
import com.example.scorpcasestudy.R
import com.example.scorpcasestudy.core.fragment.BaseFragment
import com.example.scorpcasestudy.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*

class UsersFragment : BaseFragment() {

    private lateinit var viewModel: UsersViewModel

    private val personAdapter by lazy { PersonAdapter() }

    override fun getRootLayoutId() = R.layout.users_fragment

    override fun initView(rootView: View) {
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        initRecyclerView()
        observeData()
    }

    private fun initRecyclerView() {
        peopleRecyclerView.adapter = personAdapter
    }

    private fun observeData() {
        viewModel.lastResponse.observe(this, Observer { personList ->
            personAdapter.submitList(personList)
        })
        viewModel.lastError.observe(this, Observer { errorDescription ->

        })
    }

}