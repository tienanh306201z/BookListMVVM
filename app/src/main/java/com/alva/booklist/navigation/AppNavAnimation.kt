package com.alva.booklist.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

object AppNavAnimation {
    val slideLeftEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(300)) + fadeIn(tween(durationMillis = 300))
    }

    val slideRightExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(300)) + fadeOut(tween(durationMillis = 300))
    }

    val slideUpEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Up, animationSpec = tween(300)) + fadeIn(tween(durationMillis = 300))
    }

    val slideDownExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Down, animationSpec = tween(300)) + fadeOut(tween(durationMillis = 300))
    }

    val fadeInEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = { fadeIn(animationSpec = tween(durationMillis = 250)) }

    val fadeOutExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = { fadeOut(animationSpec = tween(durationMillis = 250)) }
}