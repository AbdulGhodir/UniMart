package component.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Input(modifier: Modifier = Modifier, label: String, text: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = label.uppercase(), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0x80000000))

        BasicTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            textStyle = TextStyle(color = Color.Black, fontSize = 14.sp),
            singleLine = true,
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background, RoundedCornerShape(14.dp))
                .border(BorderStroke(2.dp, Color(0x331D3F73)), shape = RoundedCornerShape(14.dp))
                .padding(PaddingValues(horizontal = 16.dp, vertical = if(text.isNotEmpty()) 16.dp else 8.dp)),
            decorationBox = { innerTextField ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier.weight(1f)) {
                        if (text.isEmpty()) {
                            Text(
                                text = label.capitalize(),
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                }
            }
        )
    }
}