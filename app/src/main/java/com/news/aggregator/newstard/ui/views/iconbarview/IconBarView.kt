package com.news.aggregator.newstard.ui.views.iconbarview

import android.animation.AnimatorInflater
import android.content.Context
import android.media.Image
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.util.TypedValue
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import com.news.aggregator.newstard.R

class IconBarView(context: Context, attributeSet: AttributeSet): ConstraintLayout(context, attributeSet) {

    private var isScaled = false

    init {
        inflateView()

        val btn = findViewById<ImageButton>(R.id.imgBut1)

        btn.setOnClickListener {
            if(isScaled) {
                // Can use ValueAnimator to show animation instead of instantaneous value change
                val layoutParams = btn.layoutParams
                layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f, resources.displayMetrics).toInt()
                layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30f ,resources.displayMetrics).toInt()
                btn.layoutParams = layoutParams
            } else {
                val layoutParams = btn.layoutParams
                layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt()
                layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt()
                btn.layoutParams = layoutParams
            }

            isScaled = !isScaled
        }
    }

    /**
     * Inflate layout of the view
     */
    private fun inflateView(){
        inflate(context, R.layout.view_icon_bar_layout, this)
    }
}
