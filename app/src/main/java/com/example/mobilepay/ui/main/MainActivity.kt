package com.example.mobilepay.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.mobilepay.R
import com.example.mobilepay.ui.screens.app.AppHomeFragment
import com.example.mobilepay.ui.screens.pos.PosAmountFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.pos_container, PosAmountFragment.newInstance())
                .replace(R.id.app_container, AppHomeFragment.newInstance())
                .commitNow()
        }
    }
}