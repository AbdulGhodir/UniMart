package component.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import model.Barang
import repository.FirestoreRepository

class DashboardViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    var dataBarang by mutableStateOf<List<Barang>>(emptyList())
        private set

    var barangMurah by mutableStateOf<List<Barang>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        getDataBarang()
    }

    private fun getDataBarang() {
        viewModelScope.launch {
            isLoading = true
            try {
                dataBarang = repository.getProducts()
                barangMurah = dataBarang.filter { it.harga <= 50000 }
                isLoading = false
            } catch (e: Exception) {
                dataBarang = emptyList()
                isLoading = false
            }
        }
    }
}