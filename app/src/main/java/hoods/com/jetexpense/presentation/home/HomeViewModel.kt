package hoods.com.jetexpense.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.data.respository.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val income = repository.income
    private val expense = repository.expense

    var homeUiState by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            combine(income, expense) { incomeList: List<Income>,
                                       expenseList: List<Expense> ->
                homeUiState.copy(
                    income = incomeList,
                    expense = expenseList,
                    totalExpense = expenseList.sumOf {
                        it.expenseAmount
                    }.toFloat(),
                    totalIncome = incomeList.sumOf {
                        it.incomeAmount
                    }.toFloat()
                )
            }.collectLatest {
                homeUiState = it
            }
        }
    }

    fun insertIncome() = viewModelScope.launch {
        repository.insertIncome(Income(
            id = 0,
            "Teste",
            "Testando o insert",
            incomeAmount = 10.0,
            entryDate =  "",
            date = Date()
        ))
    }

    fun insertExpense() = viewModelScope.launch {
        repository.insertExpense(Expense(
            id = 0,
            "Teste",
            "Testando o insert",
            expenseAmount = 10.0,
            category =  "Expense",
            entryDate =  "",
            date = Date()
        ))
    }}

data class HomeUiState(
    val income: List<Income> = emptyList(),
    val expense: List<Expense> = emptyList(),
    val totalExpense: Float = 0f,
    val totalIncome: Float = 0f
)