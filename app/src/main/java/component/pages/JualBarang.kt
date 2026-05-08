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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.blockbusteruwu.unimart.R
import component.ui.DropdownInput
import component.ui.Input

@Composable
fun JualBarang(modifier: Modifier = Modifier, navController: NavController) {
    var namaBarang by remember { mutableStateOf("") }
    var hargaBarang by remember { mutableStateOf("") }
    var deskripsiBarang by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 14.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { navController.popBackStack() }
                        .offset(x = (-3).dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                )

                Text(text = "Jual Barang", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onPrimary)
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
                    .padding(14.dp, 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Foto Produk", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(IntrinsicSize.Min),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colorScheme.background)
                                    .border(2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(10.dp)),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = "camera",
                                    modifier = Modifier
                                        .size(24.dp),
                                    tint = MaterialTheme.colorScheme.secondary,
                                )

                                Text(text = "Tambah", color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp)

                            }

                            Icon(
                                painter = painterResource(id = R.drawable.ic_image),
                                contentDescription = "image",
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colorScheme.background)
                                    .border(2.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(10.dp))
                                    .padding(35.dp),
                                tint = MaterialTheme.colorScheme.onSecondary,
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.ic_image),
                                contentDescription = "image",
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MaterialTheme.colorScheme.background)
                                    .border(2.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(10.dp))
                                    .padding(35.dp),
                                tint = MaterialTheme.colorScheme.onSecondary,
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp ,20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Input(modifier = Modifier.padding(0.dp, 6.dp), label = "Nama Barang", text = namaBarang, onValueChange = { namaBarang = it })

                        val daftarKategori = listOf("Makanan", "Minuman", "Pakaian", "Aksesoris", "Buku", "Perlengkapan", "Perawatan", "Elektronik")

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            DropdownInput(
                                modifier = Modifier
                                    .weight(1f),
                                label = "Kategori",
                                pilihan = daftarKategori
                            )

                            DropdownInput(
                                modifier = Modifier
                                    .weight(1f),
                                label = "Status",
                                pilihan = listOf("Baru", "Prelove")
                            )
                        }

                        Input(modifier = Modifier.padding(0.dp, 6.dp), label = "Harga", text = hargaBarang, onValueChange = { hargaBarang = it })

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(text = "DESKRIPSI", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSecondary)

                            OutlinedTextField(
                                value = deskripsiBarang,
                                onValueChange = { deskripsiBarang = it },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(120.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color.White,
                                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                ),
                                shape = RoundedCornerShape(14.dp),
                                placeholder = { Text(text = "Deskripsikan barang Anda disini...", color = MaterialTheme.colorScheme.onSecondary) }
                            )
                        }
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 30.dp,
                    spotColor = Color.Black,
                )
                .background(Color.White)
                .border(1.dp, MaterialTheme.colorScheme.outline)
                .padding(16.dp),
        ) {
            Button(
                onClick = {  },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text(text = "UPLOAD BARANG", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}