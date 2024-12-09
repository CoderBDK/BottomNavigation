A Jetpack Compose-based **Bottom Navigation Bar** with a dynamic indicator that adjusts size and position based on the selected menu item. This component is fully customizable and ideal for responsive applications.

---

## Features
- **Dynamic Indicator**: Automatically resizes and animates to match the selected menu item.
- **Customizable Menu Items**: Pass a list of menu items to dynamically build the navigation bar.
- **Smooth Transitions**: Leverages `animateDpAsState` for a visually appealing indicator movement.
- **Responsive Design**: Adapts seamlessly to different screen sizes and menu configurations.
- **Click Handling**: Simple integration for handling menu selection actions.

---

## Preview
![image](https://github.com/user-attachments/assets/bebacb3a-0ff6-4a21-8bdf-84a791a74b67)
![image](https://github.com/user-attachments/assets/df78eac1-70b2-4f82-b891-825eb7f47f63)

---

## Usage

### Add the Component
Use the `DynamicBottomNavigation` in your Jetpack Compose layout:

```kotlin
@Composable
fun <T> DynamicBottomNavigation(
    modifier: Modifier = Modifier,
    menus: List<T>,
    content: @Composable (item: T, Boolean) -> Unit,
    onNavigate: (T) -> Unit
)
```
```kotlin
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
```

## Installation

Clone the repository:

```bash
git clone https://github.com/CoderBDK/BottomNavigation.git
```
