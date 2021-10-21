package com.gonzalo.airquality

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.gonzalo.airquality.databinding.ActivityMainBinding
import com.gonzalo.airquality.databinding.ActivityMainBinding.inflate
import com.gonzalo.airquality.model.geojsonmodel.GeoJsonAQI
import com.gonzalo.airquality.map.AirQualityMapController
import com.google.android.material.slider.Slider
import com.mapbox.mapboxsdk.Mapbox
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<AirQualityViewModel>()
    private lateinit var binding : ActivityMainBinding
    private lateinit var airQualityMapController: AirQualityMapController

    private val nightClockColor = Color.WHITE
    private val dayClockColor = Color.parseColor("#05121f")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        hideSystemUI()
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpSlider()
        airQualityMapController = AirQualityMapController(binding.mapView, viewModel.isDayTime())
        airQualityMapController.onCreate(savedInstanceState) {
            val airQualityObserver = Observer<List<GeoJsonAQI>> { newGeoJsonAQIList ->
                airQualityMapController.updateAQIs(newGeoJsonAQIList)
                airQualityMapController.redrawUI(0)
            }

            viewModel.airQualitiesLiveData.observe(this, airQualityObserver)

            viewModel.error.observe(this) {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

            val formatter = DateTimeFormatter.ofPattern("h:mm a")

            val currentTimeObserver = Observer<LocalDateTime> {
                binding.clock.text = formatter.format(it)
                binding.clock.setTextColor(getClockColor())
            }

            viewModel.currentTime.observe(this, currentTimeObserver)
        }
    }

    private fun getClockColor(): Int {
        return if(viewModel.isDayTime()) {
            dayClockColor
        } else {
            nightClockColor
        }
    }

    private fun setUpSlider() {
        binding.hourSelector.addOnChangeListener(Slider.OnChangeListener {
                slider, value, fromUser ->
                viewModel.setCurrentHourDelta(value.toInt())
                airQualityMapController.setDayTime(viewModel.isDayTime())
                airQualityMapController.redrawUI(value.toInt())
        })
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            //TODO test this in >= R
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    override fun onStart() {
        super.onStart()
        airQualityMapController.onStart()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAirQualityData()
        airQualityMapController.onResume()
    }

    override fun onPause() {
        super.onPause()
        airQualityMapController.onPause()
    }

    override fun onStop() {
        super.onStop()
        airQualityMapController.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        airQualityMapController.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        airQualityMapController.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        airQualityMapController.onDestroy()
    }
}