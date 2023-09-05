package hoods.com.jetexpense.data.respository

import hoods.com.jetexpense.data.local.ExpenseDao
import hoods.com.jetexpense.data.local.IncomeDao
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.local.models.Income
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val incomeDao: IncomeDao,
    private val expenseDao: ExpenseDao,
    private val dispacher: CoroutineDispatcher,
) : Repository {

    override val income: Flow<List<Income>>
        get() = incomeDao.getAllIncome()

    override val expense: Flow<List<Expense>>
        get() = expenseDao.getAllExpense()

    override suspend fun insertIncome(income: Income) = withContext(dispacher)
    {
        incomeDao.insertIncome(income)
    }

    override suspend fun insertExpense(expense: Expense) = withContext(dispacher) {
        expenseDao.insertExpense(expense)
    }

    override fun getIncomeById(id: Int): Flow<Income> = incomeDao.getIncomeById(id)

    override fun getExpenseById(id: Int): Flow<Expense> = expenseDao.getExpenseById(id)

    override suspend fun updateIncome(income: Income) = withContext(dispacher) {
        incomeDao.updateIncome(income)
    }

    override suspend fun updateExpense(expense: Expense) = withContext(dispacher) {
        expenseDao.updateExpense(expense)
    }

    override suspend fun deleteIncome(id: Int) : Int = withContext(dispacher) {
        incomeDao.deleteIncome(id)
    }

    override suspend fun deleteExpense(id: Int) : Int = withContext(dispacher) {
        expenseDao.deleteExpense(id)
    }
}