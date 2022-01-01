package com.example.hotspot.view.createProfile

import android.app.DatePickerDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspot.R
import com.example.hotspot.databinding.CreateProfileAgeFragmentBinding
import com.example.hotspot.databinding.CreateProfileNameFragmentBinding
import java.util.*

class CreateProfileAgeFragment : Fragment() {

    private var _binding: CreateProfileAgeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var datePicker: DatePickerDialog

    private val viewModel: CreateProfileSharedViewModel by activityViewModels()

    private var cday: Int = 0
    private var cmonth: Int = 0
    private var cyear: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CreateProfileAgeFragmentBinding.inflate(inflater, container, false)


        binding.continueButton.setOnClickListener{
            findNavController().navigate(R.id.action_createProfileAgeFragment_to_createProfileGenderFragment)
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.dateButton.setOnClickListener{
            initDatePicker()
            viewModel.setDayOfBirth(cday)
            viewModel.setMonth(cmonth)
            viewModel.setYear(cyear)
        }

        viewModel.getDateString().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.dateButton.text = it.toString()
        })



    }

    override fun onResume() {
        super.onResume()




    }

    private fun initDatePicker(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this.requireContext(), DatePickerDialog.OnDateSetListener { view, myear, mMonth, mdayOfMonth ->
            val mmonth = mMonth +1
            setDateForDatabase(mdayOfMonth,mmonth,myear)
            val dateString = setDateString(mdayOfMonth, mmonth,myear)
            binding.dateButton.setText(dateString)
            viewModel.setdateString(dateString)
        }, year, month, day)

        dpd.show()
    }

    private fun setDateForDatabase(xday : Int, xmonth : Int, xyear : Int){
        cday = xday
        cmonth = xmonth
        cyear = xyear
    }

    private fun setDateString(day : Int, month : Int, year : Int): String{
        if(month < 10 && day< 10) {
            return "0$day/0$month/$year"
        }
        else if(month < 10 ){
            return "$day/0$month/$year"
        }
        else if(day < 10){
            return "0$day/$month/$year"
        }
        else
            return "$day/$month/$year"
        return "null"
    }

}