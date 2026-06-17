package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import component.viewmodel.DashboardViewModel
import com.blockbusteruwu.unimart.R
import component.ui.RowLayout
import model.Barang
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun Dashboard(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DashboardViewModel = viewModel()
) {
    val posts = viewModel.dataBarang
    val postsMurah = viewModel.barangMurah
    val isLoading = viewModel.isLoading

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 10.dp)
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/makanan") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_fork_spoon),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Makanan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/minuman") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cup),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Minuman", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/pakaian") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_shirt),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Pakaian", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/aksesoris") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_galssess),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Aksesoris", fontSize = 12.sp)
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/buku") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_book),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Buku", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/perlengkapan") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_tool),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Perlengkapan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/perawatan") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_skincare),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Perawatan", fontSize = 12.sp)
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .clickable { navController.navigate("search/elektronik") },
                            verticalArrangement = Arrangement.spacedBy(3.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_zap),
                                contentDescription = "favorite",
                                modifier = Modifier
                                    .background(
                                        MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .size(45.dp)
                                    .padding(10.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(text = "Elektronik", fontSize = 12.sp)
                        }
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Produk Terbaru", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "Lihat Semua",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable { navController.navigate("search") }
                    )
                }

                if (isLoading) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        CircularProgressIndicator()
                        Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(posts) { barang ->
                            RowLayout(barang = barang, navController = navController)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Murah Meriah < 50k",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                if (isLoading) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        CircularProgressIndicator()
                        Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        if (postsMurah.isEmpty()) {
                            item { Text(text = "Produk < 50k sendang tidak ada") }
                        } else {
                            items(postsMurah) { barang ->
                                RowLayout(barang = barang, navController = navController)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Terakhir Dilihat", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                if (isLoading) {
                    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        CircularProgressIndicator()
                        Text(text = "Memuat Data...", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSecondary)
                    }
                } else {
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(posts) { barang ->
                            RowLayout(barang = barang, navController = navController)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}