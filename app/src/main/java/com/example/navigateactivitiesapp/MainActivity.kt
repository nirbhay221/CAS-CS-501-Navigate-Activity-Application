package com.example.navigateactivitiesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import java.lang.Math.abs

class MainActivity : AppCompatActivity() ,GestureDetector.OnGestureListener{
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
        setContentView(R.layout.activity_main)
        gestureDetector = GestureDetector(this,this)

//        moveToBottomScreen()
    }

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
}