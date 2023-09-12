package hoods.com.jetexpense.presentation.income

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hoods.com.jetexpense.data.local.models.Income


@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    incomes: List<Income>,
    onIncomeItemClick: (id: Int) -> Unit,
    onIncomeItemDelete: (id: Int) -> Unit
) {

}