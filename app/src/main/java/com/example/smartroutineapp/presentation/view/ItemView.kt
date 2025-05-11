package com.example.smartroutineapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.PrimaryTitle
import com.example.smartroutineapp.presentation.theme.SubTextColor
import com.example.smartroutineapp.presentation.theme.Typography
import kotlin.math.absoluteValue

@Composable
fun ItemView(
    item: ItemViewModel,
    onSelected: () -> Unit,
) {
    Card(colors = CardDefaults.cardColors().copy(containerColor = Color.White)) {
        item.images?.let { PagerStepThree(it) }
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.title,
                    style = Typography.titleMedium.copy(fontSize = 18.sp, lineHeight = 24.sp),
                    color = PrimaryTitle
                )
                item.subTitle?.let {
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = it,
                        fontStyle = FontStyle.Italic,
                        color = SubTextColor,
                        style = Typography.bodySmall
                    )
                }
                item.tag?.let {
                    Text(
                        modifier = Modifier.padding(top = 10.dp),
                        text = it,
                        style = Typography.bodyMedium,
                        color = PrimaryBlue
                    )
                }
            }
            SelectableIcon(item.selected) { onSelected() }
        }
    }
}

@Composable
fun PagerStepThree(list: List<Int>) {
    val pagerState = rememberPagerState(
        pageCount = { list.size }
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(236.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
        ) { page ->
            Image(
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(list[page]), contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 22.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                repeat(pagerState.pageCount) {
                    Box(
                        modifier = Modifier
                            .size(width = 8.dp, height = 8.dp)
                            .background(Color.LightGray, CircleShape)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .jumpingDotTransition(pagerState, 1f)
                    .background(PrimaryBlue, CircleShape)
                    .size(8.dp)
            )
        }
    }
}

private fun Modifier.jumpingDotTransition(pagerState: PagerState, jumpScale: Float) =
    graphicsLayer {
        val pageOffset = pagerState.currentPageOffsetFraction
        val scrollPosition = pagerState.currentPage + pageOffset
        val spacingPx = 8.dp.roundToPx()
        translationX = scrollPosition * (size.width + spacingPx)
        val targetScale = jumpScale - 1f
        val normalizedOffset = pageOffset.absoluteValue * 2
        val scale = if (pageOffset.absoluteValue < 0.5f) {
            1f + normalizedOffset * targetScale
        } else {
            jumpScale + (1f - normalizedOffset) * targetScale
        }
        scaleX = scale
        scaleY = scale
    }

data class ItemViewModel(
    val itemId: Int,
    val images: List<Int>?,
    val selected: Boolean,
    val title: String,
    val subTitle: String?,
    val tag: String?,
)

@Preview
@Composable
private fun ItemViewWithoutImagesPreview() {
    ItemView(
        item = ItemViewModel(
            itemId = 1,
            images = null,
            title = "Drink a Glass of Water",
            subTitle = "Start with a full glass to rehydrate after sleep",
            tag = "Hydration, Health",
            selected = false,
        ),
        onSelected = {},
    )
}

@Preview
@Composable
private fun ItemViewPreview() {
    ItemView(
        item = ItemViewModel(
            itemId = 1,
            images = listOf(
                R.drawable.big_image_1,
                R.drawable.big_image_2
            ),
            title = "Drink a Glass of Water",
            subTitle = "Start with a full glass to rehydrate after sleep",
            tag = "Hydration, Health",
            selected = false,
        ),
        onSelected = { },
    )
}
