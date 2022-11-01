package com.example.studyandroid.view.CustomView

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.studyandroid.R

class IOSRadio : ConstraintLayout {
    private lateinit var clIOSRadio: ConstraintLayout
    private lateinit var tvFirst: TextView
    private lateinit var tvSecond: TextView
    private lateinit var tvThird: TextView
    private lateinit var tvFourth: TextView
    private lateinit var selectView: View

    private var firstText: String? = null
    private var secondText: String? = null
    private var thirdText: String? = null
    private var fourthText: String? = null
    private var selectedIndex: Int = 0

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        getAttrs(attrs, defStyle)
        initView()
    }

    private fun initView() {
        val inflaterService = Context.LAYOUT_INFLATER_SERVICE
        val inflater = context.getSystemService(inflaterService) as LayoutInflater
        val view = inflater.inflate(R.layout.ios_radio, this, false)
        addView(view)

        clIOSRadio = findViewById(R.id.clIOSRadio)
        tvFirst = findViewById<TextView?>(R.id.tvFirst).apply {
            if (firstText.isNullOrEmpty()) this.visibility = View.GONE else this.visibility = View.VISIBLE
            text = firstText
            textAlignment = TEXT_ALIGNMENT_CENTER
            typeface = Typeface.DEFAULT_BOLD
            setOnClickListener {
                selectedIndex = 0
                moveSelectView(0)
            }
        }
        tvSecond = findViewById<TextView?>(R.id.tvSecond).apply {
            if (secondText.isNullOrEmpty()) this.visibility = View.GONE else this.visibility = View.VISIBLE
            text = secondText
            textAlignment = TEXT_ALIGNMENT_CENTER
            typeface = Typeface.DEFAULT_BOLD
            setOnClickListener {
                selectedIndex = 1
                moveSelectView(1)
            }
        }
        tvThird = findViewById<TextView?>(R.id.tvThird).apply {
            if (thirdText.isNullOrEmpty()) this.visibility = View.GONE else this.visibility = View.VISIBLE
            text = thirdText
            textAlignment = TEXT_ALIGNMENT_CENTER
            typeface = Typeface.DEFAULT_BOLD
            setOnClickListener {
                selectedIndex = 2
                moveSelectView(2)
            }
        }
        tvFourth = findViewById<TextView?>(R.id.tvFourth).apply {
            if (fourthText.isNullOrEmpty()) this.visibility = View.GONE else this.visibility = View.VISIBLE
            text = fourthText
            textAlignment = TEXT_ALIGNMENT_CENTER
            typeface = Typeface.DEFAULT_BOLD
            setOnClickListener {
                selectedIndex = 3
                moveSelectView(3)
            }
        }
        selectView = findViewById<View?>(R.id.selectView).apply {
            setBackgroundColor(context.getColor(R.color.Gray))
            alpha = 0.5f
        }
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IOSRadio)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IOSRadio, defStyle, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray) {
        firstText = typedArray.getString(R.styleable.IOSRadio_ios_radio_first)
        secondText = typedArray.getString(R.styleable.IOSRadio_ios_radio_second)
        thirdText = typedArray.getString(R.styleable.IOSRadio_ios_radio_third)
        fourthText = typedArray.getString(R.styleable.IOSRadio_ios_radio_fourth)

        typedArray.recycle()
    }

    private fun moveSelectView(index: Int) {
        with(selectView) {
            val moveDistance = (width.toFloat() * index)
            animate().translationX(moveDistance)
        }
    }

    fun selectedIndex(): Int = selectedIndex
}