package com.example.smartroutineapp.presentation.screens.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartroutineapp.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _homeState = MutableStateFlow<List<CardModel>>(emptyList())
    val homeState: StateFlow<List<CardModel>> = _homeState.asStateFlow()

    init {
        val cards = listOf(
            CardModel(
                image = R.drawable.image_1,
                text = "Detailed Morning Routine Tasks"
            ),
            CardModel(
                image = R.drawable.image_2,
                text = "Daily Workday Focus Goals and Tasks to Keep You on Track"
            ),
            CardModel(
                image = R.drawable.image_3,
                text = "Evening Wind-Down Routine Steps"
            ),
            CardModel(
                image = R.drawable.image_4,
                text = "Comprehensive Sleep Routine Goals"
            )
        )
        viewModelScope.launch {
            _homeState.emit(cards)
        }
    }
}

data class CardModel(
    @DrawableRes val image: Int,
    val text: String
)
