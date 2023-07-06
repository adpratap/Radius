package com.noreplypratap.radius.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noreplypratap.radius.R
import com.noreplypratap.radius.databinding.FragmentOthersBinding
import com.noreplypratap.radius.databinding.FragmentResultBinding
import com.noreplypratap.radius.utilities.Helpers.uOthers
import com.noreplypratap.radius.utilities.Helpers.uRooms
import com.noreplypratap.radius.utilities.Helpers.uTypes
import com.noreplypratap.radius.utilities.Helpers.userDatas


class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = userDatas

        binding.tvType.text = "Property Type = ${uTypes?.name}"
        binding.tvRooms.text = "Rooms = ${uRooms?.name}"
        binding.Others.text = "Other Facilities = ${uOthers?.name}"

        if (data != null) {
            binding.tvType.text = "Property Type = ${data.pType?.name}"
            binding.tvRooms.text = "Rooms = ${data.noOfRoom?.name}"
            binding.Others.text = "Other Facilities = ${data.fOthers?.name}"
        }




    }

}