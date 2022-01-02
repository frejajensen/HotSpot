package com.example.hotspot.view.createProfilePackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hotspot.R
import com.example.hotspot.databinding.CreateProfileGenderFragmentBinding
import com.example.hotspot.databinding.FragmentProfileTextBinding


class FragmentProfileText : Fragment() {
    private val viewModel: SharedViewModelCreateProfile by activityViewModels()

    private var _binding: FragmentProfileTextBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileTextBinding.inflate(inflater, container, false)


        binding.continueButton.setOnClickListener {
            viewModel.setProfileText(binding.firstNameText.text.toString())
            findNavController().navigate(R.id.action_fragmentProfileText_to_createProfileUploadImageFragment)
        }


        return binding.root
    }


}