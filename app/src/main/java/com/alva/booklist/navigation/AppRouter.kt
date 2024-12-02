package com.alva.booklist.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class AppRouter private constructor() {
    var navController: NavHostController? = null
        private set

    companion object {
        val instance: AppRouter = AppRouter()
    }

    fun init(navController: NavHostController) {
        this.navController = navController
    }

    fun clearController() {
        navController = null
    }


    fun popBackStack(popPagesNumber: Int = 1) = repeat(popPagesNumber) { if (navController?.previousBackStackEntry != null) navController?.popBackStack() }
    inline fun <reified T : Any> popBackStack(inclusive: Boolean = false) = navController?.popBackStack<T>(inclusive)
    fun popBackStack(destination: AppRoute, inclusive: Boolean = false) = navController?.popBackStack(destination, inclusive)


    fun navigate(destination: AppRoute) = navController?.navigate(destination) { launchSingleTop = true }

    fun navigate(destination: AppRoute, popUpTo: AppRoute) {
        navController?.navigate(destination) {
            popUpTo(popUpTo)
            launchSingleTop = true
        }
    }

    inline fun <reified T : Any> navigate(destination: AppRoute, popUpToGeneric: Boolean) {
        navController?.navigate(destination) {
            if (popUpToGeneric) popUpTo<T>()
            launchSingleTop = true
        }
    }

    fun navigate(destination: AppRoute, popUpTo: AppRoute, inclusive: Boolean) {
        navController?.navigate(destination) {
            popUpTo(popUpTo) { this.inclusive = inclusive }
            launchSingleTop = true
        }
    }

    inline fun <reified T : Any> navigate(destination: AppRoute, popUpToGeneric: Boolean, inclusive: Boolean) {
        navController?.navigate(destination) {
            if (popUpToGeneric) popUpTo<T> { this.inclusive = inclusive }
            launchSingleTop = true
        }
    }

    @SuppressLint("RestrictedApi")
    fun clearBackStack() {
        popBackStack(navController?.currentBackStack?.value?.size ?: 1)
    }

    fun getBackStackEntry(destination: AppRoute): NavBackStackEntry = navController!!.getBackStackEntry(destination)
}