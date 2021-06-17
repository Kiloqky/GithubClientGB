package ru.kiloqky.gb.githubclient.presentation

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import moxy.MvpAppCompatActivity
import ru.kiloqky.gb.githubclient.App.Navigation.navigatorHolder
import ru.kiloqky.gb.githubclient.App.Navigation.router
import ru.kiloqky.gb.githubclient.R
import ru.kiloqky.gb.githubclient.R.layout.activity_main
import ru.kiloqky.gb.githubclient.presentation.users.UsersScreen

class MainActivity : MvpAppCompatActivity(activity_main) {
    private val navigator = object : AppNavigator(this, R.id.container) {
        override fun setupFragmentTransaction(
            screen: FragmentScreen,
            fragmentTransaction: FragmentTransaction,
            currentFragment: Fragment?,
            nextFragment: Fragment,
        ) {
            fragmentTransaction.setCustomAnimations(R.anim.from_right, R.anim.to_left)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState ?: router.newRootScreen(UsersScreen)
        supportActionBar?.setBackgroundDrawable(
            ColorDrawable(
                ContextCompat.getColor(
                    this,
                    R.color.githubPrimaryToolbar
                )
            )
        )
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

