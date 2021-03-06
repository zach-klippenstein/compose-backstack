@file:Suppress("RemoveEmptyParenthesesFromAnnotationEntry")

package com.zachklipp.compose.backstack.viewer


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun SpinnerPreviewLight() {
  MaterialTheme(colors = lightColors()) {
    Surface {
      Spinner(listOf("foo"), selectedItem = "foo", onSelected = {}) { Text(it) }
    }
  }
}

@Preview
@Composable
private fun SpinnerPreviewDark() {
  MaterialTheme(colors = darkColors()) {
    Surface {
      Spinner(listOf("foo"), selectedItem = "foo", onSelected = {}) { Text(it) }
    }
  }
}

/**
 * Rough implementation of the Android Spinner widget.
 */
@Composable
internal fun <T : Any> Spinner(
    items: List<T>,
    selectedItem: T,
    onSelected: (item: T) -> Unit,
    drawItem: @Composable (T) -> Unit
) {
  if (items.isEmpty()) return

  var isOpen by remember { mutableStateOf(false) }

  Box {
    // Always draw the selected item.
    Row(Modifier.clickable(onClick = { isOpen = !isOpen })) {
      Box(
        modifier = Modifier
          .weight(1f)
          .align(Alignment.CenterVertically)
      ) {
        drawItem(selectedItem)
      }
      Icon(
        Icons.Default.ArrowDropDown,
        contentDescription = "Open spinner",
        modifier = Modifier
          .width(48.dp)
          .aspectRatio(1f)
      )
    }

    DropdownMenu(expanded = isOpen, onDismissRequest = { isOpen = false }) {
      for (item in items) {
        DropdownMenuItem(onClick = {
          isOpen = false
          if (item != selectedItem) onSelected(item)
        }) {
          drawItem(item)
        }
      }
    }
  }
}
