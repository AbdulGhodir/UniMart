package component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.Barang
import model.FavoriteManager
import repository.FirestoreRepository

class DetailProdukViewModel(private val repository: FirestoreRepository = FirestoreRepository()) : ViewModel() {
    var isBuying by mutableStateOf(false)

    fun toggleFavorite(barang: Barang) {
        FavoriteManager.toggleFavorit(barang = barang)
    }

    fun beliSekarang(barang: Barang, onNavigate: () -> Unit) {
        viewModelScope.launch {
            isBuying = true

            barang.isTerjual = true
            repository.updateProduct(barang)

            isBuying = false
            onNavigate()
        }
    }
}