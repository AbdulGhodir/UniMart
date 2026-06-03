package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JualBarangViewModel: ViewModel() {
    var isLoading by mutableStateOf(false)
        private set
    var namaBarang by mutableStateOf("")
        private set
    var hargaBarang by mutableStateOf("")
        private set
    var deskripsiBarang by mutableStateOf("")
        private set
    var kategoriBarang by mutableStateOf("")
        private set
    var statusBarang by mutableStateOf("")
        private set

    fun onNamaChange(newValue: String) {
        namaBarang = newValue
    }
    fun onHargaChange(newValue: String) {
        hargaBarang = newValue
    }
    fun onDeskripsiChange(newValue: String) {
        deskripsiBarang = newValue
    }
    fun onKategoriChange(newValue: String) {
        kategoriBarang = newValue
    }
    fun onStatusChange(newValue: String) {
        statusBarang = newValue
    }

    fun uploadBarang(onSuccess: () -> Unit, onError: (String) -> Unit) {

        if (namaBarang.isBlank() || hargaBarang.isBlank() || deskripsiBarang.isBlank()) {
            onError("Harap isi semua kolom")
            return
        }

        viewModelScope.launch {
            isLoading = true

            try {
                delay(2000)

                resetForm()
                onSuccess()
            } catch (e: Exception) {
                onError("Gagal mengunggah barang: ${e.message}")

            } finally {
                isLoading = false
            }
        }
    }

    private fun resetForm() {
        namaBarang = ""
        hargaBarang = ""
        deskripsiBarang = ""
        kategoriBarang = "Makanan"
        statusBarang = "Baru"
    }
}