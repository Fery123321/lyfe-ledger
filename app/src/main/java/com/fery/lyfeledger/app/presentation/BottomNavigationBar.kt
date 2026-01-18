package com.fery.lyfeledger.app.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.fery.lyfeledger.app.R

@Composable
fun BottomNavigationBar(
    navController: NavController, modifier: Modifier = Modifier
) {
    val navItems = getBottomNavigationItems()
    val newBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = newBackStackEntry?.destination?.route
    val selectedNavIndex = getSelectedNavigationIndex(currentRoute, navItems)

    NavigationBar(
        modifier = modifier
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavIndex == index,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(0)
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.iconRes),
                        contentDescription = stringResource(item.titleRes),
                        modifier = modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    }

}

private fun getBottomNavigationItems() = listOf(
    NavigationBarItem(
        titleRes = R.string.bottom_navigation_task,
        iconRes = R.drawable.ic_task_bottom_navigation,
        route = NavigationRoute.Task
    ), NavigationBarItem(
        titleRes = R.string.bottom_navigation_habit,
        iconRes = R.drawable.ic_habit_bottom_navigation,
        route = NavigationRoute.Habit
    ), NavigationBarItem(
        titleRes = R.string.bottom_navigation_pomodoro,
        iconRes = R.drawable.ic_pomodoro_bottom_navigation,
        route = NavigationRoute.Pomodoro
    ), NavigationBarItem(
        titleRes = R.string.bottom_navigation_search,
        iconRes = R.drawable.ic_more_bottom_navigation,
        route = NavigationRoute.Settings
    )
)

private fun getSelectedNavigationIndex(
    currentRoute: String?, navItems: List<NavigationBarItem>
): Int = navItems.indexOfFirst { item ->
    when (currentRoute) {
        null -> false
        NavigationRoute.Task::class.qualifiedName -> item.route is NavigationRoute.Task
        NavigationRoute.Habit::class.qualifiedName -> item.route is NavigationRoute.Habit
        NavigationRoute.Pomodoro::class.qualifiedName -> item.route is NavigationRoute.Pomodoro
        NavigationRoute.Settings::class.qualifiedName -> item.route is NavigationRoute.Settings
        else -> item.route::class.qualifiedName == currentRoute
    }
}.takeIf { it >= 0 } ?: 0

data class NavigationBarItem(
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int,
    val route: NavigationRoute
)

@Preview
@Composable
private fun bottomNavigationBarPreview() {
    BottomNavigationBar(
        navController = rememberNavController()
    )
}