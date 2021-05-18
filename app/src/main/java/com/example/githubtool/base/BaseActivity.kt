package com.example.githubtool.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.githubtool.MyApplication
import com.example.githubtool.R
import com.example.githubtool.di.components.AppComponent


/**
 * @author tuanminh.vu
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            window.decorView.importantForAutofill = View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS;
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppComponent().inject(this)
    }

    fun getAppComponent(): AppComponent {
        return (application as MyApplication).getAppComponent()
    }

    fun showErrorToast(e: Throwable?) {
        e?.printStackTrace()
        Toast.makeText(this, ErrorMapper.getErrorMessage(e, applicationContext), Toast.LENGTH_SHORT).show()
    }

    fun toggleLoading(isShow: Boolean, isTransparent: Boolean = false, marginTop: Int = -1) {
        if (isShow) {
            val view = getLoadingView(marginTop)
            if (view.isVisible) {
                return
            }
            if (isTransparent) {
                view.setBackgroundColor(0x00000000)
            } else {
                view.setBackgroundColor(-0x1)
            }
            view.visibility = View.VISIBLE
        } else {
            getLoadingView(marginTop).visibility = View.GONE
        }
    }

    protected open fun getLoadingView(marginTop: Int = -1): View {
        var loading: View? = findViewById(R.id.loading)
        if (loading == null) {
            val rootView = findViewById<ViewGroup>(android.R.id.content)
            loading = LayoutInflater.from(this).inflate(R.layout.view_loading, rootView, false)
            rootView.addView(loading)
        }
        if (marginTop != -1) {
            (loading!!.layoutParams as FrameLayout.LayoutParams).setMargins(0, marginTop, 0, 0)
        }
        return loading!!
    }

}