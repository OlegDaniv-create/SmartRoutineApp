package com.example.smartroutineapp.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smartroutineapp.presentation.navigation.NavigableGraph
import com.example.smartroutineapp.presentation.view.HorizontalCard

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigate: NavHostController
) {
    val screenState by viewModel.homeState.collectAsState()
    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        item { Spacer(Modifier.height(2.dp)) }
        items(screenState) { item ->
            HorizontalCard(
                modifier = Modifier.clickable {
                    navigate.navigate(NavigableGraph.Task(item.text))
                },
                image = item.image,
                text = item.text
            )
        }
    }
}
