package hoods.com.jetexpense.presentation

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleUp
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.presentation.home.HomeUiState
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.Util

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
            AccountCard(
                cardTitle = "TOTAL INCOME",
                amount = "150",
                cardIcon = Icons.Default.ArrowCircleUp
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