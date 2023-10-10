package com.example.navigateactivitiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import java.lang.Math.abs

class MainActivity5 : AppCompatActivity(),GestureDetector.OnGestureListener {
    private lateinit var gestureDetector: GestureDetector
    var x1: Float = 0.0f
    var x2: Float = 0.0f
    var y1: Float = 0.0f
    var y2: Float = 0.0f
    companion object {
        const val MIN_DISTANCE = 150
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        gestureDetector = GestureDetector(this,this)
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
}