package hoods.com.jetexpense.presentation.income

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.data.respository.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeViewModel @Inject constructor(
    private val respository: Repository
) : ViewModel() {
    var incomeState by mutableStateOf(IncomeState())
        private set

    init {
        getAllIncome()
    }

    fun getAllIncome() = viewModelScope.launch {
        respository.income.collect {
            incomeState = incomeState.copy(
                income = it
            )
        }
    }

    fun deleteIncome(id: Int) {
        viewModelScope.launch {
            respository.deleteIncome(id)
        }
    }

}

data class IncomeState(
    val income: List<Income> = emptyList()
)