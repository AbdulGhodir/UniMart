package component.pages

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.blockbusteruwu.unimart.R
import component.ui.DropdownInput
import component.ui.Input

import component.viewmodel.BarangViewModel
import component.viewmodel.JualBarangViewModel
import component.viewmodel.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun JualBarang(modifier: Modifier = Modifier, navController: NavController, barangViewModel: BarangViewModel, userViewModel: UserViewModel, viewModel: JualBarangViewModel = viewModel()) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { pesanError ->
            snackbarHostState.showSnackbar(pesanError)
            viewModel.resetError()
        }
    }

    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            snackbarHostState.showSnackbar("Barang berhasil diupload!")
            delay(300)
            viewModel.resetForm()
            navController.navigate("kelolaProduk") {
                popUpTo("jualProduk") { inclusive = true }
            }
        }
    }

    val launcherGaleri = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uriTerpilih ->
        if (uriTerpilih != null) {
            viewModel.imageUri = uriTerpilih
        }
    }

    Box {
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
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            launcherGaleri.launch(
                                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                            )
                                        }
                                        .height(100.dp)
                                        .fillMaxHeight()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(MaterialTheme.colorScheme.background)
                                        .border(2.dp, MaterialTheme.colorScheme.secondary, shape = RoundedCornerShape(10.dp)),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    if (viewModel.imageUri != null) {
                                        AsyncImage(
                                            model = viewModel.imageUri,
                                            contentDescription = "foto produk",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight()
                                        )
                                    } else {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_camera),
                                            contentDescription = "camera",
                                            modifier = Modifier
                                                .size(24.dp),
                                            tint = MaterialTheme.colorScheme.secondary,
                                        )

                                        Text(text = "Tambah", color = MaterialTheme.colorScheme.secondary, fontSize = 12.sp)
                                    }
                                }
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
                            Input(modifier = Modifier.padding(0.dp, 6.dp), label = "Nama Barang", text = viewModel.namaBarang, onValueChange = { viewModel.namaBarang = it })

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                DropdownInput(
                                    modifier = Modifier
                                        .weight(1f),
                                    label = "Kategori",
                                    pilihan = viewModel.daftarKategori,
                                    selectedItem = viewModel.kategoriBarang,
                                    onItemSelected = { viewModel.kategoriBarang = it }
                                )

                                DropdownInput(
                                    modifier = Modifier
                                        .weight(1f),
                                    label = "Status",
                                    pilihan = viewModel.daftarStatus,
                                    selectedItem = viewModel.statusBarang,
                                    onItemSelected = { viewModel.statusBarang = it }
                                )
                            }

                            Input(modifier = Modifier.padding(0.dp, 6.dp), label = "Harga", text = viewModel.hargaBarang, onValueChange = { viewModel.hargaBarang = it })

                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(text = "DESKRIPSI", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onSecondary)

                                OutlinedTextField(
                                    value = viewModel.deskripsiBarang,
                                    onValueChange = { viewModel.deskripsiBarang = it },
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
                    onClick = {
                        viewModel.uploadBarang(barangViewModel = barangViewModel, userViewModel = userViewModel)
                    },
                    enabled = !viewModel.isLoading,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onSecondary,
                            strokeWidth = 2.dp
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text("Memproses...")
                    } else {
                        Text(text = "UPLOAD BARANG", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        )
    }
}