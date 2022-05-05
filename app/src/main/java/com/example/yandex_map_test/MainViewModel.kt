package com.example.yandex_map_test

import androidx.lifecycle.ViewModel
import com.example.yandex_map_test.data.MainRepository
import com.yandex.mapkit.geometry.Point

class MainViewModel: ViewModel() {



    private val repository = MainRepository()


    fun fetchLukes(): List<Luke> {
        return repository.getPoints()
    }
}