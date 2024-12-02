@file:OptIn(ExperimentalMaterial3Api::class)

package com.alva.booklist.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alva.booklist.constants.AppStrings
import com.alva.booklist.navigation.AppRoute
import com.alva.booklist.navigation.AppRouter
import com.alva.booklist.ui.components.Gap
import com.alva.booklist.ui.screens.home.HomeViewModel
import com.alva.booklist.view_models.SharedViewModel

@Composable
fun DetailScreen(bookIndex: Int, homeViewModel: HomeViewModel, sharedViewModel: SharedViewModel) {
    val book = remember { homeViewModel.getBook(bookIndex) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(AppStrings.detailStrings.detailTitle) }) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            if (book != null) {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text("${AppStrings.detailStrings.title}: ${book.title}")
                    Gap(10.dp)
                    Text("${AppStrings.detailStrings.description}: ${book.description}")
                    Gap(10.dp)
                    Text("${AppStrings.detailStrings.date}: ${book.date}")
                    Gap(10.dp)
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        ElevatedButton(
                            onClick = {
                                try {
                                    homeViewModel.deleteBook(bookIndex)
                                    AppRouter.instance.popBackStack<AppRoute.Home>()
                                } catch (e: Exception) {
                                    sharedViewModel.setToastMessage(e.message.toString())
                                }
                            },
                        ) { Text(AppStrings.detailStrings.deleteButtonTitle) }
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = AppStrings.detailStrings.bookNotFound)
                }
            }
        }
    }
}