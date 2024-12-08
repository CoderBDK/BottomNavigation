package com.coderbdk.bottomNav

import android.content.res.Resources
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun <T> DynamicBottomNavigation(
    modifier: Modifier = Modifier,
    menus: List<T>,
    content: @Composable (item: T, Boolean) -> Unit,
    onNavigate: (T) -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    var indicatorWidth by remember { mutableStateOf(0.dp) }
    val indicatorOffset by animateDpAsState(
        targetValue = selectedIndex * indicatorWidth,
        label = "indicator"
    )
    BottomAppBar {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            LazyRow(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .onGloballyPositioned {
                        indicatorWidth = (it.size.width.toDp()) / menus.size
                    },
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                itemsIndexed(menus) { index, menu ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable {
                                selectedIndex = index
                                onNavigate(menu)
                            }
                    ) {
                        content.invoke(menu, index == selectedIndex)
                    }
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape)
            ) {
                Spacer(modifier = Modifier.width(indicatorOffset))
                Box(
                    Modifier
                        .width(indicatorWidth)
                        .height(8.dp)
                        .background(MaterialTheme.colorScheme.onSurfaceVariant, shape = CircleShape)
                )
            }
        }
    }

}

private fun Int.toDp(): Dp = (this / Resources.getSystem().displayMetrics.density).dp

@Composable
fun DynamicBottomNavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    icon: @Composable () -> Unit,
    label: @Composable () -> Unit,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors(),
) {
    Column(
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .height(IntrinsicSize.Min),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val iconColor = if (selected) colors.selectedIconColor else colors.unselectedIconColor

        Box(
            modifier = Modifier
                .size(48.dp, height = 24.dp)
                .background(
                    color = if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides iconColor, content = icon)
        }
        Box(Modifier.weight(1f)) {
            val labelColor = if (selected) colors.selectedTextColor else colors.unselectedTextColor
            CompositionLocalProvider(LocalContentColor provides labelColor, content = label)
        }

    }
}

