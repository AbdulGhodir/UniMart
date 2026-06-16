package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import component.viewmodel.HistoryViewModel
import component.viewmodel.UserViewModel
import model.Barang
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun History(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    historyViewModel: HistoryViewModel = viewModel()
) {
    val currentUser by userViewModel.currentUser
    val purchasedProducts by historyViewModel.purchasedProducts
    val isLoading by historyViewModel.isLoading

    LaunchedEffect(currentUser.email) {
        if (currentUser.email.isNotEmpty()) {
            historyViewModel.loadHistory(currentUser.email)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 10.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
                        .size(45.dp),
                ) {
                    Text(
                        text = "U",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                Text(
                    text = "UniMart",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "favorite",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { navController.navigate("favorite") }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "chat",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable { navController.navigate("daftarObrolan") }
                )
            }
        }

        when {
            isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CircularProgressIndicator()
                        Text(
                            text = "Memuat Riwayat...",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }

            purchasedProducts.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_history),
                            contentDescription = "empty history",
                            tint = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.size(64.dp)
                        )
                        Text(
                            text = "Belum ada riwayat pembelian",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            text = "Produk yang kamu beli akan muncul di sini",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Riwayat Pembelian",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${purchasedProducts.size} transaksi",
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }

                    items(purchasedProducts) { barang ->
                        HistoryItemCard(barang = barang)
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryItemCard(barang: Barang) {
    val dateFormatter = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id", "ID"))
    val tanggalBeli = if (barang.boughtAt > 0L) {
        dateFormatter.format(Date(barang.boughtAt))
    } else {
        "Tanggal tidak tersedia"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = barang.gambar,
                contentDescription = barang.nama,
                placeholder = painterResource(id = R.drawable.img_barang1),
                error = painterResource(id = R.drawable.img_barang2),
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                    .shadow(
                        elevation = 20.dp,
                        spotColor = Color.Black,
                        ambientColor = Color.Black
                    )
                    .width(110.dp)
                    .height(110.dp)
                    .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(top = 12.dp, bottom = 12.dp, end = 12.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = barang.nama,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = barang.kategori,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        text = "Dibeli: $tanggalBeli",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Rp. ${barang.harga.formatRibuan()}",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )

                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFF4CAF50).copy(alpha = 0.12f),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "Selesai",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF2E7D32)
                        )
                    }
                }
            }
        }
    }
}