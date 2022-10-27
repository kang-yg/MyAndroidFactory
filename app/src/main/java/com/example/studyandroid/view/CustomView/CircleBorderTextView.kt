package com.example.studyandroid.view.CustomView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.studyandroid.R
import com.example.studyandroid.Util.dpToPx

class CircleBorderTextView : LinearLayout {
    private lateinit var linearLayout: LinearLayout
    private lateinit var textView: TextView

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
        getAttrs(attrs, defStyle)
    }


    private fun initView() {
        val inflaterService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflaterService) as LayoutInflater
        val view = inflater.inflate(R.layout.circle_border_textview, this, false)
        addView(view)

        linearLayout = findViewById(R.id.llGraduallyFillsBorder)
        textView = findViewById(R.id.tvGraduallyFillsBorder)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleBorderTextView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleBorderTextView, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        val borderColor = typedArray.getResourceId(R.styleable.CircleBorderTextView_borderColor, 0)
        val borderThickness = typedArray.getDimension(R.styleable.CircleBorderTextView_thickness, 0f).toInt()
        val background = typedArray.getDrawable(R.styleable.CircleBorderTextView_android_background)
        val text = typedArray.getString(R.styleable.CircleBorderTextView_android_text)

        with(textView) {
            background?.let {
                this.background = (it as GradientDrawable).apply {
                    mutate()
                    setStroke(context.dpToPx(borderThickness), context.getColor(borderColor))
                }
            }

            this.text = text
        }
        typedArray.recycle()
    }
}