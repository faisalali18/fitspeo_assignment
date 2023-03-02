package com.fitspeo_assignment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FitspeoApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}