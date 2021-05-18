package com.example.githubtool.base

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.githubtool.R


/**
 * @author tuanminh.vu
 */
object UIUtils {
    fun Context.createDivider(
        height: Int = 8,
        color: Int = ContextCompat.getColor(this, R.color.color_divider)
    ): DividerItemDecoration {
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        if (height != 0) {
            val drawable = ShapeDrawable()
            drawable.intrinsicHeight = convertDip2Px(height)
            drawable.paint.color = color
            divider.setDrawable(drawable)
        }
        return divider
    }

    fun Context.convertDip2Px(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    fun hideKeyboard(activity: Activity) {
        activity.currentFocus?.windowToken?.let {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it, 0)
        }

    }
}