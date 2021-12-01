package com.zywczas.myworkout.watch.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.wear.widget.WearableLinearLayoutManager

class CustomScrollingLayoutCallback : WearableLinearLayoutManager.LayoutCallback() {

    /** How much should we scale the icon at most.  */
    private val maxIconProgress = 0.65f

    private var progressToCenter: Float = 0f

    override fun onLayoutFinished(child: View, parent: RecyclerView) {
        child.apply {
            // Figure out % progress from top to bottom
            val centerOffset = height.toFloat() / 2.0f / parent.height.toFloat()
            val yRelativeToCenterOffset = y / parent.height + centerOffset

            // Normalize for center
            progressToCenter = Math.abs(0.5f - yRelativeToCenterOffset)
            // Adjust to the maximum scale
            progressToCenter = Math.min(progressToCenter, maxIconProgress)

            scaleX = 1 - progressToCenter
            scaleY = 1 - progressToCenter
        }
    }

}