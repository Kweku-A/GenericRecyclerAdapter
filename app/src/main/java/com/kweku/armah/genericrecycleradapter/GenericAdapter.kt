package com.kweku.armah.genericrecycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kweku.armah.genericrecycleradapter.databinding.ItemLayoutBinding

/**Simple and reusable recyclerView adapter*/
class GenericAdapter<T, U : ViewDataBinding>(@LayoutRes private val resId: Int) :
    RecyclerView.Adapter<GenericAdapter<T, U>.GenericViewHolder>() {
    var dataModelList: MutableList<T> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var listener: GenericDataAndBindingInterface<T, U>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val itemLayoutBinding = DataBindingUtil.inflate<U>(
            LayoutInflater.from(parent.context),
            resId, parent, false
        )
        return GenericViewHolder(itemLayoutBinding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(dataModelList[position], holder.binding)
    }

    override fun getItemCount(): Int = dataModelList.size

    inner class GenericViewHolder(val binding: U) : RecyclerView.ViewHolder(binding.root) {

        fun bind(dataModel: T, binding: U) {
            listener?.bindingData(dataModel, binding)
        }
    }

    interface GenericDataAndBindingInterface<T, U> {
        fun bindingData(dataModel: T, binding: U)
    }
}