package com.rivas.linternaripense

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.rivas.linternaripense.databinding.ActivityMainBinding


import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import android.view.View;

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private var cameraManager: CameraManager? = null
    private var getCameraID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);


        // cameraManager to interact with camera devices
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        // Exception is handled, because to check whether
        // the camera resource is being used by another
        // service or not.
        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager!!.cameraIdList[0]
        } catch (e: CameraAccessException) {
            e.printStackTrace()
        }

    }

    fun toggleFlashLightOn() {
        try {
            // true sets the torch in ON mode
            cameraManager!!.setTorchMode(getCameraID!!, true)

            // Inform the user about the flashlight
            // status using Toast message
        } catch (e: CameraAccessException) {
            // prints stack trace on standard error
            // output error stream
            e.printStackTrace()
        }
    }

    fun toggleFlashLightOff() {
        try {
            // true sets the torch in ON mode
            cameraManager!!.setTorchMode(getCameraID!!, false)

            // Inform the user about the flashlight
            // status using Toast message
        } catch (e: CameraAccessException) {
            // prints stack trace on standard error
            // output error stream
            e.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}