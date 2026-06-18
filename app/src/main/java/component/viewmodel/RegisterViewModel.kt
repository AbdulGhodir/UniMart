package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    var namaLengkap by mutableStateOf("")
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var konfirmasiPassword by mutableStateOf("")

    var passwordVisible by mutableStateOf(false)
    var konfirmasiPasswordVisible by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun toggleKonfirmasiPasswordVisibility() {
        konfirmasiPasswordVisible = !konfirmasiPasswordVisible
    }

    fun daftarAkun(userViewModel: UserViewModel) {
        if (namaLengkap.isBlank() || username.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "Semua kolom wajib diisi!"
            return
        }
        if (password != konfirmasiPassword) {
            errorMessage = "Password dan Konfirmasi Password tidak cocok!"
            return
        }
        if (password.length <= 6) {
            errorMessage = "Password minimal 6 karakter!"
            return
        }

        isLoading = true
        errorMessage = null

        userViewModel.register(namaLengkap, username, email, password) { success, errorMsg ->
            isLoading = false

            if (success) {
                isSuccess = true
            } else {
                errorMessage = when {
                    errorMsg?.contains("already in use", ignoreCase = true) == true ->
                        "Email ini sudah terdaftar! Silakan gunakan email lain."
                    errorMsg?.contains("badly formatted", ignoreCase = true) == true ->
                        "Format email tidak valid (contoh: abdul@gmail.com)."
                    else -> errorMsg ?: "Terjadi kesalahan saat mendaftar."
                }
            }
        }
    }

    fun resetError() {
        errorMessage = null
    }
}