package com.souzaemerson.muscleupgym.ui.components.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.souzaemerson.muscleupgym.ui.components.navigation.util.BottomNavigationItem
import com.souzaemerson.muscleupgym.ui.components.navigation.util.items

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navigateTo: (route: String) -> Unit
) {
    NavigationBar(modifier = modifier, containerColor = Color.Black) {
        var itemIndexSelected by rememberSaveable { mutableIntStateOf(0) }

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(text = item.label.uppercase(), color = Color.White) },
                selected = itemIndexSelected == index,
                onClick = {
                    itemIndexSelected = index
                    navigateTo(item.route)
                },
                icon = {
                    Icon(
                        imageVector = getImageVector(itemIndexSelected, index, item),
                        contentDescription = item.label,
                        tint = Color.White
                    )
                }
            )
        }
    }
}

@Composable
private fun getImageVector(
    itemIndexSelected: Int,
    index: Int,
    item: BottomNavigationItem
) = if (itemIndexSelected == index) {
    item.selectedIcon
} else {
    item.unselectedIcon
}

@Composable
@Preview(showBackground = true)
fun BottomNavigationPreview() {
    BottomNavigation()
}