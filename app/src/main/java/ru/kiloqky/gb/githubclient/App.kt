package ru.kiloqky.gb.githubclient

import android.annotation.SuppressLint
import android.app.AppComponentFactory
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Cicerone
import dagger.internal.DaggerGenerated
import ru.kiloqky.gb.githubclient.di.AppComponent
import ru.kiloqky.gb.githubclient.di.AppModule
import ru.kiloqky.gb.githubclient.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}
