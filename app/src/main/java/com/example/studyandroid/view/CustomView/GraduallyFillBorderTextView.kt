package com.example.studyandroid.view.CustomView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.studyandroid.R
import com.example.studyandroid.Util.dpToPx

class GraduallyFillBorderTextView : View {
    var progress: Int = 0
        get() = field
        set(value) {
            field = value
        }
    var textColorId: Int = 0
    var borderColorId: Int = 0
    var borderThickness: Int = 0

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        getAttrs(attrs, defStyle)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GraduallyFillBorderTextView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GraduallyFillBorderTextView, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        progress = typedArray.getInt(R.styleable.GraduallyFillBorderTextView_gfb_progress, 0)
        textColorId = typedArray.getResourceId(R.styleable.GraduallyFillBorderTextView_android_textColor, 0)
        borderColorId = typedArray.getResourceId(R.styleable.GraduallyFillBorderTextView_gfb_borderColor, 0)
        borderThickness = typedArray.getDimension(R.styleable.GraduallyFillBorderTextView_gfb_thickness, 0f).toInt()

        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val textPaint = Paint().apply {
            color = context.getColor(textColorId)
            style = Paint.Style.FILL
            textSize = context.dpToPx(18).toFloat()
            textAlign = Paint.Align.CENTER
        }

        val borderPaint = Paint().apply {
            color = context.getColor(borderColorId)
            style = Paint.Style.STROKE
            strokeWidth = borderThickness.toFloat()
        }

        with(canvas) {
            progress.let {
                if (progress <= 100) {
                    val bounds = Rect()
                    textPaint.getTextBounds(progress.toString(), 0, progress.toString().length, bounds)
                    this@with?.drawArc(RectF(10f, 10f, 300f, 300f), -90f, (360 * progress / 100).toFloat(), false, borderPaint)
                    this@with?.drawText(progress.toString(), 155f, 165f, textPaint)
                    if (progress != 100) invalidate()
                }
            }
        }
        progress += 1
    }
}