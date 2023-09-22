package hoods.com.jetexpense.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.presentation.home.HomeUiState
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.Util
import hoods.com.jetexpense.util.formatAmount
import hoods.com.jetexpense.util.getColor
import java.util.Date

@Composable
fun AccountCard(
    modifier: Modifier = Modifier,
    cardTitle: String,
    amount: String,
    cardIcon: ImageVector?
) {
    Card(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = 10.dp,
                alignment = Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (cardIcon != null) {
                val iconColor =
                    if (cardTitle == "Total Expense")
                        Util.expenseColor.last()
                    else
                        Util.incomeColor.last()

                AccountIconItem(
                    modifier = Modifier.align(Alignment.End),
                    cardIcon = cardIcon,
                    color = iconColor
                )

            }

            Text(
                text = cardTitle, style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = .8f)
            )
            Text(
                text = amount, style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun AccountIconItem(
    modifier: Modifier = Modifier,
    cardIcon: ImageVector,
    color: Color,
) {
    Surface(
        shape = CircleShape,
        color = color.copy(alpha = .1f),
        contentColor = color,
        modifier = modifier.size(36.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(
                imageVector = cardIcon, contentDescription = null,
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}

@Composable
fun IncomeCard(
    account: HomeUiState,
    onClickSeeAll: () -> Unit,
    onIncomeClick: (id: Int) -> Unit
) {
    OverViewCard(
        title = "Income",
        amount = account.totalIncome,
        onClickSeeAll = onClickSeeAll,
        values = { it.incomeAmount.toFloat() },
        colors = { getColor(it.incomeAmount.toFloat(), Util.incomeColor) },
        data = account.income
    ) { income ->
        IncomeRow(
            name = income.title,
            description = income.description,
            amount = income.incomeAmount.toFloat(),
            color = getColor(income.incomeAmount.toFloat(), Util.incomeColor),
            modifier = Modifier.clickable {
                onIncomeClick.invoke(income.id)
            }
        )
    }
}

@Composable
fun ExpenseCard(
    account: HomeUiState,
    onClickSeeAll: () -> Unit,
    onExpenseClick: (id: Int) -> Unit
) {
    OverViewCard(
        title = "Expense",
        amount = account.totalExpense,
        onClickSeeAll = onClickSeeAll,
        values = { it.expenseAmount.toFloat() },
        colors = { getColor(it.expenseAmount.toFloat(), Util.expenseColor) },
        data = account.expense
    ) { expense ->
        ExpenseRow(
            name = expense.title,
            description = expense.description,
            amount = expense.expenseAmount.toFloat(),
            color = getColor(expense.expenseAmount.toFloat(), Util.expenseColor),
            modifier = Modifier.clickable {
                onExpenseClick.invoke(expense.id)
            }
        )
    }
}

@Composable
fun ExpenseRow(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    amount: Float,
    color: Color
) {
    BaseRow(
        color = color,
        title = name,
        subtitle = description,
        amount = amount,
        negative = true,
        modifier = modifier
    )
}

@Composable
fun IncomeRow(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    amount: Float,
    color: Color
) {
    BaseRow(
        color = color,
        title = name,
        subtitle = description,
        amount = amount,
        negative = false,
        modifier = modifier
    )
}

@Composable
private fun BaseRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: Float,
    negative: Boolean
) {
    val dollarSign = if (negative) "-R$ " else "R$ "
    val formattedAmount = formatAmount(amount.toDouble())

    Row(
        modifier = modifier.height(69.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography

        AccountIndicator(color, Modifier)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = subtitle,
                style = typography.bodySmall
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = dollarSign,
                style = typography.titleMedium,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Text(
                text = formattedAmount,
                style = typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Filled.ChevronRight,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 12.dp)
                .size(24.dp)
        )
    }
    JetExpenseDivider()
}

@Composable
fun JetExpenseDivider(modifier: Modifier = Modifier) {
    Divider(
        color = MaterialTheme.colorScheme.background,
        modifier = modifier,
        thickness = 2.dp
    )
}

@Composable
private fun AccountIndicator(color: Color, modifier: Modifier) {
    Spacer(
        modifier = modifier
            .size(4.dp, 36.dp)
            .background(color)
    )
}

@Composable
private fun <T> OverViewCard(
    title: String,
    amount: Float,
    onClickSeeAll: () -> Unit,
    values: (T) -> Float,
    colors: (T) -> Color,
    data: List<T>,
    row: @Composable (T) -> Unit
) {
    Card {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp)
            )
            OverViewDivider(
                data = data,
                values = values,
                colors = colors
            )
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp)
            ) {
                data.takeLast(SHOWN_ITENS).forEach {
                    row(it)
                }

                SeeAllButton(modifier = Modifier.clearAndSetSemantics {
                    contentDescription = "All $title"
                }, onClickSeeAll = onClickSeeAll)
            }
        }
    }
}

const val SHOWN_ITENS = 3

@Composable
fun SeeAllButton(
    modifier: Modifier = Modifier,
    onClickSeeAll: () -> Unit
) {
    TextButton(
        onClick = onClickSeeAll,
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth()
    ) {
        Text(text = "see all".uppercase())
    }
}

@Composable
fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        data.forEach { item ->
            Spacer(
                modifier = Modifier
                    .weight(values(item))
                    .height(1.dp)
                    .background(colors(item))
            )
        }
    }
}


@Preview(
    name = "Light mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode"
)
@Composable
private fun PreviewAccountCard() {

    JetExpenseTheme {
        Surface {
            IncomeCard(
                account = HomeUiState(
                    income = listOf(
                        Income(
                            id = 8586,
                            title = "nostra",
                            description = "adhuc",
                            incomeAmount = 22.23,
                            entryDate = "omnesque",
                            date = Date()
                        )
                    ),
                    expense = listOf(
                        Expense(
                            id = 7303,
                            title = "cetero",
                            description = "tellus",
                            expenseAmount = 26.27,
                            category = "montes",
                            entryDate = "hac",
                            date = Date()
                        )
                    ),
                    totalExpense = 16.17f,
                    totalIncome = 18.19f
                ),
                onClickSeeAll = { },
                onIncomeClick = { id -> }
            )
        }
    }
}