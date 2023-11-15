package com.example.fragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CityModel : ViewModel() {
    val city : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
}
