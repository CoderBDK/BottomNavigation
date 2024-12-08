package com.coderbdk.bottomnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.coderbdk.bottomNav.DynamicBottomNavigation
import com.coderbdk.bottomNav.DynamicBottomNavigationItem
import com.coderbdk.bottomnavigation.ui.theme.BottomNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationTheme {
                MainUI()
            }
        }
    }
}

sealed class Screen(
    val label: String,
    val icon: ImageVector
) {
    data object Home : Screen("Home", Icons.Default.Home)
    data object About : Screen("About", Icons.Default.Info)
    data object Profile : Screen("Profile", Icons.Default.Person)
    data object Settings : Screen("Settings", Icons.Default.Settings)
}

@Composable
fun MainUI(modifier: Modifier = Modifier) {
    DynamicBottomNavigation(
        menus = listOf(Screen.Home, Screen.About, Screen.Profile, Screen.Settings),
        content = { menu, selected ->
            DynamicBottomNavigationItem(
                selected = selected,
                label = {
                    Text(menu.label)
                },
                icon = {
                    Icon(menu.icon, "icon")
                },
            )
        },
        onNavigate = {}
    )
}

@Preview(showBackground = true)
@Composable
fun RockBottomNavPreview() {
    BottomNavigationTheme(
        darkTheme = true
    ) {
        MainUI()
    }
}