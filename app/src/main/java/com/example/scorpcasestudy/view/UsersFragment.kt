package com.example.scorpcasestudy.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.scorpcasestudy.R
import com.example.scorpcasestudy.core.fragment.BaseBindingFragment
import com.example.scorpcasestudy.databinding.UsersFragmentBinding
import com.example.scorpcasestudy.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.users_fragment.*

class UsersFragment : BaseBindingFragment<UsersFragmentBinding>() {

    private lateinit var viewModel: UsersViewModel

    private val personAdapter by lazy { PersonAdapter() }

    override fun getRootLayoutId() = R.layout.users_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }

    override fun initView(rootView: View) {
        initViews()
        initPullToRefresh()
        observeData()
    }

    override fun applyBinding(binding: UsersFragmentBinding) {
        binding.usersViewModel = viewModel
    }

    private fun initViews() {
        peopleRecyclerView.adapter = personAdapter
        peopleRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val offset = recyclerView.computeVerticalScrollOffset()
                    val extent = recyclerView.computeVerticalScrollExtent()
                    val range = recyclerView.computeVerticalScrollRange()
                    if (offset + extent == range) {
                        // Ready to get next part for pagination
                        viewModel.fetchData()
                    }
                }
            }
        })
        btnRetry.setOnClickListener {
            viewModel.fetchData(true)
        }
    }

    private fun initPullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchData(true)
        }
    }

    private fun observeData() {
        viewModel.lastResponse.observe(this, Observer { personList ->
            personAdapter.submitList(personList)
            swipeRefreshLayout.isRefreshing = false
        })
        viewModel.lastError.observe(this, Observer { errorDescription ->
            errorMessage.text = errorDescription
            swipeRefreshLayout.isRefreshing = false
        })
    }

}