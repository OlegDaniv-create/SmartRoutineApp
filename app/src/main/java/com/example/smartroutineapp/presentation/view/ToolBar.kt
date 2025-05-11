package com.example.smartroutineapp.presentation.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.PrimaryTitle
import com.example.smartroutineapp.presentation.theme.SubtleText
import com.example.smartroutineapp.presentation.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleToolBar(modifier: Modifier = Modifier, title: String) {
    Column {
        TopAppBar(
            modifier = modifier.fillMaxWidth(),
            title = {
                Text(
                    modifier = Modifier.padding(vertical = 12.dp),
                    text = title,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.W600,
                    color = SubtleText
                )
            }
        )
        DividerView(paddingValues = PaddingValues(horizontal = 20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsToolBar(
    modifier: Modifier = Modifier,
    title: String,
    bottomText: String,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    navigateBack: () -> Unit
) {
    Column {
        TopAppBar(
            modifier = modifier.fillMaxWidth(),
            title = {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp),
                    text = title.uppercase(),
                    style = Typography.titleMedium,
                    color = PrimaryTitle
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable { navigateBack() },
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null
                )
            },
            actions = {
                Button(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .width(90.dp),
                    onClick = onClick,
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonColors(
                        containerColor = animateColorAsState(containerColor).value,
                        contentColor = animateColorAsState(contentColor).value,
                        disabledContainerColor = PrimaryBlue,
                        disabledContentColor = PrimaryBlue
                    )
                ) {
                    AnimatedContent(targetState = bottomText) { value ->
                        Text(
                            text = value,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            },
        )
        DividerView(paddingValues = PaddingValues(horizontal = 20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TitleToolBarPreview() {
    TitleToolBar(title = "Home")
}

@Preview(showBackground = true)
@Composable
fun DetailsToolBarPreview() {
    DetailsToolBar(
        bottomText = "Select All",
        navigateBack = { },
        title = "Detailed Morning Routine Tasks",
        onClick = { },
        containerColor = PrimaryBlue,
        contentColor = Color.White
    )
}
