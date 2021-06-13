package ru.kiloqky.gb.githubclient.presentation

import android.os.Bundle
import moxy.MvpAppCompatActivity
import ru.kiloqky.gb.githubclient.App.Navigation.navigatorHolder
import ru.kiloqky.gb.githubclient.App.Navigation.router
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.R.layout.activity_main
import ru.kiloqky.gb.githubclient.presentation.users.UsersScreen

class MainActivity : MvpAppCompatActivity(activity_main) {

    private val navigator = MainAppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(UsersScreen)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}

