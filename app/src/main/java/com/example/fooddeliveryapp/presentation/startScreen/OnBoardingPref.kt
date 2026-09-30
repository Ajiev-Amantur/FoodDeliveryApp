package com.example.fooddeliveryapp.presentation.startScreen

import android.content.Context

class OnBoardingPref(context: Context) {
    private val prefs = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)

    fun onBoardingCompleted(): Boolean {
        return prefs.getBoolean("is_onboarding_completed", false)

    }

    fun setOnBoardingCompleted() {
         prefs.edit().putBoolean("is_onboarding_completed", true).apply()
    }
}
