package hoods.com.jetexpense.presentation.transaction

import android.media.MediaRouter.RouteCategory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.data.respository.Repository
import hoods.com.jetexpense.presentation.navigation.ExpenseDestination
import hoods.com.jetexpense.presentation.navigation.IncomeDestination
import hoods.com.jetexpense.presentation.navigation.JetExpenseDestination
import hoods.com.jetexpense.util.Category
import hoods.com.jetexpense.util.formatDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val repository: Repository,
    @Assisted private val transactionId: Int,
    @Assisted private val transactionType: String
) : ViewModel(), TransactionCallBack {

    var state by mutableStateOf(TransactionState())
        private set

    val income: Income
        get() = state.run {
            Income(
                title = title,
                description = description,
                incomeAmount = amount.toDouble(),
                entryDate = formatDate(date),
                date = date,
                id = id
            )
        }

    val expense: Expense
        get() = state.run {
            Expense(
                id = id,
                title = title,
                description = description,
                expenseAmount = amount.toDouble(),
                entryDate = formatDate(date),
                date = date,
                category = category.title
            )
        }

    override fun onTitleChange(newValue: String) {
        state = state.copy(
            title = newValue
        )
    }

    override fun onAmountChange(newValue: String) {
        state = state.copy(
            amount = newValue
        )
    }

    override fun onDescriptionChange(newValue: String) {
        state = state.copy(
            description = newValue
        )
    }

    override fun onTransactionTypeChange(newValue: String) {
        state = state.copy()
    }

    override fun onDateChange(newValue: Long?) {
        newValue?.let {
            state = state.copy(date = Date(it))
        }
    }

    override fun onScreenTypeChange(newValue: JetExpenseDestination) {
        state = state.copy(
            transactionScreen = newValue
        )
    }

    override fun onOpenDialog(newValue: Boolean) {
        state = state.copy(
            openDialog = newValue
        )
    }

    override fun addIncome() {
        viewModelScope.launch {
            repository.insertIncome(income)
        }
    }

    override fun addExpense() {
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

    override fun getIncome(id: Int) {
        viewModelScope.launch {
            repository.getIncomeById(id).collectLatest {
                state = state.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    amount = it.incomeAmount.toString(),
                    transactionScreen = IncomeDestination,
                    date = it.date
                )
            }
        }
    }

    override fun getExpense(id: Int) {
        viewModelScope.launch {
            repository.getExpenseById(id).collectLatest {
                state = state.copy(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    amount = it.expenseAmount.toString(),
                    transactionScreen = ExpenseDestination,
                    date = it.date,
                    category = Category.values()
                        .find { category ->
                            category.title == it.category
                        } ?: Category.CLOTHING
                )
            }
        }
    }

    override fun updateIncome() {
        viewModelScope.launch {
            repository.updateIncome(income)
        }
    }

    override fun updateExpense() {
        viewModelScope.launch {
            repository.updateExpense(expense)
        }
    }

}

data class TransactionState(
    val id: Int = 0,
    val title: String = "",
    val amount: String = "",
    val category: Category = Category.CLOTHING,
    val date: Date = Date(),
    val description: String = "",
    val transactionScreen: JetExpenseDestination = IncomeDestination,
    val openDialog: Boolean = true,
    val isUpdatingTransaction: Boolean = false
)

@Suppress("UNCHECKED_CAST")
class TransactionViewModelFactory(
    private val assistedFactory: TranactionAssistedFactory,
    private val id: Int,
    private val type: String?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(id, type) as T
    }
}

@AssistedFactory
interface TranactionAssistedFactory {
    fun create(id: Int, type: String?): TransactionViewModel
}