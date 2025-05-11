package com.example.smartroutineapp.presentation.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.SubTextColor
import com.example.smartroutineapp.presentation.theme.Typography

@Composable
fun BottomSheetItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    text: String,
    textColor: Color = Color.Black,
    countItems: Int? = null,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(PrimaryBlue.copy(alpha = 0.1f))
                .padding(15.dp)
                .size(24.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = PrimaryBlue
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = text.uppercase(),
                color = textColor,
                style = Typography.bodySmall.copy(fontWeight = FontWeight.Bold)
            )
            countItems?.let {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = pluralStringResource(R.plurals.number_of_items, countItems, countItems),
                    color = SubTextColor,
                    style = Typography.bodySmall.copy(fontSize = 12.sp)
                )
            }
        }

        if (showArrow) {
            Icon(
                modifier = Modifier.padding(15.dp),
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetItemPreview() {
    BottomSheetItem(
        icon = R.drawable.ic_star,
        text = "ADD TO NEW LIST",
        countItems = 1,
        onClick = { },
    )
}