package ru.kiloqky.gb.githubclient

import androidx.appcompat.app.AppCompatDelegate
import com.github.terrakok.cicerone.Cicerone
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.kiloqky.gb.githubclient.di.AppComponent
import ru.kiloqky.gb.githubclient.di.DaggerAppComponent
import ru.kiloqky.gb.githubclient.presentation.Screens
import ru.kiloqky.gb.githubclient.scheduler.DefaultSchedulers

class App : DaggerApplication() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent
            .builder()
            .withContext(applicationContext)
            .apply {
                val cicerone = Cicerone.create()

                withRouter(cicerone.router)
                withNavigatorHolder(cicerone.getNavigatorHolder())
                withScreens(Screens())
            }
            .withSchedulers(DefaultSchedulers())
            .build()
}
