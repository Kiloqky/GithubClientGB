package ru.kiloqky.gb.githubclient.presentation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kiloqky.gb.githubclient.R

class MainAppNavigator(activity: FragmentActivity, @IdRes id: Int) : AppNavigator(activity, id) {
    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment,
    ) {
        fragmentTransaction.setCustomAnimations(R.anim.from_right, R.anim.to_left)
    }
}
