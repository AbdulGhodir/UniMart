package component.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import model.Barang
import model.FavoriteManager
import repository.FirestoreRepository

class DetailProdukViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    private val _isBuying = mutableStateOf(false)
    val isBuying: State<Boolean> = _isBuying

    fun toggleFavorite(barang: Barang) {
        FavoriteManager.toggleFavorit(barang = barang)
    }

    fun beliSekarang(barang: Barang, buyerEmail: String, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isBuying.value = true
            val updatedBarang = barang.copy(
                isTerjual = true,
                buyerId = buyerEmail,
                boughtAt = System.currentTimeMillis()
            )
            val success = repository.updateProduct(updatedBarang)
            _isBuying.value = false
            onComplete(success)
        }
    }
}