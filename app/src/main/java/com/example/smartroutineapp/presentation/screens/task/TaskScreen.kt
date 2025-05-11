package com.example.smartroutineapp.presentation.screens.task

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.NeutralGray
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.SubTextColor
import com.example.smartroutineapp.presentation.view.DetailsToolBar
import com.example.smartroutineapp.presentation.view.DividerView
import com.example.smartroutineapp.presentation.view.ItemView
import com.example.smartroutineapp.presentation.view.MyListBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    title: String,
    navigateBack: () -> Unit,
) {
    val screenState by viewModel.taskState.collectAsState()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val hasSelectedItem = screenState.items.any { it.selected }

    Scaffold(
        topBar = {
            DetailsToolBar(
                bottomText = if (hasSelectedItem) {
                    stringResource(R.string.cancel)
                } else {
                    stringResource(R.string.select_all)
                },
                title = title,
                navigateBack = navigateBack,
                onClick = { viewModel.toggleAllItemsSelection() },
                containerColor = if (hasSelectedItem) NeutralGray else PrimaryBlue,
                contentColor = if (hasSelectedItem) SubTextColor else Color.White
            )
        },
        floatingActionButton = {
            if (hasSelectedItem) {
                FloatingActionButton(
                    contentColor = Color.White,
                    shape = CircleShape,
                    containerColor = PrimaryBlue,
                    onClick = { showBottomSheet = true },
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 22.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Filled.Add,
                            contentDescription = stringResource(R.string.content_description_floating_action_button)
                        )
                        Text(
                            text = stringResource(R.string.add_to_list),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(vertical = 12.dp),
                            text = stringResource(R.string.task_screen_title),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W400
                        )
                        DividerView()
                    }
                    items(screenState.items) { item ->
                        ItemView(
                            item = item,
                            onSelected = { viewModel.selectItem(item.itemId, !item.selected) },
                        )
                    }
                }
            }
            MyListBottomSheet(
                showBottomSheet = showBottomSheet,
                sheetState = sheetState,
                onClose = { showBottomSheet = it }
            )
        }
    )
}
