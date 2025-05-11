package com.example.smartroutineapp.presentation.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.Typography

@Composable
fun HorizontalCard(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    text: String
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors().copy(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier.background(PrimaryBlue.copy(alpha = 0.15f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(92.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(image),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = text.uppercase(),
                style = Typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}

@Preview
@Composable
fun HomeItemViewPreview() {
    HorizontalCard(
        image = R.drawable.image_1,
        text = "Daily Workday Focus Goals and Tasks to Keep You on Track"
    )
}