package hoods.com.jetexpense.presentation.transaction

import android.media.MediaRouter.RouteCategory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import hoods.com.jetexpense.data.local.models.Income
import hoods.com.jetexpense.data.respository.Repository
import hoods.com.jetexpense.presentation.navigation.IncomeDestination
import hoods.com.jetexpense.presentation.navigation.JetExpenseDestination
import hoods.com.jetexpense.util.Category
import java.util.Date
import javax.inject.Inject

class TransactionViewModel @Inject constructor(
    private val repository: Repository,
    @Assisted private val transactionId: Int,
    @Assisted private val transactionType: String
) : ViewModel(), TransactionCallBack {

    var state by mutableStateOf(TransactionState())
        private set

//    val income: Income
//        get()

    override fun onTitleChange(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun onAmountChange(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun onDescriptionChange(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun onTransactionTypeChange(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun onDateChange(newValue: Long?) {
        TODO("Not yet implemented")
    }

    override fun onScreenTypeChange(newValue: JetExpenseDestination) {
        TODO("Not yet implemented")
    }

    override fun onOpenDialog(newValue: String) {
        TODO("Not yet implemented")
    }

    override fun addIncome() {
        TODO("Not yet implemented")
    }

    override fun addExpense() {
        TODO("Not yet implemented")
    }

    override fun getIncome(id: Int) {
        TODO("Not yet implemented")
    }

    override fun getExpense(id: Int) {
        TODO("Not yet implemented")
    }

    override fun updateIncome() {
        TODO("Not yet implemented")
    }

    override fun updateExpense() {
        TODO("Not yet implemented")
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