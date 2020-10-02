package com.example.whiterabbittask.ui.employeedetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.whiterabbittask.BR
import com.example.whiterabbittask.R
import com.example.whiterabbittask.base.BaseFragment
import com.example.whiterabbittask.databinding.FragmentEmployeeDetailsBinding
import com.example.whiterabbittask.room.EmployeeItem
import kotlinx.android.synthetic.main.fragment_employee_details.*

class EmployeeDetailsFragment :
    BaseFragment<FragmentEmployeeDetailsBinding, EmployeeDetailsViewModel>() {
    override fun getViewModel(): EmployeeDetailsViewModel? =
        ViewModelProvider(this).get(EmployeeDetailsViewModel::class.java)

    override fun getBindingVariable(): Int = BR.employeeViewModel

    override fun getContentView(): Int = R.layout.fragment_employee_details

    private val navArgs by navArgs<EmployeeDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getViewModel()?.employeeItem?.value = navArgs.EmployeeItem
        getViewModel()?.employeeItem?.observe(viewLifecycleOwner, Observer {
            setValues(it)
        })
    }

    private fun setValues(employeeItem: EmployeeItem){
        Glide.with(requireActivity())
            .load(employeeItem.profileImage)
            .into(image)
        txtName.text = employeeItem.name
        txtEmail.text = employeeItem.email
    }
}