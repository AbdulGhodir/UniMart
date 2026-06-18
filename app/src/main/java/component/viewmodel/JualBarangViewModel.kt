package component.viewmodel

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class JualBarangViewModel: ViewModel() {
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    var namaBarang by mutableStateOf("")
    var hargaBarang by mutableStateOf("")
    var deskripsiBarang by mutableStateOf("")

    val daftarKategori = listOf("Makanan", "Minuman", "Pakaian", "Aksesoris", "Buku", "Perlengkapan", "Perawatan", "Elektronik")
    var kategoriBarang by mutableStateOf(daftarKategori[0])

    val daftarStatus = listOf("Baru", "Prelove")
    var statusBarang by mutableStateOf(daftarStatus[0])

    var imageUri by mutableStateOf<Uri?>(null)

    fun uploadBarang(barangViewModel: BarangViewModel, userViewModel: UserViewModel) {
        if (namaBarang.isBlank() || hargaBarang.isBlank() || deskripsiBarang.isBlank()) {
            errorMessage = "Semua kolom wajib diisi!"
            return
        }

        isLoading = true
        errorMessage = null

        val harga = hargaBarang.toIntOrNull() ?: 0
        val gambar = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png"
        val sellerId = userViewModel.currentUser.value.email
        barangViewModel.addProduct(
            nama = namaBarang,
            harga = harga,
            deskripsi = deskripsiBarang,
            kategori = kategoriBarang,
            gambar = gambar,
            status = statusBarang,
            sellerId = sellerId
        ) { success ->
            if (success) {
                isSuccess = true
            } else {
                errorMessage = "Gagal mengunggah barang"
            }
        }
    }

    fun resetForm() {
        namaBarang = ""
        hargaBarang = ""
        deskripsiBarang = ""
        kategoriBarang = daftarKategori[0]
        statusBarang = daftarStatus[0]
    }

    fun resetError() {
        errorMessage = null
    }
}