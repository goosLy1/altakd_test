package com.example.altakidtest.ui.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.altakidtest.navigation.MainScreens

@Composable
fun AltakidBottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val bottomBarItems = listOf(
        MainScreens.Profile,
        MainScreens.FamilyAccount,
        MainScreens.Settings
    )

    val menuItemSelectedColor = MaterialTheme.colorScheme.primary
    val menuItemUnselectedColor = MaterialTheme.colorScheme.outline
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
//                elevation = 2.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        bottomBarItems.forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconResId),
                        contentDescription = stringResource(id = item.titleResId)
                    )
                },
                label = { Text(text = stringResource(id = item.titleResId)) },
                selectedContentColor = menuItemSelectedColor,
                unselectedContentColor = menuItemUnselectedColor
            )
        }
    }

}