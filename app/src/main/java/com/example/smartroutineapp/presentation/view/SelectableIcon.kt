package com.example.smartroutineapp.presentation.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.PrimaryBlue

@Composable
fun SelectableIcon(
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    val backgroundColor =
        animateColorAsState(
            if (isSelected) PrimaryBlue else PrimaryBlue.copy(alpha = 0.15f),
            animationSpec = tween(1600)
        )
    val iconTint = animateColorAsState(
        if (isSelected) Color.White else Color.Black,
        animationSpec = tween(600)
    )
    val iconRes =
        if (isSelected) painterResource(R.drawable.ic_check) else painterResource(R.drawable.ic_plus)

    AnimatedContent(
        targetState = iconRes,
        transitionSpec = {
            scaleIn(animationSpec = tween(600)) togetherWith fadeOut(animationSpec = tween(600))
        }
    ) { value ->
        Icon(
            painter = value,
            contentDescription = null,
            tint = iconTint.value,
            modifier = Modifier
                .background(backgroundColor.value, CircleShape)
                .padding(12.dp)
                .clickable { onSelected() }
        )
    }
}

@Preview
@Composable
fun SelectableIconPreview() {
    SelectableIcon(isSelected = true, onSelected = {})
}