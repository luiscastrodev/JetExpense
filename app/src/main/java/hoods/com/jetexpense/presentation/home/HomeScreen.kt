package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.presentation.AccountCard
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.formatAmount

@Composable
fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Column {
                Row {
                    val balance = state.totalIncome - state.totalExpense
                    Text(text = "Your total balance:")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "$" +
                                formatAmount(balance),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AccountCard(
                        cardTitle = "TOTAL INCOME",
                        amount = "+$" + formatAmount(state.totalIncome),
                        cardIcon = Icons.Default.ArrowDownward,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                end = 4.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                    )

                    AccountCard(
                        cardTitle = "TOTAL EXPENSE",
                        amount = "-$" + formatAmount(state.totalExpense),
                        cardIcon = Icons.Default.ArrowUpward,
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                end = 4.dp,
                                top = 8.dp,
                                bottom = 8.dp
                            )
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode",
)
@Composable
private fun HomeScreenPrev() {
    JetExpenseTheme {
        Surface() {
            HomeScreen(
                state = HomeUiState(
                    totalExpense = 10f,
                    totalIncome = 23f
                ), modifier = Modifier.fillMaxWidth()
            )
        }
    }
}