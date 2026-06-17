package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.blockbusteruwu.unimart.R
import com.blockbusteruwu.unimart.formatRibuan
import component.viewmodel.BarangViewModel
import component.viewmodel.UserViewModel

@Composable
fun DaftarProduk(
    modifier: Modifier = Modifier,
    navController: NavController,
    barangViewModel: BarangViewModel,
    userViewModel: UserViewModel
) {
    val posts by barangViewModel.sellerProducts
    val isLoading by barangViewModel.isLoading
    val currentUser by userViewModel.currentUser

    LaunchedEffect(currentUser.email) {
        barangViewModel.loadSellerProducts(currentUser.email)
    }

    val myProducts = posts.filter { !it.isTerjual }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Produk Saya (${myProducts.size})",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    CircularProgressIndicator()
                    Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        } else if (myProducts.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "Belum ada produk yang dijual", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSecondary)
                    Text(text = "Mulai jual barangmu sekarang!", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSecondary)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(myProducts) { barang ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .clickable { navController.navigate("detailProduk/${barang.id}") },
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = barang.gambar,
                                contentDescription = barang.nama,
                                placeholder = painterResource(id = R.drawable.img_barang1),
                                error = painterResource(id = R.drawable.img_barang2),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                                    .width(100.dp)
                                    .height(100.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = barang.nama, fontWeight = FontWeight.Bold, fontSize = 15.sp, maxLines = 1)
                                Text(text = barang.kategori, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                                Text(
                                    text = "Rp. ${barang.harga.formatRibuan()}",
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}