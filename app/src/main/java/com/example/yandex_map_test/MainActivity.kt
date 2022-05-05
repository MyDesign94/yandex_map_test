package com.example.yandex_map_test

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yandex_map_test.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.search.search_layer.PlacemarkListener
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider

class MainActivity : AppCompatActivity() {


    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("03cc843d-28b1-4751-adf3-4b4213eec37c")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        binding.mapview.map.move(
            CameraPosition(Point(59.940082, 30.312814), 20.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
        initViewModel()
    }

    private fun initViewModel() {
        val lukes = viewModel.fetchLukes()

        binding.mapview.map.mapObjects.addPlacemark(
            lukes.first().point,
        )
    }


    override fun onStart() {
        super.onStart()
        binding.mapview.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapview.onStop()
        MapKitFactory.getInstance().onStop()
    }
}