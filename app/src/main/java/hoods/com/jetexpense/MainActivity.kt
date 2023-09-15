package hoods.com.jetexpense

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.HomeViewModel
import hoods.com.jetexpense.presentation.income.IncomeScreen
import hoods.com.jetexpense.ui.theme.JetExpenseTheme
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetExpenseTheme {
                val homeViewModel: HomeViewModel = viewModel()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        state = homeViewModel.homeUiState,
                        modifier = Modifier,
                        onIncomeclick = {},
                        onClickSeeAllIncome = {},
                        onInsertExpense = homeViewModel::insertExpense,
                        onClickSeeAllExpense = {} ,
                        onInsertIncome = homeViewModel::insertIncome,
                        onExpenseclick = {}
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