package com.yamidev.easycvmaker.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.yamidev.easycvmaker.R
import androidx.lifecycle.lifecycleScope
import com.yamidev.easycvmaker.ui.cv.Cv_Activity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EasyCVAnimationActivity : AppCompatActivity() {

    private lateinit var lottieView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.Splashscreen)
        supportActionBar?.hide()
        lottieView = findViewById(R.id.lottieAnim)
        lottieView.speed = 0.3f

        // Duración del splash: 3 segundos
        lifecycleScope.launch {
            delay(3000)
            goToMain()
        }

        lottieView.setOnClickListener {
            if (lottieView.isAnimating) {
                lottieView.pauseAnimation()
            } else {
                lottieView.playAnimation()
            }
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, Cv_Activity::class.java)) // o la Activity que quieras abrir
        finish() // evita que vuelva atrás al splash
    }
}
