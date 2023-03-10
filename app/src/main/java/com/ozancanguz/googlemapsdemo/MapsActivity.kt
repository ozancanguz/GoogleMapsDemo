package com.ozancanguz.googlemapsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.ozancanguz.googlemapsdemo.databinding.ActivityMapsBinding
import com.ozancanguz.googlemapsdemo.misc.CameraAndViewPort
import com.ozancanguz.googlemapsdemo.misc.TypeAndStyle

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    // init type and style class
    private val typeAndStyle by lazy { TypeAndStyle() }

    // init camera and viewport class
    private val cameraAndViewPort by lazy { CameraAndViewPort() }

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
        // set map type
         typeAndStyle.setMapType(item,map)

        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        // 15 f how close the view
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10f))

        // camera new position
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraAndViewPort.Sdyney))

        map.uiSettings.apply {
            // zoom buttons enabled
            isZoomControlsEnabled=true
        }

        //
     //   map.setPadding(0,0,300,0)


        // type function
      typeAndStyle.setMapStyle(map,this)


    }



}