package com.noreplypratap.radius.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noreplypratap.radius.R
import com.noreplypratap.radius.databinding.FragmentRoomsBinding
import com.noreplypratap.radius.model.Option
import com.noreplypratap.radius.utilities.Helpers.dataAll
import com.noreplypratap.radius.utilities.Helpers.uRooms
import com.noreplypratap.radius.utilities.Helpers.uTypes
import com.noreplypratap.radius.utilities.getNewOptionsList
import com.noreplypratap.radius.view.adapters.FacilityAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val data = dataAll
        var newOptionsList: MutableList<Option> = mutableListOf()

        uTypes?.id?.let {
            if (data != null) {
                newOptionsList = getNewOptionsList(it,data, data.facilities[1].options,1) as MutableList<Option>
            }
        }

        if(newOptionsList.isNullOrEmpty()){
            val delayMillis = 2000L // Delay of 2 seconds
            CoroutineScope(Dispatchers.Main).launch {
                delay(delayMillis)
                // Code to be executed after the delay
                findNavController().navigate(R.id.action_roomsFragment_to_othersFragment)
            }
            Toast.makeText(requireContext(), "Room Not Available !!! Skipped Room Selection in 2 Sec", Toast.LENGTH_SHORT).show()
        }else{
            val facilityAdapter = FacilityAdapter(newOptionsList)
            binding.rvNoOfRoom.adapter = facilityAdapter

            facilityAdapter.setOnClickListener {
                uRooms = it
                findNavController().navigate(R.id.action_roomsFragment_to_othersFragment)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}