package hoods.com.jetexpense.presentation.income

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.presentation.components.IncomeRow
import hoods.com.jetexpense.presentation.components.TransactionStatement
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.Util
import hoods.com.jetexpense.util.getColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable
fun IncomeScreen(
    modifier: Modifier = Modifier,
    incomes: List<Income>,
    onIncomeItemClick: (id: Int) -> Unit,
    onIncomeItemDelete: (id: Int) -> Unit
) {
    TransactionStatement(
        items = incomes,
        colors = { getColor(it.incomeAmount.toFloat(), Util.incomeColor) },
        amounts = { it.incomeAmount.toFloat() },
        amountsTotal = incomes.sumOf { it.incomeAmount }.toFloat(),
        circleLabel = "Receive",
        onItemSwiped = { onIncomeItemDelete.invoke(it.id) },
        key = { it.id },
        modifier = modifier
    ) {
        IncomeRow(name = it.title, description = " Receive ${formatDetailsDate(it.date)}", amount = it.incomeAmount.toFloat() , color = getColor(it.incomeAmount.toFloat(), Util.incomeColor)  )
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

    val list = listOf<Income>(
        Income(
            id = 4012,
            title = "quas",
            description = "saperet",
            incomeAmount = 2000.3,
            entryDate = "vulputate",
            date = Date()
        )
    )
    JetExpenseTheme {
        Surface {
            IncomeScreen(
                modifier = Modifier,
                incomes = list,
                onIncomeItemClick = {},
                onIncomeItemDelete = {})
        }
    }
}

fun formatDetailsDate(date :Date){
    SimpleDateFormat("MM dd", Locale.getDefault()).format(date)
}