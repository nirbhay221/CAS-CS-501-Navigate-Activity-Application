package com.example.navigateactivitiesapp

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.Toast
import java.lang.Math.abs

class MainActivity : AppCompatActivity() ,GestureDetector.OnGestureListener,SensorEventListener {
    private lateinit var gestureDetector: GestureDetector
    private var sensor:Sensor ?= null
    private var sensorManager: SensorManager ?=null
    var x1:Float = 0.0f
    var x2:Float = 0.0f
    var y1:Float = 0.0f
    var y2:Float = 0.0f

    var xOld:Float = 0.0f
    var yOld:Float = 0.0f
    var zOld:Float = 0.0f
    var threshold:Float = 3000.0f
    var oldTime:Long = 0
    private lateinit var imageViewed: ImageView
    companion object{
        const val MIN_DISTANCE = 150
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape)
        } else {
            setContentView(R.layout.activity_main)
        }
        gestureDetector = GestureDetector(this,this)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        imageViewed =findViewById<ImageView>(R.id.imageView3)


    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_landscape)

            Toast.makeText(this,"changed to landscape",Toast.LENGTH_LONG)
        } else {
            setContentView(R.layout.activity_main)
            Toast.makeText(this,"changed to portrait",Toast.LENGTH_LONG)
        }}
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        when(event?.action){
            0->{
                x1 = event.x
                y1 = event.y
            }
            1->{
                x2 = event.x
                y2 = event.y

                val valueX : Float = x2 - x1
                val valueY : Float = y2 - y1

                if(abs(valueX)> MIN_DISTANCE){
                    if(x2>x1){
                        moveToRightScreen()
                    }
                    else if (x2<x1){
                        moveToLeftScreen()
                    }
                }
                if(abs(valueY)> MIN_DISTANCE){
                    if(y2>y1){
                        moveToBottomScreen()
                    }
                    else if(y1>y2){
                        moveToTopScreen()
                    }
                }

            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        //TODO("Not yet implemented")
        return false

    }

    override fun onShowPress(e: MotionEvent) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onResume(){
        super.onResume()
        sensorManager!!.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        //TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        //TODO("Not yet implemented")
        return false
    }
    private fun moveToTopScreen(){

        val intent = Intent(this,MainActivity4::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Top Activity",Toast.LENGTH_LONG)

    }
    private fun moveToBottomScreen(){
        val intent = Intent(this,MainActivity5::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Bottom Activity",Toast.LENGTH_LONG)
    }
    private fun moveToLeftScreen(){
        val intent = Intent(this,MainActivity3::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Left Activity",Toast.LENGTH_LONG)
    }
    private fun moveToRightScreen(){
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Right Activity",Toast.LENGTH_LONG)
    }

    override fun onSensorChanged(event: SensorEvent?) {
//        TODO("Not yet implemented")
        var currentX = event!!.values[0]
        var currentY = event!!.values[1]
        var currentZ = event!!.values[2]
        var currentTime = System.currentTimeMillis()
        if((currentTime - oldTime) > 100 ){
            var timeDifference = currentTime - oldTime
            oldTime = currentTime
            var speed = Math.abs(currentX+currentY+currentZ - xOld - yOld - zOld)/timeDifference*10000
            if(speed > threshold){
                shakeImage()
                Toast.makeText(this,"Start Rotating",Toast.LENGTH_LONG)
            }
        }
    }
    private fun shakeImage(){

            imageViewed.animate().apply {
                duration  = 250
                rotationBy(-20f)
            }.withEndAction{
                imageViewed.animate().apply {
                    duration  = 250
                    rotationBy(40f)}
            }.withEndAction{
                imageViewed.animate().apply {
                    duration  = 250
                    rotationBy(-40f)}
            }.withEndAction{
                imageViewed.animate().apply {
                    duration  = 250
                    rotationBy(40f)
                }.withEndAction{
                    imageViewed.animate().apply {
                        duration  = 250
                        rotationBy(-40f)
                    }}.withEndAction{
                    imageViewed.animate().apply {
                        duration  = 250
                        rotationBy(40f)}
                }.withEndAction{
                    imageViewed.animate().apply {
                        duration  = 250
                        rotationBy(-40f)
                    }
                }.withEndAction{
                    imageViewed.animate().apply {
                        duration  = 250
                        rotationBy(-20f)}
                }


            }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//        TODO("Not yet implemented")
    }
}