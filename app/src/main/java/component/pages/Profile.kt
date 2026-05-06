package component.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blockbusteruwu.unimart.R
import model.UserSource

@Composable
fun Profile(modifier: Modifier, navController: NavController) {
    val user = UserSource.user
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 14.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)) {
            Text(text = "Profile", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            Row(horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(80.dp)
                    .background(Color(0x33FFFFFF), shape = RoundedCornerShape(16.dp))
                    .border(BorderStroke(2.dp, Color(0x33FFFFFF)), shape = RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center) {
                    Text(text = user.username.get(0).uppercase(), fontSize = 26.sp, color = Color.White)
                }
                Column(modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start) {
                    Text(text = user.namaLengkap, fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                    Text(text = user.email, fontSize = 12.sp, color = Color(0x80FFFFFF))
                    OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.height(36.dp), colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF1b52a6)
                    )) {
                        Text(text = if(user.isPremium) "Premium" else "Daftar Premium", fontSize = 12.sp)
                    }

                }
            }
        }
        Column(modifier = Modifier.weight(1f)
            .verticalScroll(scrollState)
            .padding(PaddingValues(horizontal = 14.dp, vertical = 16.dp)),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {
            if(user.isPremium) {
                Text(text = "PENJUAL", fontSize = 16.sp, color = Color(0x80000000), fontWeight = FontWeight.SemiBold)
                Column(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFFFFFFF))
                    .border(1.dp, Color(0x401D3F73), shape = RoundedCornerShape(14.dp))
                    .padding(PaddingValues(horizontal = 14.dp, vertical = 16.dp))) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f),verticalArrangement = Arrangement.spacedBy(1.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Close", tint = Color(0xFFFFFFFF), modifier = Modifier.size(45.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    MaterialTheme.colorScheme.primary).padding(10.dp))
                            Text(text = "24", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                            Text(text = "Pesanan", fontWeight = FontWeight.Normal, fontSize = 12.sp, color = Color(0x80000000))
                        }
                        Column(modifier = Modifier.weight(1f),verticalArrangement = Arrangement.spacedBy(1.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Close", tint = Color(0xFFFFFFFF), modifier = Modifier.size(45.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    MaterialTheme.colorScheme.primary).padding(10.dp))
                            Text(text = "24", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                            Text(text = "Produk", fontWeight = FontWeight.Normal, fontSize = 12.sp, color = Color(0x80000000))
                        }
                        Column(modifier = Modifier.weight(1f),verticalArrangement = Arrangement.spacedBy(1.dp),
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(imageVector = Icons.Default.Done, contentDescription = "Close", tint = Color(0xFFFFFFFF), modifier = Modifier.size(45.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    MaterialTheme.colorScheme.primary).padding(10.dp))
                            Text(text = "22", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                            Text(text = "Terjual", fontWeight = FontWeight.Normal, fontSize = 12.sp, color = Color(0x80000000))
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxWidth().height(14.dp))
                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x401D3F73)))
                    Spacer(modifier = Modifier.fillMaxWidth().height(14.dp))
                    Row(modifier = Modifier.background(Color.White),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        TextButton(onClick = {  navController.navigate("kelolaProduk") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        } }) {
                            Icon(imageVector = Icons.Default.Build, contentDescription = "Riwayat", tint = Color(0xFFFFFFFF), modifier = Modifier.size(42.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    MaterialTheme.colorScheme.primary).padding(10.dp))
                            Spacer(modifier = Modifier.width(12.dp))
                            Box(Modifier.weight(1f)) {
                                Text(text = "Kelola Produk", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                            }
                            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "ArrowRight", tint = Color(0xFF000000), modifier = Modifier.size(32.dp))
                        }
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0x301D3F73))
                    .border(1.dp, Color(0x401D3F73), shape = RoundedCornerShape(14.dp))
                    .padding(PaddingValues(horizontal = 14.dp, vertical = 16.dp))) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "Close", tint = Color(0xFF1D3F73), modifier = Modifier.size(45.dp))
                        Column(modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.Start) {
                            Text("AKTIFKAN AKUN PREMIUM", letterSpacing = 0.5.sp, lineHeight = 10.sp, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1D3F73))
                            Text("Buka Toko Anda", fontSize = 16.sp, lineHeight = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000))
                            Text("Kelola produk & pantau penjualan", lineHeight = 10.sp, fontSize = 12.sp, fontWeight = FontWeight.Normal, color = Color(0x80000000))
                        }
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "ArrowRight", tint = Color(0xFF000000), modifier = Modifier.size(30.dp))
                    }
                }
            }
            Text(text = "AKTIVITAS", fontSize = 16.sp, color = Color(0x80000000), fontWeight = FontWeight.SemiBold)
            Column(modifier = Modifier.fillMaxWidth()
                .border(1.dp, Color(0x401D3F73), shape = RoundedCornerShape(15.dp))) {
                Row(modifier = Modifier.background(Color.White)
                    .padding(PaddingValues(horizontal = 6.dp, vertical = 6.dp)),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = { navController.navigate("editProfile") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    } }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit", tint = Color(0xFFFFFFFF), modifier = Modifier.size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                MaterialTheme.colorScheme.primary).padding(10.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(Modifier.weight(1f)) {
                            Text(text = "Edit Profile", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                        }
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "ArrowRight", tint = Color(0xFF000000), modifier = Modifier.size(32.dp))
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x401D3F73)))
                Row(modifier = Modifier.background(Color.White)
                    .padding(PaddingValues(horizontal = 6.dp, vertical = 6.dp)),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {
                        navController.navigate("favorite") {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorit", tint = Color(0xFFFFFFFF), modifier = Modifier.size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                MaterialTheme.colorScheme.primary).padding(10.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(Modifier.weight(1f)) {
                            Text(text = "Favorit", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                        }
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "ArrowRight", tint = Color(0xFF000000), modifier = Modifier.size(32.dp))
                    }
                }
                Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0x401D3F73)))
                Row(modifier = Modifier.background(Color.White)
                    .padding(PaddingValues(horizontal = 6.dp, vertical = 6.dp)),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    TextButton(onClick = {  navController.navigate("history") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    } }) {
                        Icon(painter = painterResource(R.drawable.ic_history), contentDescription = "Riwayat", tint = Color(0xFFFFFFFF), modifier = Modifier.size(42.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(
                                MaterialTheme.colorScheme.primary).padding(10.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(Modifier.weight(1f)) {
                            Text(text = "Riwayat", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = Color.Black)
                        }
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "ArrowRight", tint = Color(0xFF000000), modifier = Modifier.size(32.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {  navController.navigate("login") {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            } }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x30FF0000),
                    contentColor = Color.Red
                ),
                border = BorderStroke(1.dp, Color.Red)) {
                Text(text = "Keluar", fontSize = 16.sp, color = Color.Red)
            }
        }
    }
}