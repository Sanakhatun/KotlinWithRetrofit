package com.sana.kotlinwithretrofit.common

import android.view.ScaleGestureDetector
import android.widget.ImageView

class ScaleListener(val imageView: ImageView) : ScaleGestureDetector.SimpleOnScaleGestureListener() {

    private var mScaleFactor = 1.0f

    override fun onScale(detector: ScaleGestureDetector): Boolean {
        mScaleFactor *= detector.scaleFactor

        mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
        imageView.setScaleX(mScaleFactor)
        imageView.setScaleY(mScaleFactor)
        return true
    }
}
