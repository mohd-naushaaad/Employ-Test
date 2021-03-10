package com.example.employmenttest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.employmenttest.adapters.ListAdapter
import com.example.employmenttest.database.ChannelModel
import com.example.employmenttest.database.ChannelViewModel

class CreateChannelViewModel(application: Application) : AndroidViewModel(application) {

    val channelViewModel: ChannelViewModel = ChannelViewModel(application)
    var channelModel: List<ChannelModel> = channelViewModel.getAll()
    var adapter: ListAdapter = ListAdapter(application, channelModel)

}