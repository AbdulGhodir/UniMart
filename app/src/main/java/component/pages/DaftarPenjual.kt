package component.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.blockbusteruwu.unimart.R
import component.viewmodel.DaftarPenjualViewModel
import component.viewmodel.UserViewModel
import kotlinx.coroutines.delay


@Composable
fun DaftarPenjual(
    modifier: Modifier = Modifier,
    navController: NavController,
    userViewModel: UserViewModel,
    viewModel: DaftarPenjualViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.errorMessage) {
        viewModel.errorMessage?.let { pesanError ->
            snackbarHostState.showSnackbar(pesanError)
            viewModel.resetError()
        }
    }

    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            snackbarHostState.showSnackbar("Pendaftaran berhasil!")
            delay(500)
            navController.navigate("statusPengajuan") {
                popUpTo("daftarPenjual") { inclusive = true }
            }
        }
    }

    Box {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF003366))
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.Close, contentDescription = "Back", tint = Color.White)
                }
                Text(
                    text = "Daftar Sebagai Penjual",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(12.dp))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "Foto KTM", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .border(1.dp, Color(0xFF003366), RoundedCornerShape(8.dp))
                                .background(Color(0xFFF0F4F8))
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_camera),
                                    contentDescription = null,
                                    tint = Color(0xFF003366)
                                )
                                Text(text = "Upload", fontSize = 12.sp, color = Color(0xFF003366))
                            }
                        }
                    }
                }

                Card(
                    colors = CardDefaults.cardColors(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(2.dp, RoundedCornerShape(12.dp))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CustomInputField(label = "NAMA LENGKAP", value = viewModel.nama, onValueChange = { viewModel.nama = it }, placeholder = "Nama sesuai KTM")
                        CustomInputField(label = "NPM", value = viewModel.npm, onValueChange = { viewModel.npm = it }, placeholder = "Contoh: 2217051xxx")
                        CustomInputField(label = "EMAIL", value = viewModel.email, onValueChange = { viewModel.email = it }, placeholder = "Surya@email.com")
                        CustomInputField(label = "FAKULTAS", value = viewModel.fakultas, onValueChange = { viewModel.fakultas = it }, placeholder = "Contoh: MIPA / Teknik")
                        CustomInputField(label = "PROGRAM STUDI", value = viewModel.prodi, onValueChange = { viewModel.prodi = it }, placeholder = "Contoh: Ilmu Komputer")
                        CustomInputField(label = "ALAMAT", value = viewModel.alamat, onValueChange = { viewModel.alamat = it }, placeholder = "Alamat Domisili")
                        CustomInputField(label = "NOMOR TELEPON", value = viewModel.noTelp, onValueChange = { viewModel.noTelp = it }, placeholder = "08xxxxxxxxxx")
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        viewModel.daftarPenjual(userViewModel)
                    },
                    enabled = !viewModel.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF003366))
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "SUBMIT PENDAFTARAN",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
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

@Composable
fun CustomInputField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Column {
        Text(text = label, fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = placeholder, fontSize = 14.sp) },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF003366),
                unfocusedBorderColor = Color(0xFFDEE2E6)
            ),
            singleLine = true
        )
    }
}