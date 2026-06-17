package component.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import repository.FirestoreRepository

class HistoryViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    private val _purchasedProducts = mutableStateOf<List<Barang>>(emptyList())
    val purchasedProducts: State<List<Barang>> = _purchasedProducts

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun loadHistory(buyerEmail: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _purchasedProducts.value = repository.getProductsByBuyer(buyerEmail)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
