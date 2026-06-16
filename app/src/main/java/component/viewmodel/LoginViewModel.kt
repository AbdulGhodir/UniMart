package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var passwordVisible by mutableStateOf(false)

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var isSuccess by mutableStateOf(false)

    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }

    fun loginAkun(userViewModel: UserViewModel) {
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Semua kolom wajib diisi!"
            return
        }

        isLoading = true
        errorMessage = null

        userViewModel.login(email, password) { success, errorMsg ->
            isLoading = false

            if (success) {
                isSuccess = true
            } else {
                errorMessage = when {
                    errorMsg?.contains("credential is incorrect", ignoreCase = true) == true ->
                        "Email atau Password salah."
                    errorMsg?.contains("badly formatted", ignoreCase = true) == true ->
                        "Format email tidak valid (contoh: abdul@gmail.com)."
                    else -> errorMsg ?: "Terjadi kesalahan saat login."
                }
            }
        }
    }

    fun resetError() {
        errorMessage = null
    }
}