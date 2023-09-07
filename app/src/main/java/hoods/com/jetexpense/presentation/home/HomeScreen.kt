package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.ui.theme.JetExpenseTheme

@Composable
fun HomeScreen(
    state: HomeUiState,
    modifier: Modifier
) {

}


@Preview(
    name = "Light mode",
    showBackground = true
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true, name = "Dark Mode"
)
@Composable
private fun HomeScreenPrev() {
    JetExpenseTheme {
        Surface {
            HomeScreen(state = HomeUiState(), modifier = Modifier)
        }
    }
}