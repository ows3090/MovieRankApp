package ows.kotlinstudy.movierankapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import ows.kotlinstudy.movierankapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }, 1000)
    }
}