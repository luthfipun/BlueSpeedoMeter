package studio.elevenbox.bluespeedometer

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class BlueSpeedoMeter : ConstraintLayout {

    private var mTitle = ""
    private var mTitleSize = 14f
    private var mTotal = 0
    private var mTotalSize = 30f
    private var mTotalAnimate = 2000
    private var mNeedleAnimate = 2000
    private var mNeedleAnimateFrom = 0F
    private var mNeedleAnimateTo = 360F
    private var isAnimate = true
    private var mNeedleWidth = 100F
    private var mNeedleHeight = 10F

    private var textTitle: TextView? = null
    private var textTotal: TextView? = null
    private var imgNeedle: ImageView? = null
    private var imgBg: ImageView? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
        initValuesFromAttrs(attributeSet, context)
    }

    private fun init(context: Context) {
        val root = LayoutInflater.from(context).inflate(R.layout.bluespeedometer, this, true)
        textTitle = root.findViewById(R.id.title)
        textTotal = root.findViewById(R.id.total)
        imgNeedle = root.findViewById(R.id.needle)
        imgBg = root.findViewById(R.id.bg)
    }

    private fun initValuesFromAttrs(attributeSet: AttributeSet?, context: Context) {
        if (attributeSet != null) {
            val array: TypedArray =
                context.theme.obtainStyledAttributes(
                    attributeSet,
                    R.styleable.BlueSpeedoMeter,
                    0,
                    0
                )

            mTitle = array.getString(R.styleable.BlueSpeedoMeter_bsm_title).toString()
            mTotal = array.getInt(R.styleable.BlueSpeedoMeter_bsm_total, mTotal)
            mTotalAnimate =
                array.getInt(R.styleable.BlueSpeedoMeter_bsm_totalAnimate, mTotalAnimate)
            mNeedleAnimate =
                array.getInt(R.styleable.BlueSpeedoMeter_bsm_needleAnimate, mNeedleAnimate)
            isAnimate = array.getBoolean(R.styleable.BlueSpeedoMeter_bsm_isAnimate, isAnimate)
            mTitleSize = array.getDimension(R.styleable.BlueSpeedoMeter_bsm_titleSize, mTitleSize)
            mTotalSize = array.getDimension(R.styleable.BlueSpeedoMeter_bsm_totalSize, mTotalSize)
            mNeedleWidth =
                array.getDimension(R.styleable.BlueSpeedoMeter_bsm_needleWidth, mNeedleWidth)
            mNeedleHeight =
                array.getDimension(R.styleable.BlueSpeedoMeter_bsm_needleHeight, mNeedleHeight)

            array.recycle()
            initAttributeValue()
        }
    }

    private fun initAttributeValue() {
        textTitle?.text = mTitle
        textTotal?.text = mTotal.toString()
        textTitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleSize)
        textTotal?.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTotalSize)

        imgNeedle?.layoutParams?.width = mNeedleWidth.toInt()
        imgNeedle?.layoutParams?.height = mNeedleHeight.toInt()

//        imgNeedle?.layoutParams?.width = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            mNeedleWidth,
//            resources.displayMetrics
//        ) as Int
//        imgNeedle?.layoutParams?.height = TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            mNeedleHeight,
//            resources.displayMetrics
//        ) as Int
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun rotateNeedle() {
        val rotate =
            ObjectAnimator.ofFloat(imgNeedle, "rotation", mNeedleAnimateFrom, mNeedleAnimateTo)
        rotate.duration = mNeedleAnimate.toLong()
        rotate.start()
        invalidate()
    }

    fun setIsAnimate(boolean: Boolean) {
        isAnimate = boolean
    }

    fun setNeedleAnimateTo(float: Float) {
        mNeedleAnimateTo = float
    }

    fun setNeedleAnimateFrom(float: Float) {
        mNeedleAnimateFrom = float
    }

    fun setNeedleAnimate(int: Int) {
        mNeedleAnimate = int
    }

    fun setTotalAnimate(int: Int) {
        mTotalAnimate = int
    }

    fun setTotal(int: Int) {
        mTotal = int
        textTotal?.text = int.toString()
    }

    fun setTotalSize(size: Float) {
        mTotalSize = size
        textTotal?.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTotalSize)
    }

    fun setTitleSize(size: Float) {
        mTitleSize = size
        textTitle?.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTitleSize)
    }

    fun setTitle(string: String) {
        mTitle = string
        textTitle?.text = string
    }

    fun playNeedle() {
        if (isAnimate) {
            rotateNeedle()
        }
    }

    fun playTotal() {
        if (isAnimate) {
            animateTotal()
        }
    }

    private fun animateTotal() {
        val value = ValueAnimator()
        value.setObjectValues(0, mTotal)
        value.addUpdateListener {
            textTotal?.text = it.animatedValue.toString()
        }
        value.duration = mTotalAnimate.toLong()
        value.start()
        invalidate()
    }

    fun playAll() {
        if (isAnimate) {
            rotateNeedle()
            animateTotal()
        }
    }
}