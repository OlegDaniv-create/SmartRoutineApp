package com.example.smartroutineapp.presentation.screens.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.view.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor() : ViewModel() {
    private val _taskState = MutableStateFlow<TaskScreenState>(TaskScreenState())
    val taskState: StateFlow<TaskScreenState> = _taskState.asStateFlow()

    init {
        val cards = listOf(
            ItemViewModel(
                itemId = 1,
                images = listOf(
                    R.drawable.big_image_1,
                    R.drawable.big_image_2,
                    R.drawable.big_image_3
                ),
                title = "Drink a Glass of Water",
                subTitle = "Start with a full glass to rehydrate after sleep",
                tag = "Hydration, Health",
                selected = false,
            ),

            ItemViewModel(
                itemId = 2,
                images = null,
                title = "Deep Breathing Exercise",
                subTitle = "Practice deep breathing for 5 minutes",
                tag = "Breathing, Mindfulness, Calm",
                selected = false,
            ),
            ItemViewModel(
                itemId = 3,
                images = null,
                title = "10-15 minutes of light exercise to energize",
                subTitle = null,
                tag = null,
                selected = false,
            ),

            ItemViewModel(
                itemId = 4,
                images = null,
                title = "Short Walk or Jog",
                subTitle = "Go outside for fresh air and movement",
                tag = "Outdoor, Movement",
                selected = false,
            ),
        )

        viewModelScope.launch {
            _taskState.emit(
                TaskScreenState(
                    items = cards
                )
            )
        }
    }

    fun selectItem(itemId: Int, selected: Boolean) {
        val currentItems = _taskState.value.items
        val index = currentItems.indexOfFirst { it.itemId == itemId }
        if (index == -1) return

        val updatedItems = currentItems.toMutableList().apply {
            this[index] = this[index].copy(selected = selected)
        }

        viewModelScope.launch {
            _taskState.emit(
                _taskState.value.copy(
                    items = updatedItems
                )
            )
        }
    }

    fun toggleAllItemsSelection() {
        val shouldSelectAll = _taskState.value.items.none { it.selected }
        val updatedItems = _taskState.value.items.map { it.copy(selected = shouldSelectAll) }

        viewModelScope.launch {
            _taskState.emit(
                _taskState.value.copy(items = updatedItems)
            )
        }
    }
}

data class TaskScreenState(
    val items: List<ItemViewModel> = emptyList(),
)
