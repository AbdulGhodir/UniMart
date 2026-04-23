package component.pages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import component.ui.Input
import model.UserSource

fun handleEditProfile(username: String, namaLengkap: String, email: String, noTelp: String) {
    val user = UserSource.user
    user.username = username
    user.namaLengkap = namaLengkap
    user.email = email
    user.noTelp = noTelp
}

@Composable
fun EditProfile(modifier: Modifier, navController: NavController) {
    val user = UserSource.user
    var namaLengkap by remember { mutableStateOf(user.namaLengkap) }
    var username by remember { mutableStateOf(user.username) }
    var email by remember { mutableStateOf(user.email) }
    var noTelp by remember { mutableStateOf(user.noTelp) }
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 14.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                IconButton(onClick = { navController.navigate("profile") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                } }, modifier = Modifier.size(40.dp).clip(
                    CircleShape),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0x33FFFFFF),
                        contentColor = Color.White
                    )) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Back", tint = Color.White)
                }
                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Edit Profile",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(onClick = {
                    navController.navigate("profile") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    handleEditProfile(username, namaLengkap, email, noTelp)
                    Toast.makeText(context, "Berhasil disimpan", Toast.LENGTH_SHORT).show() }, modifier = Modifier.height(40.dp)
                    .width(115.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0x33FFFFFF),
                        contentColor = Color.White
                    )) {
                    Text(text = "Simpan", color = Color.White)
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(modifier = Modifier.size(100.dp).background(Color(0x301D3F73),
                    shape = RoundedCornerShape(16.dp))
                    .border(BorderStroke(2.dp, Color(0x33FFFFFF)), shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center) {
                    Text(text = user.username.get(0).uppercase(), color = Color.White)
                    Box(modifier = Modifier.background(Color(0x33FFFFFF), shape = CircleShape)
                        .align(Alignment.BottomEnd)
                        .padding(10.dp),
                        contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color.White, modifier = Modifier.size(16.dp))
                    }
                }
                Text(text = "ketuk untuk ganti profil anda", fontSize = 12.sp, color = Color(0x80FFFFFF))

            }
        }
        Column(modifier = Modifier.weight(1f)
            .padding(PaddingValues(horizontal = 14.dp, vertical = 16.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Input(label = "Nama Lengkap", text = namaLengkap, onValueChange = { namaLengkap = it })
            Input(label = "Username", text = username, onValueChange = { username = it })
            Input(label = "Email", text = email, onValueChange = { email = it })
            Input(label = "No. Telepon", text = noTelp, onValueChange = { noTelp = it })
        }
    }
}