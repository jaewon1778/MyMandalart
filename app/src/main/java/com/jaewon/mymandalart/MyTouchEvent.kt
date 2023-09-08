package com.jaewon.mymandalart

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import kotlin.math.abs

class MyTouchEvent(val TContext: Context) : MyAppActivity(),
    GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener{

    val WAIT = -1
    val LONG_PRESS = 0
    val SINGLE_TAP = 1
    val DOUBLE_TAP = 2
    val FLING_UP = 3
    val FLING_DOWN = 4
    val FLING_LEFT = 5
    val FLING_RIGHT = 6

//    private val mDetector: GestureDetectorCompat = GestureDetectorCompat(this,this)
//    fun getMDetector(): GestureDetectorCompat {
//        mDetector.setOnDoubleTapListener(this)
//        return mDetector
//    }

    private var GestureNum: Int = WAIT
    private fun setGestureNum(GN : Int){
        GestureNum = GN
    }
    fun getGestureNum():Int{
        return GestureNum
    }

    override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
        return false
    }

    override fun onDoubleTap(e: MotionEvent): Boolean {
        setGestureNum(DOUBLE_TAP)
        return true

    }

    override fun onDoubleTapEvent(e: MotionEvent): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {
        return
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        setGestureNum(SINGLE_TAP)
        Toast.makeText(TContext, "Single Tap", Toast.LENGTH_SHORT).show()
        return true
    }

    override fun onScroll(
        e1: MotionEvent,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        return
    }

    override fun onFling(
        e1: MotionEvent,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val absX : Float = abs(velocityX)
        val absY : Float = abs(velocityY)

        if (absX > absY){
            if (velocityX>0) setGestureNum(FLING_RIGHT)
            else             setGestureNum(FLING_LEFT)
        }
        else{
            if (velocityY>0) setGestureNum(FLING_DOWN)
            else             setGestureNum(FLING_UP)
        }

        return true
    }


}