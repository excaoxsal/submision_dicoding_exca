package com.example.bfaa2_exca.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bfaa2_exca.databinding.ItemRowUserBinding
import com.example.bfaa2_exca.model.PersonModel

class PersonAdapter (private val personModels : ArrayList<PersonModel>, private val clickListener : (String, View) -> Unit) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){
    inner class PersonViewHolder(private val binding: ItemRowUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(personModel: PersonModel, click: (String, View) -> Unit){
            binding.data = personModel
            binding.root.transitionName = personModel.status
            binding.root.setOnClickListener {click(personModel.status, binding.root)}
        }
    }

    fun setData(items:List<PersonModel>){
        personModels.apply {
            clear()
            addAll(items)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) =holder.bind(personModels[position], clickListener)

    override fun getItemCount(): Int = personModels.size
}