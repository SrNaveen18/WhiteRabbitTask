package com.example.whiterabbittask.ui.employee

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.whiterabbittask.BR
import com.example.whiterabbittask.R
import com.example.whiterabbittask.base.BaseFragment
import com.example.whiterabbittask.databinding.FragmentEmployeeBinding
import com.example.whiterabbittask.room.EmployeeItem
import com.example.whiterabbittask.ui.employee.adapter.EmployeeAdapter
import kotlinx.android.synthetic.main.fragment_employee.*

class EmployeeFragment : BaseFragment<FragmentEmployeeBinding, EmployeeViewModel>(),View.OnClickListener {

    override fun getViewModel(): EmployeeViewModel? =
        ViewModelProvider(this).get(EmployeeViewModel::class.java)

    override fun getBindingVariable(): Int = BR.employeeViewModel

    override fun getContentView(): Int = R.layout.fragment_employee

    private var employeeAdapter : EmployeeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViewModel()?.getCustomerData()?.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                fetchDataFromServer()
            } else {
                setAdapter(it)
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                employeeAdapter?.filter?.filter(newText)
                return true
            }
        })

    }

    private fun setAdapter(list: List<EmployeeItem>) {
        employeeAdapter = EmployeeAdapter(ArrayList(list),this,this)
        recyclerView.apply {
            adapter = employeeAdapter
        }
    }


    private fun fetchDataFromServer() {
        showProgress()
        getViewModel()?.makeApiCall()?.observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                getViewModel()?.addEmployees(it)
            }
            hideProgress()
        })
    }

    private fun showProgress() {
        progressbar.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progressbar.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txtName ->{
                val employeeItem = v.getTag(R.id.txtName) as EmployeeItem
                navigateTo(employeeItem)
            }
        }
    }

    private fun navigateTo(employeeItem: EmployeeItem) {
        val action = EmployeeFragmentDirections.actionEmployeeFragmentToEmployeeDetailsFragment(employeeItem)
        findNavController().navigate(action)
    }
}