package viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import repository.FirestoreRepository

class BarangViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    private val _products = mutableStateOf<List<Barang>>(emptyList())
    val products: State<List<Barang>> = _products

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addProduct(
        nama: String,
        harga: Int,
        deskripsi: String,
        kategori: String,
        gambar: String,
        status: String,
        sellerId: String = "",
        onComplete: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            val success = repository.addProduct(
                Barang(
                    nama = nama,
                    harga = harga,
                    deskripsi = deskripsi,
                    kategori = kategori,
                    gambar = gambar,
                    status = status,
                    sellerId = sellerId
                )
            )
            if (success) {
                loadProducts()
            }
            _isLoading.value = false
            onComplete(success)
        }
    }

    fun updateProduct(barang: Barang, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val success = repository.updateProduct(barang)
            if (success) {
                loadProducts()
            }
            _isLoading.value = false
            onComplete(success)
        }
    }

    fun deleteProduct(id: Int, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            val success = repository.deleteProduct(id)
            if (success) {
                loadProducts()
            }
            _isLoading.value = false
            onComplete(success)
        }
    }
}
