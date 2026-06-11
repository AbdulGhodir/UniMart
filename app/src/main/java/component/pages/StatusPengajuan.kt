package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.blockbusteruwu.unimart.R
import viewmodel.UserViewModel

@Composable
fun StatusPengajuan(modifier: Modifier = Modifier, navController: NavController, userViewModel: UserViewModel) {
    val user by userViewModel.currentUser
    val pengajuan by userViewModel.pengajuan
    val statusPengajuan = pengajuan?.status ?: "NOT_SUBMITTED"

    val isPremium = user.isPremium || statusPengajuan == "APPROVED"
    val isPending = statusPengajuan == "PENDING"
    val isRejected = statusPengajuan == "REJECTED"


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
                    Text(text = "U", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.align(Alignment.Center))
                }

                Text(text = "UniMart", fontSize = 22.sp, color = MaterialTheme.colorScheme.onPrimary, fontWeight = FontWeight.Bold)
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
                        .clickable{ navController.navigate("favorite") }
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_message),
                    contentDescription = "chat",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(26.dp)
                        .clickable{ navController.navigate("daftarObrolan") }
                )
            }
        }

        Column(
            modifier= Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .width(310.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 3.dp
                )

            ) {
                if (isPending){
                    Column(
                        modifier= Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(300.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_timer),
                            contentDescription = "pending",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Text(
                            text = "Pengajuan Sedang Diproses",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Halo, form pendaftaran kamu sudah masuk dan sedang menunggu konfirmasi admin. Mohon tunggu maksimal 3x24 jam.",
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            onClick = {
                                    navController.navigate("profile")
                            }
                        ) {
                            Text(
                                text = "Kembali ke Profil"
                            )
                        }
                    }

                } else if (isPremium) {
                    Column(
                        modifier= Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(300.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_approved),
                            contentDescription = "approved",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Text(
                            text = "Pengajuan Telah Disetujui",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Halo, pendaftaran toko kamu sudah dikonfirmasi oleh admin dan berhasil disetujui. Toko kamu sekarang sudah aktif. Yuk, mulai jual produk pertamamu.",
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            onClick = {
                                navController.navigate("kelolaProduk")
                            }
                        ) {
                            Text(
                                text = "Mulai Berjualan!"
                            )
                        }
                    }
                } else if (isRejected) {
                    Column(
                        modifier= Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .height(300.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_approved),
                            contentDescription = "approved",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(50.dp)
                        )
                        Text(
                            text = "Maaf Pengajuan Anda Ditolak",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Halo, setelah dilakukan pengecekan oleh admin, form pendaftaran kamu belum dapat disetujui. Pastikan data yang dikirim sudah sesuai dan lengkap, lalu silakan melakukan pendaftaran kembali",
                            textAlign = TextAlign.Center,
                        )

                        Button(
                            onClick = {
                                navController.navigate("profile")
                            }
                        ) {
                            Text(
                                text = "Kembali ke Profile"
                            )
                        }
                    }
                }
            }

        }

//        if(isLoading) {
//            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
//                CircularProgressIndicator()
//                Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
//            }
//        } else {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize(),
//                contentPadding = PaddingValues(start = 10.dp, end = 10.dp, top = 25.dp, bottom = 40.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                item { Text(text = "Riwayat Pembelian", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
//
//                items(posts) { barang ->
//                    ColumnLayout(barang = barang, navController = navController)
//                }
//        }
//    }
    } // end outer Column
} // end StatusPengajuan