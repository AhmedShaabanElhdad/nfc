package com.ahmed.nfc.ui.activity.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ahmed.nfc.BR
import com.ahmed.nfc.R
import com.ahmed.nfc.databinding.ActivityMainBinding
import com.ahmed.nfc.databinding.ActivitySplahBinding
import com.ahmed.nfc.ui.activity.main.MainActivity
import com.ahmed.nfc.ui.base.BaseActivity
import javax.inject.Inject


class SplashActivity : BaseActivity<ActivitySplahBinding, SplashViewModel>() {

    @Inject
    lateinit var mSplashViewModel: SplashViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_splah

    override fun getViewModel(): SplashViewModel = mSplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish()
        }, 2000)
    }
}