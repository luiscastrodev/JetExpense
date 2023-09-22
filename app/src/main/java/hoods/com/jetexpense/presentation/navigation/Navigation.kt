package hoods.com.jetexpense.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import hoods.com.jetexpense.presentation.expense.ExpenseScreen
import hoods.com.jetexpense.presentation.expense.ExpenseViewModel
import hoods.com.jetexpense.presentation.home.HomeScreen
import hoods.com.jetexpense.presentation.home.HomeViewModel
import hoods.com.jetexpense.presentation.income.IncomeScreen
import hoods.com.jetexpense.presentation.income.IncomeViewModel


@Composable
fun JetExpenseNavigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    incomeViewModel: IncomeViewModel = hiltViewModel(),
    expenseViewModel: ExpenseViewModel = hiltViewModel()
) {

    NavHost(navController = navHostController, startDestination = HomeDestination.routePath) {
        composable(
            route = HomeDestination.routePath
        ) {
            HomeScreen(
                state = homeViewModel.homeUiState,
                modifier = modifier,
                onIncomeclick = {},
                onClickSeeAllIncome = {
                    navHostController.navigateToSingleTop(IncomeDestination.routePath)
                },
                onInsertExpense = homeViewModel::insertExpense,
                onClickSeeAllExpense = {
                    navHostController.navigateToSingleTop(ExpenseDestination.routePath)
                },
                onInsertIncome = homeViewModel::insertIncome,
                onExpenseclick = {}
            )
        }
        composable(route = ExpenseDestination.routePath) {
            ExpenseScreen(
                modifier = modifier,
                expenses = expenseViewModel.expenseState.expenses,
                onExpenseItemClick = { expenseId ->

                },
                onExpenseItemDelete = {
                    expenseViewModel.deleteExpense(it)
                }
            )
        }
        composable(route = IncomeDestination.routePath) {
            IncomeScreen(
                modifier = modifier,
                incomes = incomeViewModel.incomeState.income,
                onIncomeItemClick = { id ->

                },
                onIncomeItemDelete = {
                    incomeViewModel.deleteIncome(it)
                }
            )
        }
    }
}

fun NavHostController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}



