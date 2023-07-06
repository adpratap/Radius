package com.noreplypratap.radius.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.noreplypratap.radius.R
import com.noreplypratap.radius.databinding.FragmentOthersBinding
import com.noreplypratap.radius.model.Option
import com.noreplypratap.radius.model.SelectedList
import com.noreplypratap.radius.utilities.Helpers.dataAll
import com.noreplypratap.radius.utilities.Helpers.uOthers
import com.noreplypratap.radius.utilities.Helpers.uRooms
import com.noreplypratap.radius.utilities.Helpers.uTypes
import com.noreplypratap.radius.utilities.Helpers.userDatas
import com.noreplypratap.radius.utilities.getNewOptionsList
import com.noreplypratap.radius.view.adapters.FacilityAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OthersFragment : Fragment() {


    private var _binding: FragmentOthersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOthersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        val data = dataAll
        var newOthersOptionsList: MutableList<Option> = mutableListOf()

        uTypes?.id?.let {
            if (data != null) {
                newOthersOptionsList = getNewOptionsList(it,data, data.facilities[2].options,2) as MutableList<Option>
            }
        }

        if(newOthersOptionsList.isNullOrEmpty()){
            val delayMillis = 2000L // Delay of 2 seconds
            CoroutineScope(Dispatchers.Main).launch {
                delay(delayMillis)
                findNavController().navigate(R.id.action_othersFragment_to_resultFragment)
            }
            Toast.makeText(requireContext(), "Not Available !!! Skipped Selection in 2 Sec", Toast.LENGTH_SHORT).show()
        }else{
            val facilityAdapter = FacilityAdapter(newOthersOptionsList)
            binding.rvOthersFacilities.adapter = facilityAdapter

            facilityAdapter.setOnClickListener {
                uOthers = it
                userDatas = SelectedList(uTypes, uRooms, uOthers)
                findNavController().navigate(R.id.action_othersFragment_to_resultFragment)
                Toast.makeText(requireContext(), userDatas.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}