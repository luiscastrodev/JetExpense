package hoods.com.jetexpense.presentation.expense

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.respository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val respository: Repository
) : ViewModel() {
    var expenseState by mutableStateOf(ExpenseState())
        private set

    init {
        getAllExpense()
    }

    fun getAllExpense() = viewModelScope.launch {
        respository.expense.collect {
            expenseState = expenseState.copy(
                expenses = it
            )
        }
    }

    fun deleteExpense(id: Int) {
        viewModelScope.launch {
            respository.deleteExpense(id)
        }
    }
}

data class ExpenseState(
    val expenses: List<Expense> = emptyList()
)