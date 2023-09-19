package hoods.com.jetexpense.presentation.navigation

import hoods.com.jetexpense.R

sealed class JetExpenseDestination {
    abstract val iconResId: Int
    abstract val routePath: String
    abstract val pageTitle: String
}

object HomeDestination : JetExpenseDestination() {
    override val iconResId: Int
        get() = R.drawable.ic_home
    override val routePath: String
        get() = "home"
    override val pageTitle: String
        get() = "Home"

}

object IncomeDestination : JetExpenseDestination() {
    override val iconResId: Int
        get() = R.drawable.ic_income_dollar
    override val routePath: String
        get() = "income"
    override val pageTitle: String
        get() = "Income"
}

object ExpenseDestination : JetExpenseDestination() {
    override val iconResId: Int
        get() = R.drawable.ic_expense_dollar
    override val routePath: String
        get() = "expense"
    override val pageTitle: String
        get() = "Expense"
}