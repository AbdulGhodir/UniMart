package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import repository.FirestoreRepository

class PencarianViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    var semuaBarang by mutableStateOf<List<Barang>>(emptyList())

    var isLoading by mutableStateOf(true)

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                isLoading = true
                semuaBarang = repository.getProducts()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
            }
        }
    }


}