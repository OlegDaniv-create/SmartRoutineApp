package com.example.smartroutineapp.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.smartroutineapp.R
import com.example.smartroutineapp.presentation.theme.DarkGray
import com.example.smartroutineapp.presentation.theme.LightGray
import com.example.smartroutineapp.presentation.theme.PrimaryBlue
import com.example.smartroutineapp.presentation.theme.Typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyListBottomSheet(
    showBottomSheet: Boolean,
    onClose: (Boolean) -> Unit,
    sheetState: SheetState,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onClose(false) },
            sheetState = sheetState
        ) {
            BottomSheetTitle(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 19.dp
                ),
                sheetState = sheetState,
                scope = scope,
                onClose = onClose
            )
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                BottomSheetItem(
                    icon = R.drawable.ic_plus,
                    text = stringResource(R.string.bottom_sheet_item_add_to_new_list),
                    textColor = PrimaryBlue,
                    showArrow = false,
                    onClick = { }
                )
                BottomSheetItem(
                    icon = R.drawable.ic_star,
                    text = stringResource(R.string.bottom_sheet_item_add_to_favorites),
                    countItems = 23,
                    onClick = { }
                )
                BottomSheetItem(
                    icon = R.drawable.ic_sun,
                    text = stringResource(R.string.bottom_sheet_item_morning_tasks),
                    countItems = 10,
                    onClick = { }
                )
                BottomSheetItem(
                    icon = R.drawable.ic_pen,
                    text = stringResource(R.string.bottom_sheet_item_daily_tasks),
                    countItems = 1,
                    onClick = { }
                )
            }
            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomSheetTitle(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    scope: CoroutineScope,
    onClose: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.bottom_sheet_title_my_lists).uppercase(),
            style = Typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
        Icon(
            modifier = Modifier
                .clip(CircleShape)
                .background(LightGray.copy(alpha = 0.2f))
                .padding(12.dp)
                .clickable {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onClose(false)
                        }
                    }
                },
            painter = painterResource(R.drawable.ic_close),
            tint = DarkGray.copy(alpha = 0.5f),
            contentDescription = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyListBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState()
    MyListBottomSheet(
        showBottomSheet = true,
        onClose = {},
        sheetState = sheetState
    )
}