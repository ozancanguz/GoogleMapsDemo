package com.ozancanguz.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ozancanguz.googlemapsdemo.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_types_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
  when(item.itemId){
      R.id.normalmap -> {
          map.mapType=GoogleMap.MAP_TYPE_NORMAL
      }R.id.hybrid_map -> {
          map.mapType=GoogleMap.MAP_TYPE_HYBRID
      }R.id.satellite_map -> {
          map.mapType=GoogleMap.MAP_TYPE_SATELLITE
      }R.id.terrain_map -> {
          map.mapType=GoogleMap.MAP_TYPE_TERRAIN
      }
  }

        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // 15 f how close the view
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f))

        map.uiSettings.apply {
            // zoom buttons enabled
            isZoomControlsEnabled=true
        }

        //
     //   map.setPadding(0,0,300,0)
    }
}