package viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import model.User
import model.PengajuanPremium
import repository.FirestoreRepository

class UserViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _currentUser = mutableStateOf<User>(
        User(
            namaLengkap = "Abdul Ghodir",
            username = "abdul",
            email = "abdul@gmail.com",
            noTelp = "08123456789",
            isPremium = true
        )
    )
    val currentUser: State<User> = _currentUser

    private val _pengajuan = mutableStateOf<PengajuanPremium?>(null)
    val pengajuan: State<PengajuanPremium?> = _pengajuan

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        val currentUserEmail = auth.currentUser?.email
        if (currentUserEmail != null) {
            loadUser(currentUserEmail)
        } else {
            loadUser(_currentUser.value.email)
        }
    }

    fun loadUser(email: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val user = repository.getUser(email)
                if (user != null) {
                    _currentUser.value = user
                } else {
                    repository.saveUser(_currentUser.value)
                }
                loadPengajuan(email)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(username: String, email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    val user = User(
                        namaLengkap = username,
                        username = username,
                        email = email,
                        noTelp = "08123456789",
                        isPremium = false
                    )
                    val success = repository.saveUser(user)
                    if (success) {
                        _currentUser.value = user
                        loadPengajuan(email)
                        onComplete(true, null)
                    } else {
                        onComplete(false, "Gagal menyimpan data ke Firestore")
                    }
                } else {
                    onComplete(false, "Registrasi Firebase gagal")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false, e.localizedMessage ?: "Terjadi kesalahan")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                if (result.user != null) {
                    val user = repository.getUser(email)
                    if (user != null) {
                        _currentUser.value = user
                        loadPengajuan(email)
                        onComplete(true, null)
                    } else {
                        val newUser = User(
                            namaLengkap = email.substringBefore("@"),
                            username = email.substringBefore("@"),
                            email = email,
                            noTelp = "08123456789",
                            isPremium = false
                        )
                        repository.saveUser(newUser)
                        _currentUser.value = newUser
                        loadPengajuan(email)
                        onComplete(true, null)
                    }
                } else {
                    onComplete(false, "Login gagal")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                onComplete(false, e.localizedMessage ?: "Email atau password salah")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateProfile(namaLengkap: String, username: String, noTelp: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val updatedUser = _currentUser.value.copy(
                namaLengkap = namaLengkap,
                username = username,
                noTelp = noTelp
            )
            val success = repository.saveUser(updatedUser)
            if (success) {
                _currentUser.value = updatedUser
            }
            _isLoading.value = false
            onComplete(success)
        }
    }

    fun loadPengajuan(email: String) {
        viewModelScope.launch {
            try {
                val app = repository.getPengajuan(email)
                _pengajuan.value = app
                if (app != null && app.status == "APPROVED" && !_currentUser.value.isPremium) {
                    _currentUser.value = _currentUser.value.copy(isPremium = true)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun submitPengajuan(
        npm: String,
        fakultas: String,
        prodi: String,
        alamat: String,
        noTelp: String,
        onComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val app = PengajuanPremium(
                user = _currentUser.value,
                status = "PENDING",
                npm = npm,
                fakultas = fakultas,
                prodi = prodi,
                alamat = alamat,
                noTelp = noTelp
            )
            val success = repository.savePengajuan(app)
            if (success) {
                _pengajuan.value = app
            }
            _isLoading.value = false
            onComplete(success)
        }
    }
}
