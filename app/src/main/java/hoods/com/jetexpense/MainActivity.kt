package hoods.com.jetexpense

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hoods.com.jetexpense.presentation.expense.ExpenseViewModel
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.HomeUiState
import hoods.com.jetexpense.presentation.home.HomeViewModel
import hoods.com.jetexpense.presentation.income.IncomeViewModel
import hoods.com.jetexpense.presentation.navigation.JetExpenseNavigation
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import hoods.com.jetexpense.util.listExpense
import hoods.com.jetexpense.util.listIncome

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetExpenseTheme {
                val navHostController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetExpenseNavigation(
                        modifier =  Modifier,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Light mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
)
@Preview(
    name = "Dark Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
)
@Composable
private fun HomePreview() {

    JetExpenseTheme {
        Surface {
            HomeScreen(state = HomeUiState(
                income = listIncome,
                expense = listExpense,
                totalExpense = 12.13f,
                totalIncome = 14.15f
            ),
                modifier = Modifier,
                onIncomeclick = {},
                onClickSeeAllIncome = {},
                onExpenseclick = {},
                onClickSeeAllExpense = {},
                onInsertIncome = {},
                onInsertExpense = {})
        }
    }
}