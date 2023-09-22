package hoods.com.jetexpense.util

import androidx.compose.ui.graphics.Color
import hoods.com.jetexpense.data.local.models.Expense
import hoods.com.jetexpense.data.local.models.Income
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object Util {
    val incomeColor = listOf(
        Color(0xFF37EFBA),
        Color(0xFF04B97F),
        Color(0xFF005D57),
        Color(0xFF29B82F),
        Color(0xFF008605)
    )
    val expenseColor = listOf(
        Color(0xFFFFD7D0),
        Color(0xFFFFDC78),
        Color(0xFFFFAC12),
        Color(0xFFFFAC12),
        Color(0xFFFF6951),
    )
}

private val date = Calendar.getInstance()

private fun formatDate(
    date: Calendar,
): String {
    val c = date
    val randomDay = 1..7
    c.set(Calendar.DAY_OF_WEEK, randomDay.random())
    return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .format(c.time)
}

fun formatDate(date: Date): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        .format(date)

fun formatDays(days: Int) = when (days) {
    1 -> "Mon"
    2 -> "Tue"
    3 -> "Wed"
    4 -> "thus"
    5 -> "Fri"
    6 -> "Sat"
    7 -> "Sun"
    else -> "Unknown"
}

fun formatDays(date: Date): String {
    val sdf = SimpleDateFormat("EE", Locale.getDefault())
    return sdf.format(date)
}

fun getColor(amount: Float, colors: List<Color>): Color {
    return when {
        amount < 500 -> {
            colors[0]
        }

        amount < 1000 -> {
            colors[1]
        }

        amount < 5000 -> {
            colors[2]
        }

        amount < 10000 -> {
            colors[3]
        }

        else -> {
            colors[4]
        }
    }
}

fun formatAmount(amoount: Float): String {
    return AmountDecimalFormat.format(amoount)
}
fun formatAmount(amount: Double):String{
    val df = DecimalFormat("###,###.00")
    return df.format(amount)
}

private val AmountDecimalFormat = DecimalFormat("#,###.##")



val listIncome = listOf<Income>(
    Income(
        id = 4012,
        title = "Roupas",
        description = "Loja Ike",
        incomeAmount = 2000.3,
        entryDate = "vulputate",
        date = Date()
    ),
    Income(
        id = 6893,
        title = "Oculos",
        description = "Otica 1",
        incomeAmount = 18.19,
        entryDate = "vehicula",
        date =Date()
    ),
    Income(
        id = 68923,
        title = "Notebook",
        description = "DELL",
        incomeAmount = 23.2,
        entryDate = "vehicula",
        date =Date()
    )
)
val listExpense = listOf<Expense>(
    Expense(
        id = 3062,
        title = "Medico",
        description = "Clinica DT",
        expenseAmount = 22.3,
        category = "ea",
        entryDate = "pharetra",
        date = Date()
    ),
    Expense(
        id = 30622,
        title = "Aluguel",
        description = "Despesas fixas",
        expenseAmount = 342.3,
        category = "ea",
        entryDate = "pharetra",
        date = Date()
    ),
    Expense(
        id = 303622,
        title = "Convenio",
        description = "Amil",
        expenseAmount = 2.43,
        category = "ea",
        entryDate = "pharetra",
        date = Date()
    )
)