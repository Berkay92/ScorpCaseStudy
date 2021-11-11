package com.example.scorpcasestudy.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scorpcasestudy.data.local.Person
import com.example.scorpcasestudy.databinding.ItemPersonBinding

class PersonAdapter : ListAdapter<Person, PersonViewHolder>(PersonModelDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = with(ItemPersonBinding.inflate(
        LayoutInflater.from(parent.context), parent, false)) {
        PersonViewHolder(this)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

class PersonViewHolder(private val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(person: Person) {
        binding.person = person
    }
}

object PersonModelDiff : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }
}