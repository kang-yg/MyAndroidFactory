package com.example.studyandroid.view.CustomView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.studyandroid.R

class ColorChangingCircle : View {
    var myWidth: Int = 0
    var myHeight: Int = 0
    private var colorId: Int = 0
    private var touchedColorId: Int = 0
    private var radius = 0
    private var isTouched = false

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        getAttrs(attrs, defStyle)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorChangingCircle)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorChangingCircle, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        myWidth = typedArray.getDimension(R.styleable.ColorChangingCircle_android_layout_width, 10f).toInt()
        myHeight = typedArray.getDimension(R.styleable.ColorChangingCircle_android_layout_height, 10f).toInt()
        colorId = typedArray.getResourceId(R.styleable.ColorChangingCircle_ccc_color, 0)
        touchedColorId = typedArray.getResourceId(R.styleable.ColorChangingCircle_ccc_touch_color, 0)
        radius = typedArray.getDimension(R.styleable.ColorChangingCircle_ccc_radius, 0f).toInt()

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = if (!isTouched) Paint().apply {
            style = Paint.Style.FILL
            color = context.getColor(colorId)
        } else Paint().apply {
            style = Paint.Style.FILL
            color = context.getColor(touchedColorId)
        }

        with(canvas) {
            this@with?.drawCircle((width / 2).toFloat(), (width / 2).toFloat(), radius.toFloat(), paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isTouched = true
                invalidate()
                return true
            }

            MotionEvent.ACTION_UP -> {
                isTouched = false
                invalidate()
                return true
            }
        }

        return false
    }
}