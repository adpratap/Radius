package com.noreplypratap.radius.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noreplypratap.radius.R
import com.noreplypratap.radius.databinding.FragmentFacilitiesBinding
import com.noreplypratap.radius.utilities.Helpers.dataAll
import com.noreplypratap.radius.utilities.Helpers.uTypes
import com.noreplypratap.radius.view.adapters.FacilityAdapter
import com.noreplypratap.radius.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FacilitiesFragment : Fragment() {

    private var _binding: FragmentFacilitiesBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFacilitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE

        binding.lifecycleOwner = viewLifecycleOwner
        //binding.viewModel = mainViewModel

        mainViewModel.data.observe(viewLifecycleOwner) { data ->
            data.data?.let {
                dataAll = it
                val facilityAdapter = FacilityAdapter(it.facilities[0].options)
                binding.rvPropertyTypes.adapter = facilityAdapter
                binding.progressBar.visibility = View.GONE

                facilityAdapter.setOnClickListener {
                    uTypes = it
                    findNavController().navigate(R.id.action_facilitiesFragment_to_roomsFragment)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}