package component.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import api.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import model.Barang

class DashboardViewModel: ViewModel() {
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
                dataBarang = RetrofitClient.instance.getPosts()
                barangMurah = dataBarang.filter { it.harga <= 500000 }
                isLoading = false
            } catch (e: Exception) {
                dataBarang = emptyList()
                isLoading = false
            }
        }
    }
}