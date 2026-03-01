package com.example.loginnavigation

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView

class ScalableImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var scaleFactor = 1f
    private val scaleMatrix = Matrix()

    private val scaleGestureDetector = ScaleGestureDetector(context,
        object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                applyScale(detector.scaleFactor, detector.focusX, detector.focusY)
                return true
            }
        })

    private fun applyScale(factor: Float, focusX: Float, focusY: Float) {
        scaleFactor *= factor
        scaleFactor = scaleFactor.coerceIn(0.5f, 5.0f)
        scaleMatrix.reset()
        scaleMatrix.postScale(scaleFactor, scaleFactor, focusX, focusY)
        imageMatrix = scaleMatrix
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        scaleGestureDetector.onTouchEvent(event)
        return true
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_SCROLL) {
            val scrollY = event.getAxisValue(MotionEvent.AXIS_VSCROLL)
            val zoomFactor = if (scrollY > 0) 1.1f else 0.9f
            applyScale(zoomFactor, width / 2f, height / 2f)
            return true
        }
        return super.onGenericMotionEvent(event)
    }
}