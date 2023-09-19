package hoods.com.jetexpense.presentation.expense

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.presentation.components.ExpenseRow
import hoods.com.jetexpense.presentation.components.TransactionStatement
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.Util
import hoods.com.jetexpense.util.getColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun ExpenseScreen(
    modifier: Modifier = Modifier,
    expenses: List<Expense>,
    onExpenseItemClick: (id: Int) -> Unit,
    onExpenseItemDelete: (id: Int) -> Unit
) {
    TransactionStatement(
        items = expenses,
        colors = { getColor(it.expenseAmount.toFloat(), Util.expenseColor) },
        amounts = { it.expenseAmount.toFloat() },
        amountsTotal = expenses.sumOf { it.expenseAmount }.toFloat(),
        circleLabel = "Pay",
        onItemSwiped = { onExpenseItemDelete.invoke(it.id) },
        key = { it.id },
        modifier = modifier
    ) {
        ExpenseRow(
            name = it.title,
            description = " Receive ${formatDetailsDate(it.date)}",
            amount = it.expenseAmount.toFloat(),
            color = getColor(it.expenseAmount.toFloat(), Util.expenseColor)
        )
    }
}

@Preview(
    showSystemUi = true,
    name = "Light mode",
    showBackground = true
)
@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode"
)
@Composable
private fun IncomeScreenPreview(

) {

    val list = listOf<Expense>(
        Expense(
            id = 3062,
            title = "magnis",
            description = "mutat",
            expenseAmount = 2.3,
            category = "ea",
            entryDate = "pharetra",
            date = Date()
        )
    )
    JetExpenseTheme {
        Surface {
            ExpenseScreen(
                modifier = Modifier,
                expenses = list,
                {},
                {})
        }
    }
}

fun formatDetailsDate(date: Date) {
    SimpleDateFormat("MM dd", Locale.getDefault()).format(date)
}