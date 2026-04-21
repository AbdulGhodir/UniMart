package component.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchInput(search: String, onSearchChange: (String) -> Unit) {
    BasicTextField(
        value = search,
        onValueChange = { onSearchChange(it) },
        textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
        singleLine = true,
        modifier = Modifier.fillMaxWidth().background(Color.White, RoundedCornerShape(26.dp)).padding(PaddingValues(horizontal = 16.dp, vertical = if(search.isEmpty()) 16.dp else 6.dp)),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0x801D3F73), modifier = Modifier.size(24.dp))
                Box(Modifier.weight(1f)) {
                    if (search.isEmpty()) {
                        Text(
                            text = "Cari produk, kategori, dan brand",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                    innerTextField()
                }
                if(search.isNotEmpty()) {
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear", tint = Color.Red, modifier = Modifier.size(24.dp))
                    }
                }
            }
        }
    )
}