package com.example.employmenttest.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.employmenttest.R
import com.example.employmenttest.database.ChannelModel
import com.example.employmenttest.databinding.DialogCreateYourOwnBinding
import com.example.employmenttest.viewmodel.CreateChannelViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateYourOwnDialog : BottomSheetDialogFragment() {

    private var _binding: DialogCreateYourOwnBinding? = null
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

        _binding =
            DialogCreateYourOwnBinding.inflate(LayoutInflater.from(context), container, false)

        binding.apply {

            close.setOnClickListener { navController.navigateUp() }

            connectToChannel.setOnClickListener {
                if (editText.text.isNotEmpty()) {
                    viewModel.channelViewModel.insert(ChannelModel(editText.text.toString(), true))
                    Toast.makeText(context, "Channel Name Added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Please Write Channel Name", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }

}