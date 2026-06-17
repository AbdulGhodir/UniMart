package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import model.Barang
import repository.FirestoreRepository

class KelolaProdukViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    var produkSaya by mutableStateOf<List<Barang>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        getDataProdukSaya()
    }

    fun getDataProdukSaya() {
        viewModelScope.launch {
            try {
                isLoading = true
                val currentUser = FirebaseAuth.getInstance().currentUser
                val SellerId = currentUser?.email ?: ""

                val semuaProduk = repository.getProductsBySeller(SellerId)

                produkSaya = semuaProduk.filter { !it.isTerjual }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }
}