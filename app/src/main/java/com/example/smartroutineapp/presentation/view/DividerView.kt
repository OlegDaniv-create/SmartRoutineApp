package com.example.smartroutineapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smartroutineapp.presentation.theme.Divider

@Composable
fun DividerView(paddingValues: PaddingValues = PaddingValues()) {
    HorizontalDivider(
        modifier = Modifier
            .height(2.dp)
            .padding(paddingValues)
            .background(Divider)
    )
}