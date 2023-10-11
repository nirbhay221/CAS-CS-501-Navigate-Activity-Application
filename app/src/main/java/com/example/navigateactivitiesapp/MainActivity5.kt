package com.example.navigateactivitiesapp

import android.content.Intent
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

class MainActivity5 : AppCompatActivity(),GestureDetector.OnGestureListener,SensorEventListener {
    private lateinit var gestureDetector: GestureDetector
    var x1: Float = 0.0f
    var x2: Float = 0.0f
    var y1: Float = 0.0f
    var y2: Float = 0.0f

    var xOld:Float = 0.0f
    var yOld:Float = 0.0f
    var zOld:Float = 0.0f
    var threshold:Float = 3000.0f
    var oldTime:Long = 0

    private lateinit var imageViewed: ImageView
    companion object {
        const val MIN_DISTANCE = 150
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        gestureDetector = GestureDetector(this,this)

        imageViewed =findViewById<ImageView>(R.id.imageView5)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event != null) {
            gestureDetector.onTouchEvent(event)
        }
        when(event?.action){
            0 -> {
               x1 = event.x
               y1 = event.y

            }
            1 -> {
                x2 = event.x
                y2 = event.y
                val valueX: Float = x2 - x1
                val valueY: Float = y2 - y1
                if(abs(valueY) > MIN_DISTANCE){
                    if(y1 < y2){

                        Toast.makeText(this,"Cannot Move Further down",Toast.LENGTH_LONG).show()
                    }
                    else if (y2 < y1 ){
                        moveToTopScreen()
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }
    override fun onDown(e: MotionEvent): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent) {
//        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
//        TODO("Not yet implemented")
        return false
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

    private fun moveToTopScreen(){

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this,"You are on the Home Activity",Toast.LENGTH_LONG)

    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
//        TODO("Not yet implemented")
        return false
    }

    override fun onSensorChanged(event: SensorEvent?) {
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