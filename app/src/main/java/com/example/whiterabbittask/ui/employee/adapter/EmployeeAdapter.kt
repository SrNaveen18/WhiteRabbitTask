package com.example.whiterabbittask.ui.employee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.whiterabbittask.R
import com.example.whiterabbittask.databinding.AdapterEmployeeBinding
import com.example.whiterabbittask.room.EmployeeItem
import com.example.whiterabbittask.ui.employee.EmployeeFragment
import kotlin.collections.ArrayList

class EmployeeAdapter(
    item: ArrayList<EmployeeItem>,
    private val employeeFragment: EmployeeFragment,
    private val listener: View.OnClickListener
) :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>(), Filterable {

    private var filterList = ArrayList<EmployeeItem>()
    private var actualList = ArrayList<EmployeeItem>()

    init {
        actualList = item
        filterList = actualList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view: AdapterEmployeeBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_employee,
            parent,
            false
        )
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.onBind(filterList[position], employeeFragment, listener)
    }

    override fun getItemCount(): Int = filterList.size


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    filterList = actualList
                } else {
                    val resultList = ArrayList<EmployeeItem>()
                    for (row in actualList) {
                        if (row.name!!.toLowerCase().contains(charSearch.toLowerCase())) {
                            resultList.add(row)
                        }
                    }
                    filterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<EmployeeItem>
                notifyDataSetChanged()
            }

        }
    }

    inner class EmployeeViewHolder(private val adapterEmployeeBinding: AdapterEmployeeBinding) :
        RecyclerView.ViewHolder(adapterEmployeeBinding.root) {

        fun onBind(
            employeeItem: EmployeeItem,
            employeeFragment: EmployeeFragment,
            listener: View.OnClickListener
        ) {
            adapterEmployeeBinding.employeeItem = employeeItem

            Glide.with(employeeFragment)
                .load(employeeItem.profileImage)
                .into(adapterEmployeeBinding.imgView)

            adapterEmployeeBinding.txtName.setTag(R.id.txtName, employeeItem)
            adapterEmployeeBinding.txtName.setOnClickListener(listener)
        }
    }

}