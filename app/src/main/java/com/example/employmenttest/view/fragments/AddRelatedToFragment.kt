package com.example.employmenttest.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.employmenttest.adapters.ListAdapter
import com.example.employmenttest.databinding.FragmentAddRelatedToBinding
import com.example.employmenttest.viewmodel.CreateChannelViewModel

class AddRelatedToFragment : Fragment() {

    private var _binding: FragmentAddRelatedToBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewModel: CreateChannelViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        navController = findNavController()
        _binding = FragmentAddRelatedToBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[CreateChannelViewModel::class.java]

        val adapter = ListAdapter(requireContext(), viewModel.channelViewModel.getAll())

        binding.apply {
            createYourOwn.setOnClickListener {
                val action =
                    AddRelatedToFragmentDirections.actionAddRelatedToFragmentToCreateYourOwnFragment()
                navController.navigate(action)
            }

            searchedList.adapter = adapter

            search.setOnClickListener {
                if (binding.searchEditText.text.isNotEmpty()) {
                    adapter.filter.filter(binding.searchEditText.text.toString())
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}