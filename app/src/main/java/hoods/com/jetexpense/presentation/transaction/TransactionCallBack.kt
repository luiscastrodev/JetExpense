package hoods.com.jetexpense.presentation.transaction

import hoods.com.jetexpense.presentation.navigation.JetExpenseDestination

interface TransactionCallBack {
    fun onTitleChange(newValue: String)
    fun onAmountChange(newValue: String)
    fun onDescriptionChange(newValue: String)
    fun onTransactionTypeChange(newValue: String)
    fun onDateChange(newValue: Long?)
    fun onScreenTypeChange(newValue: JetExpenseDestination)
    fun onOpenDialog(newValue: String)
    fun addIncome()
    fun addExpense()
    fun getIncome(id: Int)
    fun getExpense(id: Int)
    fun updateIncome()
    fun updateExpense()
}