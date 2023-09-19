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
import hoods.com.jetexpense.presentation.home.HomeViewModel
import hoods.com.jetexpense.presentation.income.IncomeViewModel
import hoods.com.jetexpense.presentation.navigation.JetExpenseDestination
import hoods.com.jetexpense.presentation.navigation.JetExpenseNavigation
import hoods.com.jetexpense.ui.theme.JetExpenseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetExpenseTheme {
                val homeViewModel: HomeViewModel = viewModel()
                val expenseViewModel: ExpenseViewModel = viewModel()
                val incomeViewModel: IncomeViewModel = viewModel()
                val navHostController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    JetExpenseNavigation(
                        modifier =  Modifier,
                        expenseViewModel = expenseViewModel,
                        incomeViewModel = incomeViewModel,
                        homeViewModel = homeViewModel,
                        navHostController = navHostController
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode"
)
@Composable
private fun MainThemePreview() {
    JetExpenseTheme {
        Surface {

        }
    }
}