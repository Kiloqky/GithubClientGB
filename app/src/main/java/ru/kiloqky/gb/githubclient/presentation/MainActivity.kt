package ru.kiloqky.gb.githubclient.presentation

import android.os.Bundle
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatActivity
import ru.kiloqky.gb.githubclient.App
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.R.layout.activity_main
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(activity_main) {

    private val navigator = MainAppNavigator(this, R.id.container)

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        savedInstanceState ?: router.newRootScreen(screens.UsersScreen())
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

