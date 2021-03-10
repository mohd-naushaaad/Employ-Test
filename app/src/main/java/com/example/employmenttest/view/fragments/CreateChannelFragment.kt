package com.example.employmenttest.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.employmenttest.R
import com.example.employmenttest.database.ChannelViewModel
import com.example.employmenttest.databinding.FragmentCreateChannelBinding
import com.example.employmenttest.viewmodel.CreateChannelViewModel


class CreateChannelFragment : Fragment(R.layout.fragment_create_channel) {

    private var _binding: FragmentCreateChannelBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var viewModel: CreateChannelViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        viewModel = ViewModelProvider(requireActivity())[CreateChannelViewModel::class.java]

        _binding = FragmentCreateChannelBinding.inflate(inflater, container, false)

        binding.apply {
            addMoreRelatedTo.setOnClickListener {
                val action =
                    CreateChannelFragmentDirections.actionCreateChannelFragmentToAddRelatedToFragment()
                navController.navigate(action)
            }
        }

        addRelatedToChannels()

        return binding.root
    }

    private fun addRelatedToChannels() {
        val channelModel = viewModel.channelViewModel.getAll()

        for (i in channelModel) {
            addChannel(i.name, binding.addRelatedToLinearLayout)
        }
    }


    private fun addChannel(answer: String, viewGroup: ViewGroup) {
        val view: View =
            LayoutInflater.from(requireContext())
                .inflate(R.layout.custom_channel_layout, viewGroup, false)

        val channelName = view.findViewById<TextView>(R.id.channel_name)
        channelName.text = answer
        viewGroup.addView(view)

        val close = view.findViewById<ImageView>(R.id.close_channel)
        close.setOnClickListener {
            viewGroup.removeView(view)
            val channelModel = viewModel.channelViewModel.get(channelName.text.toString())
            viewModel.channelViewModel.delete(channelModel)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}