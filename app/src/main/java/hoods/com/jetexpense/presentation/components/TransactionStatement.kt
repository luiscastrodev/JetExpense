package hoods.com.jetexpense.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun <T> TransactionStatement(
    modifier: Modifier = Modifier,
    items: List<T>,
    colors: (T) -> Color,
    amounts: (T) -> Float,
    amountsTotal: Float,
    circleLabel: String,
    onItemSwiped: (T) -> Unit,
    key: (T) -> Int,
    rows: @Composable (T) -> Unit
) {
    LazyColumn(modifier = modifier){
        
    }
}


