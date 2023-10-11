package com.example.navigateactivitiesapp

import android.content.Intent
import android.content.res.Configuration
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.Toast
import java.lang.Math.abs

class MainActivity3 : AppCompatActivity() ,GestureDetector.OnGestureListener,SensorEventListener{
    private lateinit var gestureDetector: GestureDetector
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
            setContentView(R.layout.activity_main3_landscape)
        } else {
            setContentView(R.layout.activity_main3)
        }
        gestureDetector = GestureDetector(this,this)

        imageViewed =findViewById<ImageView>(R.id.imageView2)
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main3_landscape)

            Toast.makeText(this,"changed to landscape",Toast.LENGTH_LONG)
        } else {
            setContentView(R.layout.activity_main3)
            Toast.makeText(this,"changed to portrait",Toast.LENGTH_LONG)
        }}
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        when(event?.action){
            0 -> {
                x1 = event.x
                y1 = event.y
            }
            1->{
                x2 = event.x
                y2 = event.y
                val valueX:Float = x2-x1
                val valueY:Float = y2-y1
                if(abs(valueX)> MIN_DISTANCE){
                    if(x2>x1){
                        moveToRightScreen()
                    }
                    else if(x1>x2){
                        Toast.makeText(this,"We can't go further left.",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
//        TODO("Not yet implemented")
    }

    override fun onShowPress(e: MotionEvent) {

//        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return false
//        TODO("Not yet implemented")
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent) {
//        TODO("Not yet implemented")
    }

    private fun moveToRightScreen(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Home Activity", Toast.LENGTH_LONG)
    }
    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        return false
//        TODO("Not yet implemented")
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