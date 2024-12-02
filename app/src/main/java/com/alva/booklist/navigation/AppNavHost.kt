package com.alva.booklist.navigation

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.alva.booklist.ui.screens.detail.DetailScreen
import com.alva.booklist.ui.screens.home.HomeScreen
import com.alva.booklist.view_models.SharedViewModel
import kotlin.reflect.KType


@ExperimentalComposeUiApi
@Composable
fun AppNavHost() {
    val context = LocalContext.current
    val navController = rememberNavController()

    val sharedViewModel: SharedViewModel = hiltViewModel()
    val toastState by sharedViewModel.uiState.collectAsState()
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(toastState.message) {
        if (toastState.message.isNotEmpty()) {
            Toast.makeText(context, toastState.message, Toast.LENGTH_SHORT).show()
            sharedViewModel.setToastMessage("")
        }
    }

    LaunchedEffect(Unit) {
        AppRouter.instance.init(navController)
        isLoading = false
    }

    if (!isLoading) NavRouter(sharedViewModel = sharedViewModel)
}

@Composable
private fun NavRouter(sharedViewModel: SharedViewModel) {
    if (AppRouter.instance.navController == null) return

    NavHost(navController = AppRouter.instance.navController!!, startDestination = AppRoute.Home) {
        customComposable<AppRoute.Home>(screenContent = { _, _ ->
            HomeScreen(homeViewModel = hiltViewModel(), sharedViewModel = sharedViewModel)
        })
        customComposable<AppRoute.Detail>(
            screenContent = { argument, navBackStackEntry ->
                val parentEntry = remember(navBackStackEntry) { AppRouter.instance.getBackStackEntry(AppRoute.Home) }

                DetailScreen(bookIndex = argument.bookIndex, homeViewModel = hiltViewModel(parentEntry), sharedViewModel = sharedViewModel)
            },
        )
    }
}

private val typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = mapOf(
//    typeOf<ReviewQuestionProperty>() to CustomNavType(ReviewQuestionProperty.serializer()),
)

@SuppressLint("RestrictedApi")
private inline fun <reified T : Any> NavGraphBuilder.customComposable(
    noinline enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = AppNavAnimation.slideLeftEnterTransition,
    noinline exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = { null },
    noinline popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition? = { null },
    noinline popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition? = AppNavAnimation.slideRightExitTransition,
    crossinline screenContent: @Composable (T, NavBackStackEntry) -> Unit,
) {
    composable<T>(
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        typeMap = typeMap,
    ) {
        screenContent(it.toRoute(), it)
    }
}