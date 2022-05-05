package com.example.yandex_map_test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.yandex_map_test.databinding.ActivityMainBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.IconStyle
import com.yandex.mapkit.map.MapObject
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.runtime.image.ImageProvider

class MainActivity : AppCompatActivity(), MapObjectTapListener {


    private val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("03cc843d-28b1-4751-adf3-4b4213eec37c")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        binding.mapview.map.move(
            CameraPosition(Point(59.940082, 30.312814), 10.0f, 0.0f, 0.0f),
            Animation(Animation.Type.SMOOTH, 0F),
            null
        )
        initViewModel()
    }

    private fun initViewModel() {
        val lukes = viewModel.fetchLukes()



        val points = lukes.map {
            val drawable = if (it.status == LukeStatus.CLOSED) R.drawable.luke_closed else R.drawable.luke
            binding.mapview.map.mapObjects.addPlacemark(
                it.point,
                ImageProvider.fromResource(this, drawable),
                IconStyle().apply {
                    scale = 0.05f
                }
            ).apply {
                userData = it
                addTapListener(this@MainActivity)
            }
        }

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

    override fun onMapObjectTap(mapObject: MapObject, p1: Point): Boolean {
        return if (mapObject.userData !is Luke) false
        else {
            Toast.makeText(this, "${(mapObject.userData as Luke).status}", Toast.LENGTH_SHORT).show()
            true
        }
    }
}