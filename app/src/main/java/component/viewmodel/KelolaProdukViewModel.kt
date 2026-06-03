package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.RetrofitClient
import kotlinx.coroutines.launch
import model.Barang

class KelolaProdukViewModel : ViewModel() {
    var produkSaya by mutableStateOf<List<Barang>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    init {
        getDataProdukSaya()
    }

    private fun getDataProdukSaya() {
        viewModelScope.launch {
            try {
                isLoading = true
                produkSaya = RetrofitClient.instance.getPosts()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }
}