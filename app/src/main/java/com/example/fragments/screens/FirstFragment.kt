package com.example.fragments.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.fragments.constants.MAIN
import com.example.fragments.R
import com.example.fragments.databinding.FragmentFirstBinding
import com.example.fragments.viewModels.CityModel


class FirstFragment : Fragment() {

    lateinit var binding: FragmentFirstBinding
    private val cityViewModel : CityModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater,container,false)
        val items = listOf("Choose the city", "Kyiv", "Lviv", "London", "Kharkiv","Dnipro")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem != "Choose the city") {
                    val bundle = Bundle().apply {
                        putString("selected_item", selectedItem)
                    }

                    MAIN.navController.navigate(R.id.action_firstFragment_to_secondFragment, bundle)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}