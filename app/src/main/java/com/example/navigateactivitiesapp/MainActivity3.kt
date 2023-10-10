package com.example.navigateactivitiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import java.lang.Math.abs

class MainActivity3 : AppCompatActivity() ,GestureDetector.OnGestureListener{
    private lateinit var gestureDetector: GestureDetector
    var x1:Float = 0.0f
    var x2:Float = 0.0f
    var y1:Float = 0.0f
    var y2:Float = 0.0f

    companion object{
        const val MIN_DISTANCE = 150
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
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
}