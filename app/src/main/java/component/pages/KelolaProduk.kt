package component.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.blockbusteruwu.unimart.R
import com.blockbusteruwu.unimart.formatRibuan
import component.ui.SearchInput
import component.viewmodel.BarangViewModel
import component.viewmodel.KelolaProdukViewModel

@Composable
fun KelolaProduk(modifier: Modifier = Modifier, navController: NavController, barangViewModel: BarangViewModel, viewModel: KelolaProdukViewModel = viewModel()) {
    val context = androidx.compose.ui.platform.LocalContext.current
    var search by remember { mutableStateOf("") }

    val filteredBarang = viewModel.produkSaya.filter { it.nama.contains(search, ignoreCase = true) }

    Column(modifier = modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 14.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = { navController.navigate("profile") {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    } },
                    modifier = Modifier.size(40.dp).clip(
                        CircleShape
                    ),
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(0x33FFFFFF),
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Kelola Produk",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            SearchInput(search = search, onSearchChange = { search = it })
        }
        Column(modifier = Modifier.weight(1f).padding(PaddingValues(horizontal = 14.dp, vertical = 16.dp))) {
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Daftar Produk", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Button(onClick = { navController.navigate("jualProduk") {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                } }) {
                    Text(text = "Jual Produk")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            if(viewModel.isLoading) {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    CircularProgressIndicator()
                    Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                }
            } else {
                if(filteredBarang.isEmpty()) {
                    Row(modifier = Modifier.fillMaxWidth().padding(PaddingValues(vertical = 30.dp)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color(0x801D3F73), modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "\"${search}\" Tidak ditemukan", fontSize = 16.sp, color = Color.Gray)
                    }
                } else {
                    LazyColumn(modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        items(filteredBarang) { barang ->
                            Card(modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
                                colors = CardDefaults.cardColors(Color.White),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)) {
                                Row(modifier = Modifier.fillMaxWidth().padding(8.dp),
                                    verticalAlignment = Alignment.Top,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                    AsyncImage(
                                        model = barang.gambar,
                                        contentDescription = barang.nama,
                                        placeholder = painterResource(id = R.drawable.img_barang1),
                                        error = painterResource(id = R.drawable.img_barang2),
                                        modifier = Modifier.size(100.dp).clip(RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.Top) {
                                            Box(modifier = Modifier.weight(1f)) {
                                                Text(text = barang.nama, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                                            }
                                            var showMenu by remember { mutableStateOf(false) }
                                            Box {
                                                IconButton(onClick = { showMenu = true }) {
                                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                                                }
                                                androidx.compose.material3.DropdownMenu(
                                                    expanded = showMenu,
                                                    onDismissRequest = { showMenu = false }
                                                ) {
                                                    androidx.compose.material3.DropdownMenuItem(
                                                        text = { Text("Hapus") },
                                                        onClick = {
                                                            showMenu = false
                                                            barangViewModel.deleteProduct(barang.id) { success ->
                                                                if (success) {
                                                                    android.widget.Toast.makeText(context, "Produk berhasil dihapus!", android.widget.Toast.LENGTH_SHORT).show()
                                                                    viewModel.getDataProdukSaya()
                                                                } else {
                                                                    android.widget.Toast.makeText(context, "Gagal menghapus produk!", android.widget.Toast.LENGTH_SHORT).show()
                                                                }
                                                            }
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                        Text(text = "Rp ${barang.harga.formatRibuan()}", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                        Row(modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                            Box(contentAlignment = Alignment.Center, modifier = Modifier.width(60.dp).height(25.dp).background(Color(0x301D3F73), shape = RoundedCornerShape(10.dp))) {
                                                Text(text = "Stok: 12", fontSize = 10.sp, color = Color(0xFF1D3F73), modifier = Modifier.align(Alignment.Center))
                                            }
                                            Box(contentAlignment = Alignment.Center, modifier = Modifier.width(40.dp).height(25.dp).background(Color(0x3000FF00), shape = RoundedCornerShape(10.dp))) {
                                                Text(text = "Aktif", fontSize = 10.sp, color = Color.Green, modifier = Modifier.align(Alignment.Center))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}