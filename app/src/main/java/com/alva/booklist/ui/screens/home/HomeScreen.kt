@file:OptIn(ExperimentalMaterial3Api::class)

package com.alva.booklist.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alva.booklist.constants.AppStrings
import com.alva.booklist.constants.BookSortMode
import com.alva.booklist.constants.LoadStatus
import com.alva.booklist.models.Book
import com.alva.booklist.navigation.AppRoute
import com.alva.booklist.navigation.AppRouter
import com.alva.booklist.view_models.SharedViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, sharedViewModel: SharedViewModel) {
    val uiState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        homeViewModel.loadBooks()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(AppStrings.homeStrings.homeTitle) },
                actions = {
                    IconButton(onClick = { homeViewModel.toggleDropDown(true) }) {
                        Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = uiState.showDropDown,
                        onDismissRequest = { homeViewModel.toggleDropDown(false) },
                    ) {
                        BookSortMode.entries.map {
                            DropdownMenuItem(
                                onClick = {
                                    homeViewModel.sortBooks(it)
                                    homeViewModel.toggleDropDown(false)
                                },
                                text = { Text(text = it.name) },
                            )
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            when (uiState.status) {
                is LoadStatus.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LoadStatus.Error -> {
                    LaunchedEffect(Unit) {
                        sharedViewModel.setToastMessage(uiState.status.description)
                    }

                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(text = uiState.status.description)
                    }
                }

                else -> {
                    LazyColumn {
                        items(uiState.books, key = { book -> book.index }) { book ->
                            BookCard(
                                modifier = Modifier.animateItem(), book = book,
                                onClick = { AppRouter.instance.navigate(AppRoute.Detail(book.index)) },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(modifier: Modifier = Modifier, book: Book, onClick: () -> Unit = {}) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp)
    ) {
        ListItem(
            overlineContent = { Text(book.index.toString()) },
            headlineContent = { Text(book.title) },
            supportingContent = { Text(book.date) },
            trailingContent = { Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null) },
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            colors = ListItemDefaults.colors(containerColor = Color.Transparent)
        )
    }
}