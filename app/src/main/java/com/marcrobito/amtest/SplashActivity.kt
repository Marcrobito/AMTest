package com.marcrobito.amtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marcrobito.amtest.modules.java.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finishAffinity()
    }
}
