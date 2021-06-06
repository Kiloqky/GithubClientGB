package ru.kiloqky.gb.githubclient

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

    object Navigation {
        private val cicerone by lazy { Cicerone.create() }
        val router get() = cicerone.router
        val navigatorHolder get() = cicerone.getNavigatorHolder()
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
