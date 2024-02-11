package com.example.resturantrowviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resturantrowviews.datalevel.MyRepository
import com.example.resturantrowviews.datalevel.restItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ResturantRowsViewModel : ViewModel() {
    private val repository = MyRepository()

    val resturantList : StateFlow<List<restItem>> = repository.getResturants()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}
//jhhhh