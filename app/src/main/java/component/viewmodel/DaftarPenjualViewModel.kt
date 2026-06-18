package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DaftarPenjualViewModel : ViewModel() {
    var nama by mutableStateOf("")
    var npm by mutableStateOf("")
    var email by mutableStateOf("")
    var fakultas by mutableStateOf("")
    var prodi by mutableStateOf("")
    var alamat by mutableStateOf("")
    var noTelp by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    fun daftarPenjual(userViewModel: UserViewModel) {
        if (nama.isBlank() || npm.isBlank() || email.isBlank() || fakultas.isBlank() || prodi.isBlank() || alamat.isBlank() || noTelp.isBlank()) {
            errorMessage = "Mohon isi semua kolom"
            return
        }

        isLoading = true

        userViewModel.submitPengajuan(
            npm = npm,
            fakultas = fakultas,
            prodi = prodi,
            alamat = alamat,
            noTelp = noTelp
        ) { success ->
            if (success) {
                isSuccess = true
            } else {
                errorMessage = "Terjadi kesalahan saat mendaftar sebagai penjual."
            }
        }
    }

    fun resetError() {
        errorMessage = null
    }
}